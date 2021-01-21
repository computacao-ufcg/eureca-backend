package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
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

import static br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass.LATE;
import static br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass.UNFEASIBLE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MetricsCalculator.class)
public class StudentTest {

    private Student student;
    private StudentData fakeStudentData;
    private CpfRegistration fakeCpfRegistration;

    @Before
    public void setUp() {
        this.fakeStudentData = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Inativo (GRADUADO 2011.2)",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x", 0,0,0,
                0,0,0,0,
                0,0,0,0,0,
                0,0,0);
        this.fakeCpfRegistration = new CpfRegistration("nationalId", "registration");
        this.student = new Student(fakeCpfRegistration, fakeStudentData);

    }

    // test case: Call the getId method and tests a successfully return.
    @Test
    public void getIdTest() {
        // set up
        CpfRegistration expected = this.fakeCpfRegistration;

        // exercise
        CpfRegistration id = this.student.getId();

        // verify
        assertEquals(expected, id);
    }

    // test case: Call the getStudentData method and tests a successfully return.
    @Test
    public void getStudentDataTest() {
        // set up
        StudentData expected = this.fakeStudentData;

        // exercise
        StudentData studentData = this.student.getStudentData();

        // verify
        assertEquals(expected, studentData);
    }

    // test case: Call the getRiskClass method and tests a successfully return.
    @Test
    public void getRiskClassTest() {
        // set up
        RiskClass expected = LATE;
        mockMetricsCalculator();

        // exercise
        RiskClass riskClass = this.student.getRiskClass();

        // verify
        assertEquals(expected, riskClass);

    }

    public void mockMetricsCalculator() {
        Metrics metrics = new Metrics(0,0,0,0,0,0,0,0);
        MetricsCalculator metricsCalculator = mock(MetricsCalculator.class);
        Mockito.when(metricsCalculator.computeMetrics(this.student)).thenReturn(metrics);
        PowerMockito.mockStatic(MetricsCalculator.class);
        BDDMockito.given(MetricsCalculator.getInstance()).willReturn(metricsCalculator);
    }

}
