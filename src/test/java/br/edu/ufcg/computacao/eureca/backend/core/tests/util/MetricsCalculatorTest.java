package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataAccessFacadeHolder.class, DataAccessFacade.class})
public class MetricsCalculatorTest {

    private MetricsCalculator metricsCalculator;

    @Before
    public void setUp() {
        mockDataAccessFacadeHolder();
        this.metricsCalculator = MetricsCalculator.getInstance();
    }

    // Test case: Call the getInstance method and tests a successfully return.
    @Test
    public void getInstanceTest() {
        assertNotEquals(this.metricsCalculator, null);
    }

    // mock method from the DataAccessFacadeHolder class, that simulates the behavior of a real object.
    public void mockDataAccessFacadeHolder() {
        DataAccessFacadeHolder dataAccessFacadeHolder = DataAccessFacadeHolder.getInstance();
        DataAccessFacade dataAccessFacade = mock(DataAccessFacade.class);
        dataAccessFacadeHolder.setDataAccessFacade(dataAccessFacade);
        PowerMockito.mockStatic(DataAccessFacadeHolder.class);
        BDDMockito.given(DataAccessFacadeHolder.getInstance()).willReturn(dataAccessFacadeHolder);
    }

    // checks the behavior of computeMetrics method, and whether it returns an object of type Metrics.
    @Test
    public void computeMetricsWithValidStudentTest() {
        // Student object creation.
        String registrationNumber = "12346533354";
        CpfRegistration cpfRegistrationFake = new CpfRegistration("+55", registrationNumber);
        StudentData studentDataFake = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Inativo (GRADUADO 2011.2)",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x", 0,0,0,
                0,0,0,0,
                0,0,0,0,0,
                0,0,0);

        Student student = new Student(cpfRegistrationFake, studentDataFake);

        Assert.assertEquals(metricsCalculator.computeMetrics(student) instanceof Metrics, true);
    }

}
