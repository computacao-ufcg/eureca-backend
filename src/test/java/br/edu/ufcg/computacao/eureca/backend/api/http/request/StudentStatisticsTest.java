package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import java.util.Collection;

import static br.edu.ufcg.computacao.eureca.backend.util.TestUtils.*;

@WebMvcTest(value = StudentsStatistics.class, secure = false)
public class StudentStatisticsTest extends EndpointTest {

    private static final String STUDENT_STATISTICS_ENDPOINT = StudentsStatistics.ENDPOINT;

    // test case: Calls the GetActives route and tests a successfully return.
    @Test
    public void testGetActives() throws Exception {
        // set up
        ActivesSummaryResponse response = getActivesSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getActiveSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedActiveSummaryResponse(), res.getResponse().getContentAsString());
    }

    // test case: Calls the getActivesCsv route and tests a successfully return.
    @Test
    public void testGetActivesCsv() throws Exception {
        // set up
        Collection<StudentDataResponse> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getActiveCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives/csv", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentCsvResponse(), res.getResponse().getContentAsString());
    }

    // test case: Calls the getAlumni route and tests a successfully return.
    @Test
    public void testGetAlumni() throws Exception {
        // set up
        AlumniSummaryResponse response = getAlumniSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getAlumniSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/alumni", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedAlumniSummaryResponse(), res.getResponse().getContentAsString());
    }

    // test case: Calls the getAlumniCsv route and tests a successfully return.
    @Test
    public void testGetAlumniCsv() throws Exception {
        // set up
        Collection<StudentDataResponse> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getAlumniCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives/csv", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentCsvResponse(), res.getResponse().getContentAsString());
    }

    // test case: Calls the getDropouts route and tests a successfully return.
    @Test
    public void getDropoutsTest() throws Exception {
        // set up
        DropoutsSummaryResponse response = getDropoutsSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getDropoutsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/dropouts", null, "");

        // exercise
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    // test case: Calls the getDropoutsCsv route and tests a successfully return.
    @Test
    public void getDropoutsCsvTest() throws Exception {
        // set up
        Collection<StudentDataResponse> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getDropoutsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/dropouts/csv", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());

    }

    // test case: Calls the getDelayed route and tests a successfully return.
    @Test
    public void getDelayedTest() throws Exception {
        // set up
        DelayedSummaryResponse response = getDelayedSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getDelayedSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/delayed", null, "");

        // exercise
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    // test case: Calls the getDelayedCsv route and tests a successfully return.
    @Test
    public void getDelayedCsvTest() throws Exception {
        // set up
        Collection<StudentDataResponse> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getDelayedCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/delayed/csv", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }


}
