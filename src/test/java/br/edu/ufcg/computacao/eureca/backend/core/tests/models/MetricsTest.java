package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MetricsTest {

    private Metrics metrics;

    @Before
    public void setUp() {
        this.metrics = new Metrics(1,1,1,1,
                1,1,1,1);
    }

    // test case: Call the getAttemptedCredits method and tests a successfully return.
    @Test
    public void getAttemptedCreditsTest() {
        // set up
        int expected = 1;

        // exercise
        int attemptedCredits = this.metrics.getAttemptedCredits();

        // verify
        assertEquals(expected, attemptedCredits);
    }

    // test case: Call the getAttemptedCredits method with null attempted credits and tests a successfully return.
    @Test
    public void getNullAttemptedCreditsTest() {
        // set up
        Metrics metrics = new Metrics(null,1,1,1,
                1,1,1,1);
        int expected = 0;

        // exercise
        int attemptedCredits = metrics.getAttemptedCredits();

        // verify
        assertEquals(expected, attemptedCredits);

    }

    // test case: Call the setAttemptedCredits method and tests a successfully return.
    @Test
    public void setAttemptedCreditsTest() {
        // set up
        int expected = 2;

        // exercise
        this.metrics.setAttemptedCredits(2);

        // verify
        int attemptedCredits = this.metrics.getAttemptedCredits();
        assertEquals(expected, attemptedCredits);
    }

    // test case: Call the getFeasibility method and tests a successfully return.
    @Test
    public void getFeasibilityTest() {
        // set up
        int expected = 1;

        // exercise
        double feasibility = this.metrics.getFeasibility();

        // verify
        assertEquals(expected, feasibility, 0);
    }

    // test case: Call the setFeasibility method and tests a successfully return.
    @Test
    public void setFeasibilityTest() {
        // set up
        int expected = 2;

        // exercise
        this.metrics.setFeasibility(2);

        // verify
        double feasibility = this.metrics.getFeasibility();
        assertEquals(expected, feasibility, 0);
    }

    // test case: Call the getSuccessRate method and tests a successfully return.
    @Test
    public void getSuccessRateTest() {
        // set up
        int expected = 1;

        // exercise
        double successRate = this.metrics.getSuccessRate();

        // verify
        assertEquals(expected, successRate, 0);
    }

    // test case: Call the setSuccessRate method and tests a successfully return.
    @Test
    public void setSuccessRateTest() {
        // set up
        int expected = 2;

        // exercise
        this.metrics.setSuccessRate(2);

        // verify
        double successRate = this.metrics.getSuccessRate();
        assertEquals(expected, successRate, 0);
    }

    // test case: Call the getAverageLoad method and tests a successfully return.
    @Test
    public void getAverageLoadTest() {
        // set up
        int expected = 1;

        // exercise
        double avarageLoad = this.metrics.getAverageLoad();

        // verify
        assertEquals(expected, avarageLoad, 0);
    }

    // test case: Call the setAverageLoad method and tests a successfully return.
    @Test
    public void setAverageLoadTest() {
        // set up
        int expected = 2;

        // exercise
        this.metrics.setAverageLoad(2);

        // verify
        double avarageLoad = this.metrics.getAverageLoad();
        assertEquals(expected, avarageLoad, 0);
    }

    // test case: Call the getCost method and tests a successfully return.
    @Test
    public void getCostTest() {
        // set up
        int expected = 1;

        // exercise
        double cost = this.metrics.getCost();

        // verify
        assertEquals(expected, cost, 0);
    }

    // test case: Call the setCost method and tests a successfully return.
    @Test
    public void setCostTest() {
        // set up
        int expected = 2;

        // exercise
        this.metrics.setCost(2);

        // verify
        double cost = this.metrics.getCost();
        assertEquals(expected, cost, 0);

    }

    // test case: Call the getPace method and tests a successfully return.
    @Test
    public void getPaceTest() {
        // set up
        int expected = 1;

        // exercise
        double pace = this.metrics.getPace();

        // verify
        assertEquals(expected, pace, 0);
    }

    // test case: Call the setPace method and tests a successfully return.
    @Test
    public void setPaceTest() {
        // set up
        int expected = 2;

        // exercise
        this.metrics.setPace(2);

        // verify
        double pace = this.metrics.getPace();
        assertEquals(expected, pace, 0);
    }

    // test case: Call the getCourseDurationPrediction method and tests a successfully return.
    @Test
    public void getCourseDurationPredictionTest() {
        // set up
        int expected = 1;

        // exercise
        int courseDurationPrediction = this.metrics.getCourseDurationPrediction();

        // verify
        assertEquals(expected, courseDurationPrediction);
    }

    // test case: Call the setCourseDurationPrediction method and tests a successfully return.
    @Test
    public void setCourseDurationPredictionTest() {
        // set up
        int expected = 2;

        // exercise
        this.metrics.setCourseDurationPrediction(2);

        // verify
        int courseDurationPrediction = this.metrics.getCourseDurationPrediction();
        assertEquals(expected, courseDurationPrediction);
    }

    // test case: Call the getRisk method and tests a successfully return.
    @Test
    public void getRiskTest() {
        // set up
        int expected = 1;

        // exercise
        double risk = this.metrics.getRisk();

        // verify
        assertEquals(expected, risk, 0);
    }

    // test case: Call the setRisk method and tests a successfully return.
    @Test
    public void setRiskTest() {
        // set up
        int expected = 2;

        // exercise
        metrics.setRisk(2);

        // verify
        double risk = this.metrics.getRisk();
        assertEquals(expected, risk, 0);
    }

}
