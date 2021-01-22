package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.AttemptsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.validation.constraints.Null;
import java.util.*;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MetricsCalculator.class)
public class MetricsCalculatorTest {

    @InjectMocks
    private static MetricsCalculator instance;

    @Mock
    private Map<Registration, Integer> attemptsMap;

    // Auxiliary object for testing.
    private Student student;

    // setup: creation of a base objects that will be used in the tests.
    @Before
    public void setUp() {
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

        student = new Student(cpfRegistrationFake, studentDataFake);

        // adding registration and attemptedCredits in the attemptsMap.
        Registration registrationFake = new Registration("12346533354");
        attemptsMap.put(registrationFake, 24);

        // attemptsMap structure creation and populate.
        attemptsMap = new HashMap<>();
        Registration reg1 = new Registration("123456789");
        Registration reg2 = new Registration("323131331");
        Registration reg3 = new Registration("355125512");
        attemptsMap.put(reg1, 24);
        attemptsMap.put(reg2, 20);
        attemptsMap.put(reg3, 18);
    }

    // ckecks if the instance is actually created.
    @Test
    public void getInstanceTest() {
        mockInstance();
        instance = MetricsCalculator.getInstance();
    }

    public void mockInstance() {
        DataAccessFacadeHolder dataAccessFacadeHolder = mock(DataAccessFacadeHolder.class);
        Mockito.when(dataAccessFacadeHolder.getDataAccessFacade().getAttemptsSummary()).thenReturn((Collection<AttemptsSummary>) attemptsMap);
        PowerMockito.mockStatic(DataAccessFacadeHolder.class);
        BDDMockito.given(DataAccessFacadeHolder.getInstance()).willReturn(dataAccessFacadeHolder);
    }

    // checks the behavior of computeMetrics method, and whether it returns an object of type Metrics.
    @Test
    public void computeMetricsWithAllDataCorrectTest() {
        Assert.assertEquals(MetricsCalculator.getInstance().computeMetrics(student) instanceof Metrics, true);
    }

    // checks the behavior of computeMetrics method when the registration of the student not in attemptsMap.
    @Test
    public void computeMetricsWithStudentDataInvalidTest() throws NullPointerException {
        // Student object creation with registration invalid.
        String registrationNumber = "34342243412";
        CpfRegistration cpfRegistrationFake = new CpfRegistration("+55", registrationNumber);
        StudentData studentDataFake = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Inativo (GRADUADO 2011.2)",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x", 0,0,0,
                0,0,0,0,
                0,0,0,0,0,
                0,0,0);

        student = new Student(cpfRegistrationFake, studentDataFake);

        Assert.assertEquals(MetricsCalculator.getInstance().computeMetrics(student), null);
    }


}
