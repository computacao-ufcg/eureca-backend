package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectMetricsStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsStatisticsSummary;
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
        Mockito.doReturn(getSubjectStatisticsMock()).when(this.facade).getSubjectsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        int expectedStatusCode = HttpStatus.OK.value();

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT + "/summary", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(expectedStatusCode, res.getResponse().getStatus());
    }

    private SubjectsSummaryResponse getSubjectStatisticsMock() {
        MetricStatistics metrics = new MetricStatistics(1, 3, 5, 7, 9, 6, 100);
        SubjectMetricsStatistics subjectMetrics = new SubjectMetricsStatistics(metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectsStatisticsSummary mandatory = new SubjectsStatisticsSummary(30, subjectMetrics);
        SubjectsStatisticsSummary optional = new SubjectsStatisticsSummary(10, subjectMetrics);
        SubjectsStatisticsSummary elective = new SubjectsStatisticsSummary(15, subjectMetrics);
        SubjectsStatisticsSummary complementary = new SubjectsStatisticsSummary(10, subjectMetrics);
        SubjectsSummaryResponse summary = new SubjectsSummaryResponse("14102100", "2017", "1980.1", "2020.1", mandatory, optional, elective, complementary);
        return summary;
    }
}
