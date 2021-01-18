package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricsSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MetricsSummaryTest {

    // instance of the MetricsSummary object.
    private MetricsSummary metricsSummary;

    // setup: creation of a base object of the type MetricsSummary that will be used in the tests.
    @Before
    public void setUp() {
        int activeStudentsCount = 100;
        double averageCost = 26.1;
        double averageCourseDurationPrediction = 0.4;

        metricsSummary = new MetricsSummary(activeStudentsCount, averageCost, averageCourseDurationPrediction);
    }

    // test case 1: check if the Actives Students Count number is correct.
    @Test
    public void getActiveStudentsCountTest() {
        Assert.assertEquals(metricsSummary.getActiveStudentsCount(), 100);
    }

    // test case 2: changes the Actives Student Count number of the instance.
    @Test
    public void setActiveStudentsCountTest() {
        metricsSummary.setActiveStudentsCount(200);
        Assert.assertEquals(metricsSummary.getActiveStudentsCount(), 200);
    }

    // test case 3: check if the Average Cost is correct.
    @Test
    public void getAverageCostTest() {
        Assert.assertEquals(metricsSummary.getAverageCost(), 26.1, 0.0);
    }

    // test case 4: changes the Average Cost of the instance.
    @Test
    public void setAverageCostTest() {
        metricsSummary.setAverageCost(18.3);
        Assert.assertEquals(metricsSummary.getAverageCost(), 18.3, 0.0);
    }

    // test case 5: check if the Average Course Duration Prediction is correct.
    @Test
    public void getAverageCourseDurationPrediction() {
        Assert.assertEquals(metricsSummary.getAverageCourseDurationPrediction(), 0.4, 0.0);
    }

    // test case 6: changes the Average Course Duration Prediction of the instance.
    @Test
    public void setAverageCourseDurationPrediction() {
        metricsSummary.setAverageCourseDurationPrediction(0.8);
        Assert.assertEquals(metricsSummary.getAverageCourseDurationPrediction(), 0.8, 0.0);
    }

}
