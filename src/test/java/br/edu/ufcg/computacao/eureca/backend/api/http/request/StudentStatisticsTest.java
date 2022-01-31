package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsStatisticsSummaryResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static br.edu.ufcg.computacao.eureca.backend.util.TestUtils.*;

@WebMvcTest(value = StudentsStatistics.class, secure = false)
public class StudentStatisticsTest extends EndpointTest {

    private static final String STUDENT_STATISTICS_ENDPOINT = StudentsStatistics.ENDPOINT;

    // test case: Calls the GetActives route and tests a successfully return.
    @Test
    public void getActivesTest() throws Exception {
        // set up
        ActivesStatisticsResponse res = getActivesSummaryResponse();
        Mockito.doReturn(res).when(this.facade).getActivesStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedActiveSummaryResponse(), result.getResponse().getContentAsString());
    }

    // test case: Calls the getActivesCsv route and tests a successfully return.
    @Test
    public void getActivesCsvTest() throws Exception {
        // set up
        StudentsResponse res = getStudentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getActivesCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentCsvResponse(), result.getResponse().getContentAsString());
    }

    // test case: Calls the getAlumni route and tests a successfully return.
    @Test
    public void getAlumniStatisticsTest() throws Exception {
        // set up
        AlumniStatisticsResponse res = getAlumniSummaryResponse();
        Mockito.doReturn(res).when(this.facade).getAlumniStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/alumni" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedAlumniStatisticsResponse(), result.getResponse().getContentAsString());
    }

    // test case: Calls the getAlumniCsv route and tests a successfully return.
    @Test
    public void getAlumniCsvTest() throws Exception {
        // set up
        StudentsResponse res = getStudentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getAlumniCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/alumni/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        //verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentCsvResponse(), result.getResponse().getContentAsString());
    }

    // test case: Calls the getDropouts route and tests a successfully return.
    @Test
    public void getDropoutsTest() throws Exception {
        // set up
        DropoutsStatisticsResponse res = getDropoutsSummaryResponse();
        Mockito.doReturn(res).when(this.facade).getDropoutsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/dropouts" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();
        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    // test case: Calls the getDropoutsCsv route and tests a successfully return.
    @Test
    public void getDropoutsCsvTest() throws Exception {
        // set up
        StudentsResponse res = getStudentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getDropoutsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/dropouts/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();
        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getStudentStatisticsTest() throws Exception {
        StudentsStatisticsSummaryResponse res = new StudentsStatisticsSummaryResponse("", "", null, null, null);
        Mockito.doReturn(res).when(this.facade).getStudentsStatisticsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/summary" + DEFAULT_COURSE_CURRICULUM_QUERY);
        MvcResult result = this.mockMvc.perform(req).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentStatisticsSummaryResponse(), result.getResponse().getContentAsString());
    }
}
