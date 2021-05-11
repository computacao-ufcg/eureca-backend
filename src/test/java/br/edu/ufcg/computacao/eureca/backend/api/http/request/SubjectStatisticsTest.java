package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsSummaryResponse;
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
        Mockito.doReturn(new SubjectsSummaryResponse()).when(this.facade).getSubjectsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        int expectedStatusCode = HttpStatus.OK.value();

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT + "/summary", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(expectedStatusCode, res.getResponse().getStatus());
        Assert.assertEquals("{\"term\":null,\"code\":null,\"name\":null,\"enrollments\":null,\"summary\":null}", res.getResponse().getContentAsString());
    }
}
