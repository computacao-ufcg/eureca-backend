package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import br.edu.ufcg.computacao.eureca.backend.util.TestUtils;
import static br.edu.ufcg.computacao.eureca.backend.util.TestUtils.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;



@WebMvcTest(value = EnrollmentsStatistics.class, secure = false)
public class EnrollmentsStatisticsTest extends EndpointTest {

    private static final String ENROLLMENTS_STATISTICS_ENDPOINT = EnrollmentsStatistics.ENDPOINT;

    @Test
    public void testGetComplementaryEnrollments() throws Exception {
        EnrollmentsStatisticsResponse res = mockEnrollmentsStatisticsResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = ENROLLMENTS_STATISTICS_ENDPOINT + "/complementary" + DEFAULT_COURSE_CURRICULUM_QUERY;

        RequestBuilder request = this.getRequestBuilder(HttpMethod.GET, endpoint);
        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getComplementaryEnrollmentsCsvTest() throws Exception {
        // set up
        EnrollmentsResponse res = getEnrollmentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, ENROLLMENTS_STATISTICS_ENDPOINT + "/complementary/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectEnrollmentCsvResponse(), result.getResponse().getContentAsString());

    }

    @Test
    public void testGetElectiveEnrollments() throws Exception {
        EnrollmentsStatisticsResponse res = mockEnrollmentsStatisticsResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = ENROLLMENTS_STATISTICS_ENDPOINT + "/elective" + DEFAULT_COURSE_CURRICULUM_QUERY;

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET, endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getElectiveEnrollmentsCsvTest() throws Exception {
        // set up
        EnrollmentsResponse res = getEnrollmentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, ENROLLMENTS_STATISTICS_ENDPOINT + "/elective/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectEnrollmentCsvResponse(), result.getResponse().getContentAsString());

    }

    @Test
    public void getMandatoryEnrollmentsTest() throws Exception {
        EnrollmentsStatisticsResponse res = mockEnrollmentsStatisticsResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = ENROLLMENTS_STATISTICS_ENDPOINT + "/mandatory" + DEFAULT_COURSE_CURRICULUM_QUERY;

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET, endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getMandatoryEnrollmentsCsvTest() throws Exception {
        // set up
        EnrollmentsResponse res = getEnrollmentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, ENROLLMENTS_STATISTICS_ENDPOINT + "/mandatory/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectEnrollmentCsvResponse(), result.getResponse().getContentAsString());

    }

    @Test
    public void getOptionalEnrollmentsTest() throws Exception {
        EnrollmentsStatisticsResponse res = mockEnrollmentsStatisticsResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = ENROLLMENTS_STATISTICS_ENDPOINT + "/optional" + DEFAULT_COURSE_CURRICULUM_QUERY;

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET, endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getOptionalEnrollmentsCsvTest() throws Exception {
        // set up
        EnrollmentsResponse res = getEnrollmentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, ENROLLMENTS_STATISTICS_ENDPOINT + "/optional/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectEnrollmentCsvResponse(), result.getResponse().getContentAsString());

    }

    @Test
    public void getStudentStatisticsTest() throws Exception {
        EnrollmentsStatisticsSummaryResponse res = new EnrollmentsStatisticsSummaryResponse("","",null,null,null,null);
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatisticsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, ENROLLMENTS_STATISTICS_ENDPOINT + "/summary" + DEFAULT_COURSE_CURRICULUM_QUERY);
        MvcResult result = this.mockMvc.perform(req).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectEnrollmentSummaryResponse(), result.getResponse().getContentAsString());
    }

    private EnrollmentsStatisticsResponse mockEnrollmentsStatisticsResponse() {
        List<EnrollmentsMetricsPerTerm> listCollection = new ArrayList<>();
        List<EnrollmentsMetricsPerTermSummary> listSummary = new ArrayList<>();
        EnrollmentsMetricsPerTerm metricsResponse = new EnrollmentsMetricsPerTerm("2017", 0, 0, 0);
        listCollection.add(metricsResponse);
        EnrollmentsMetricsPerTermSummary metricsSummaryResponse = new EnrollmentsMetricsPerTermSummary("1950.0", "2049.9", "1411192", "SISTEMAS OPERACIONAIS", listCollection);
        listSummary.add(metricsSummaryResponse);
        EnrollmentsStatisticsResponse response = new EnrollmentsStatisticsResponse(listSummary, "14102100", "2017");

        return response;
    }
}
