package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.models.MetricsSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MetricsSummaryTest {

    private MetricsSummary metricsSummary;

    // setup: creation of a base object of the type MetricsSummary that will be used in the tests.
    @Before
    public void setUp() {
        int activeStudentsCount = 100;
        double averageCost = 26.1;
        double averageCourseDurationPrediction = 0.4;

        metricsSummary = new MetricsSummary(activeStudentsCount, averageCost, averageCourseDurationPrediction);
    }

    // test case: check if the Actives Students Count number is correct.
    @Test
    public void getActiveStudentsCountTest() {
        Assert.assertEquals(metricsSummary.getActiveStudentsCount(), 100);
    }

    // test case: changes the Actives Student Count number of the instance.
    @Test
    public void setActiveStudentsCountTest() {
        metricsSummary.setActiveStudentsCount(200);
        Assert.assertEquals(metricsSummary.getActiveStudentsCount(), 200);
    }


}
