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
import org.powermock.reflect.Whitebox;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataAccessFacadeHolder.class, DataAccessFacade.class})
public class MetricsCalculatorTest {

    private MetricsCalculator metricsCalculator;
    private Student student;

    @Before
    public void setUp() {
        CpfRegistration cpfRegistrationStudent = new CpfRegistration("+55", "12346533354");
        StudentData studentData = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Ativo",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x", 0,120,0,
                58,0,26,5.68,
                7,1.69,14,1,0,
                0,0,0);
        this.student = new Student(cpfRegistrationStudent, studentData);
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

    // Test case:
    @Test
    public void computeMetricsWithValidStudentTest() {
        // set up
        Metrics expected = new Metrics(0,-1,-1,-1,-1,13.2,15,0.07);

        // exercise
        Metrics result = this.metricsCalculator.computeMetrics(this.student);

        // verify
        Assert.assertEquals(expected.getAttemptedCredits(), result.getAttemptedCredits());
        Assert.assertEquals(expected.getCourseDurationPrediction(), result.getCourseDurationPrediction());
        Assert.assertEquals(expected.getCost(), result.getCost(), 0.1);
        Assert.assertEquals(expected.getAverageLoad(), result.getAverageLoad(), 0.1);
        Assert.assertEquals(expected.getFeasibility(), result.getFeasibility(), 0.1);
        Assert.assertEquals(expected.getPace(), result.getPace(), 0.1);
        Assert.assertEquals(expected.getRisk(), result.getRisk(), 0.1);
        Assert.assertEquals(expected.getSuccessRate(), result.getSuccessRate(), 0.1);
    }

    // Test case:
    @Test
    public void computeFeasibilityTest() throws Exception {
        // set up
        double expected = -1;

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeFeasibility", this.student);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }

    // Test case:
    @Test
    public void computeSuccessRateTest() throws Exception {
        // set up
        double expected = -1;

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeSuccessRate", this.student);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }

    // Test case:
    @Test
    public void computeAverageLoadTest() throws Exception {
        // set up
        double expected = -1;

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeAverageLoad", this.student);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }

    // Test case:
    @Test
    public void computeCostTest() throws Exception {
        // set up
        double expected = -1;

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeCost", this.student);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }

    // Test case:
    @Test
    public void computePaceTest() throws Exception {
        // set up
        double expected = 13.2;

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computePace", this.student);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }

    // Test case:
    @Test
    public void computeCourseDurationPredictionTest() throws Exception {
        // set up
        int expected = 15;

        // exercise
        int result = Whitebox.invokeMethod(this.metricsCalculator, "computeCourseDurationPrediction", this.student);

        // verify
        Assert.assertEquals(expected, result);
    }

    // Test case:
    @Test
    public void computeRiskTest() throws Exception {
        // set up
        double expected = 0.07;

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeRisk", this.student);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }
}
