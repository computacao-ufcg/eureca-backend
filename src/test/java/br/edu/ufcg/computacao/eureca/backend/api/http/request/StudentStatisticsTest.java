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
        ActivesStatisticsResponse response = getActivesSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getActivesStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
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
        Collection<StudentCSV> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getActivesCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
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
        AlumniStatisticsResponse response = getAlumniSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getAlumniStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
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
        Collection<StudentCSV> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getAlumniCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/alumni/csv", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentCsvResponse(), res.getResponse().getContentAsString());
    }

    // test case: Calls the getDropouts route and tests a successfully return.
    @Test
    public void getDropoutsTest() throws Exception {
        // set up
        DropoutsStatisticsResponse response = getDropoutsSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getDropoutsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
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
        Collection<StudentCSV> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getDropoutsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
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
        StudentsRetentionStatisticsResponse response = getDelayedSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getStudentsRetentionStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
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
        Collection<StudentCSV> response = getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getStudentsRetentionCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/delayed/csv", null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }

    @Test
    public void getStudentStatisticsTest() throws Exception {
        StudentsStatisticsSummaryResponse response = new StudentsStatisticsSummaryResponse(null, null, null);
        Mockito.doReturn(response).when(this.facade).getStudentsStatisticsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/summary", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals("{\"activesSummary\":null,\"alumniSummary\":null,\"dropoutsSummary\":null,\"glossary\":null}", res.getResponse().getContentAsString());
    }
}
