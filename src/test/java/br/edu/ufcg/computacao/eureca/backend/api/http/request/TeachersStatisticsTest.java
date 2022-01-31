package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.core.models.EmailSearchResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static br.edu.ufcg.computacao.eureca.backend.util.TestUtils.*;

@WebMvcTest(value = TeachersStatistics.class, secure = false)
public class TeachersStatisticsTest extends EndpointTest {

    private static final String TEACHERS_STATISTICS_ENDPOINT = TeachersStatistics.ENDPOINT;

    @Test
    public void getTeachersStatisticsTest() throws Exception {
        // set up
        TeachersStatisticsResponse res = getTeachersStaticticsResponse();
        Mockito.doReturn(res).when(this.facade).getTeachersStatistics(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, TEACHERS_STATISTICS_ENDPOINT + "?academicUnitId=1411&courseCode=14102100&curriculumCode=2017&from=1950.0&to=2049.9");

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();
        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getTeachersCSVTest() throws Exception {
        // set up
        TeachersResponse res = getTeachersResponse();
        Mockito.doReturn(res).when(this.facade).getTeachersCSV(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, TEACHERS_STATISTICS_ENDPOINT + "/csv?academicUnitId=1411&courseCode=14102100&curriculumCode=2017&from=1950.0&to=2049.9");

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();
        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getTeachersStatisticsSummaryTest() throws Exception {
        // set up
        TeachersStatisticsSummaryResponse res = getTeachersStatisticsSummaryResponse();
        Mockito.doReturn(res).when(this.facade).getTeachersStatisticsSummary(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, TEACHERS_STATISTICS_ENDPOINT + "/summary?courseCode=14102100&curriculumCode=2017&from=1950.0&language=PORTUGUESE&to=2049.9");

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();
        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
    }
}
