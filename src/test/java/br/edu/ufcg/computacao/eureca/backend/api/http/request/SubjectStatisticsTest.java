package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectMetricsStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectStatisticsSummary;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

@WebMvcTest(value = SubjectsStatistics.class, secure = false)
public class SubjectStatisticsTest extends EndpointTest {

    private static String SUBJECT_STATISTICS_ENDPOINT = SubjectsStatistics.ENDPOINT;

    @Test
    public void getSubjectSummaryTest() throws Exception {
        Mockito.doReturn(getSubjectStatisticsMock()).when(this.facade).getSubjectsStatisticsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        int expectedStatusCode = HttpStatus.OK.value();

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT + "/summary", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(expectedStatusCode, res.getResponse().getStatus());
    }

    private SubjectsStatisticsSummaryResponse getSubjectStatisticsMock() {
        MetricStatistics metrics = new MetricStatistics(1, 3, 5, 7, 9, 6, 100);
        SubjectMetricsStatistics subjectMetrics = new SubjectMetricsStatistics(metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsSummary mandatory = new SubjectStatisticsSummary(30, subjectMetrics);
        SubjectStatisticsSummary optional = new SubjectStatisticsSummary(10, subjectMetrics);
        SubjectStatisticsSummary elective = new SubjectStatisticsSummary(15, subjectMetrics);
        SubjectStatisticsSummary complementary = new SubjectStatisticsSummary(10, subjectMetrics);
        SubjectsStatisticsSummaryResponse summary = new SubjectsStatisticsSummaryResponse("14102100", "2017", "2018.1", "2020.1", mandatory, optional, elective, complementary);
        return summary;
    }
}
