package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectsStatisticsSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectsStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.util.TestUtils;
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

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT + "/summary" + TestUtils.DEFAULT_COURSE_CURRICULUM_QUERY, null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(expectedStatusCode, res.getResponse().getStatus());
    }

    private SubjectsStatisticsSummaryResponse getSubjectStatisticsMock() {
        MetricStatistics metrics = new MetricStatistics(1, 3, 5, 7, 9, 6, 100);
        SubjectMetricsStatistics subjectMetrics = new SubjectMetricsStatistics(metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectsStatisticsSummary mandatory = new SubjectsStatisticsSummary("2018.1", "2020.1", 30, subjectMetrics);
        SubjectsStatisticsSummary optional = new SubjectsStatisticsSummary("2018.1", "2020.1", 10, subjectMetrics);
        SubjectsStatisticsSummary elective = new SubjectsStatisticsSummary("2018.1", "2020.1", 15, subjectMetrics);
        SubjectsStatisticsSummary complementary = new SubjectsStatisticsSummary("2018.1", "2020.1", 10, subjectMetrics);
        SubjectsStatisticsSummaryResponse summary = new SubjectsStatisticsSummaryResponse("14102100", "2017", mandatory, optional, elective, complementary);
        return summary;
    }
}
