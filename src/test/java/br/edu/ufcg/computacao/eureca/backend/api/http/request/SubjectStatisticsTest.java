package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectStatisticsItem;
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

    private SubjectSummaryResponse getSubjectStatisticsMock() {
        MetricSummary metrics = new MetricSummary(20, 30, 25);
        SubjectStatisticsItem mandatory = new SubjectStatisticsItem(30,metrics,metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem optional = new SubjectStatisticsItem(10, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem elective = new SubjectStatisticsItem(15,metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem complementary = new SubjectStatisticsItem(10, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectSummaryResponse summary = new SubjectSummaryResponse("2017", "1980.1", "2020.1", mandatory, optional, elective, complementary);
        return summary;
    }
}
