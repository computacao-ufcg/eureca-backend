package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.StudentMetricsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.NationalIdRegistrationKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.CostClass;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.StudentMetrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.util.StudentMetricsCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataAccessFacadeHolder.class, DataAccessFacade.class})
public class MetricsCalculatorTest {

    private StudentMetricsCalculator metricsCalculator;
    private StudentData studentData;

    @Before
    public void setUp() {
        this.studentData = new StudentData("x", "Ativo", "VESTIBULAR 2007.2", "x", "x", "x", "x",
                "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", 1980,196,840,
                56,450,30,5.68,
                7,1.69,14,1,0,
                0,0,0);

        this.metricsCalculator = new StudentMetricsCalculator();

    }

    // Test case: Calls the getInstance method and tests a successfully return.
    @Test
    public void getInstanceTest() {
        assertNotEquals(this.metricsCalculator, null);
    }

    // Test case: Calls the computeMetrics method with valid studentData and tests a successfully return.
    @Test
    public void computeMetricsWithValidStudentTest() {
        // set up
        StudentMetrics expected = new StudentMetrics(0,0,-1,0,-1,18.5,11,1);

        // exercise
        int attemptedCredits = this.studentData.getAttemptedCredits();
        int termsAccounted = this.studentData.getCompletedTerms() + this.studentData.getInstitutionalEnrollments() + this.studentData.getInstitutionalEnrollments();
        int completedCredits = this.studentData.getCompletedCredits();
        StudentMetrics result = StudentMetricsCalculator.computeMetrics(attemptedCredits, termsAccounted, completedCredits, null);

        // verify
        Assert.assertEquals(expected.getAttemptedCredits(), result.getAttemptedCredits(), 0.1);
        Assert.assertEquals(expected.getCourseDurationPrediction(), result.getCourseDurationPrediction(), 0);
        Assert.assertEquals(expected.getCost(), result.getCost(), 0);
        Assert.assertEquals(expected.getAverageLoad(), result.getAverageLoad(), 0);
        Assert.assertEquals(expected.getFeasibility(), result.getFeasibility(), 0);
        Assert.assertEquals(expected.getPace(), result.getPace(), 0.1);
        Assert.assertEquals(expected.getRisk(), result.getRisk(), 0.1);
        Assert.assertEquals(expected.getSuccessRate(), result.getSuccessRate(), 0);
    }

    // Test case: Calls the computeFeasibility method with valid parameters and tests a successfully return.
    @Test
    public void computeFeasibilityTest() throws Exception {
        // set up
        double expected = 0;
        int completedTerms = this.studentData.getCompletedTerms();
        int completedCredits = this.studentData.getCompletedCredits();

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeFeasibility", completedTerms, completedCredits);

        // verify
        Assert.assertEquals(expected, result, 0);
    }

    // Test case: Calls the computeSuccessRate method with valid parameters and tests a successfully return.
    @Test
    public void computeSuccessRateTest() throws Exception {
        // set up
        double expected = -1;
        int completedCredits = this.studentData.getCompletedCredits();
        int attemptedCredits = this.studentData.getAttemptedCredits();

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeSuccessRate", completedCredits, attemptedCredits);

        // verify
        Assert.assertEquals(expected, result, 0);
    }

    // Test case: Calls the computeAverageLoad method with valid parameters and tests a successfully return.
    @Test
    public void computeAverageLoadTest() throws Exception {
        // set up
        double expected = 0;
        int completedTerms = this.studentData.getCompletedTerms();
        int attemptedCredits = this.studentData.getAttemptedCredits();

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeAverageLoad", completedTerms, attemptedCredits);

        // verify
        Assert.assertEquals(expected, result, 0);
    }

    // Test case: Calls the computeCost method with valid parameters and tests a successfully return.
    @Test
    public void computeCostTest() throws Exception {
        // set up
        double expected = -1;
        int completedTerms = this.studentData.getCompletedTerms();
        int completedCredits = this.studentData.getCompletedCredits();
        int attemptedCredits = this.studentData.getAttemptedCredits();

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeCost", completedTerms, completedCredits, attemptedCredits);

        // verify
        Assert.assertEquals(expected, result, 0);
    }

    // Test case: Calls the computePace method with valid parameters and tests a successfully return.
    @Test
    public void computePaceTest() throws Exception {
        // set up
        double expected = 18.5;
        int completedTerms = this.studentData.getCompletedTerms();
        int completedCredits = this.studentData.getCompletedCredits();

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computePace", completedTerms, completedCredits);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }

    // Test case: Calls the computeCourseDurationPrediction method with valid parameters and tests a successfully return.
    @Test
    public void computeCourseDurationPredictionTest() throws Exception {
        // set up
        int expected = 11;
        int completedTerms = this.studentData.getCompletedTerms();
        int completedCredits = this.studentData.getCompletedCredits();

        // exercise
        int result = Whitebox.invokeMethod(this.metricsCalculator, "computeCourseDurationPrediction", completedTerms, completedCredits);

        // verify
        Assert.assertEquals(expected, result);
    }

    // Test case: Calls the computeRisk method with valid parameters and tests a successfully return.
    @Test
    public void computeRiskTest() throws Exception {
        // set up
        double expected = 1;
        int completedTerms = this.studentData.getCompletedTerms();
        int completedCredits = this.studentData.getCompletedCredits();

        // exercise
        double result = Whitebox.invokeMethod(this.metricsCalculator, "computeRisk", completedTerms, completedCredits);

        // verify
        Assert.assertEquals(expected, result, 0.1);
    }

    // Test case: Calls the computeRiskClass method with valid parameters and tests a successfully return.
    @Test
    public void computeRiskClassTest() {
        // set up
        RiskClass expected = RiskClass.LOW;

        // exercise
        RiskClass result = StudentMetricsCalculator.computeRiskClass(1, 0);

        Assert.assertEquals(expected, result);
    }

    // Test case: Calls the computeCostClass method with valid parameters and tests a successfully return.
    @Test
    public void computeCostClassTest() {
        // set up
        CostClass expected = CostClass.NOT_APPLICABLE;

        // exercise
        CostClass result = StudentMetricsCalculator.computeCostClass(-1, 0);

        // verify
        Assert.assertEquals(expected, result);
    }

    // Test case: Calls the computeMetricsSummary method with valid parameters and tests a successfully return.
    @Test
    public void computeMetricsSummaryTest() {
        // set up
        List<Student> students = new ArrayList<>();
        NationalIdRegistrationKey cpfRegistration = new NationalIdRegistrationKey("","");
        Student student = this.studentData.createStudent(cpfRegistration, null);
        students.add(student);

        // exercise
        StudentMetricsSummary metricsSummary = StudentMetricsCalculator.computeMetricsSummary(students);

        // verify
        Assert.assertNotNull(metricsSummary);

    }
}
