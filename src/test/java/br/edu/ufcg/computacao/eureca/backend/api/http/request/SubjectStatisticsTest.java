package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsMetricsPerTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsMetricsPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricStatistics;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import br.edu.ufcg.computacao.eureca.backend.util.TestUtils;
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

import static br.edu.ufcg.computacao.eureca.backend.util.TestUtils.*;

@WebMvcTest(value = SubjectsStatistics.class, secure = false)
public class SubjectStatisticsTest extends EndpointTest {

    private static String SUBJECT_STATISTICS_ENDPOINT = SubjectsStatistics.ENDPOINT;

    @Test
    public void testGetComplementarySubjects() throws Exception {
        SubjectsStatisticsResponse res = getSubjectsStatisticsMock();
        Mockito.doReturn(res).when(this.facade).getSubjectsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = SUBJECT_STATISTICS_ENDPOINT + "/complementary" + DEFAULT_COURSE_CURRICULUM_QUERY;

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET, endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getComplementarySubjectsCsvTest() throws Exception {
        // set up
        SubjectsResponse response = getSubjectsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getSubjectsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT+ "/complementary/csv" + DEFAULT_COURSE_CURRICULUM_QUERY, null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectCsvResponse(), res.getResponse().getContentAsString());

    }

    @Test
    public void testGetElectiveSubjects() throws Exception {
        SubjectsStatisticsResponse res = getSubjectsStatisticsMock();
        Mockito.doReturn(res).when(this.facade).getSubjectsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = SUBJECT_STATISTICS_ENDPOINT + "/elective" + DEFAULT_COURSE_CURRICULUM_QUERY;

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET, endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getElectiveSubjectsCsvTest() throws Exception {
        // set up
        SubjectsResponse response = getSubjectsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getSubjectsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT+ "/elective/csv" + DEFAULT_COURSE_CURRICULUM_QUERY, null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectCsvResponse(), res.getResponse().getContentAsString());

    }

    @Test
    public void testGetMandatorySubjects() throws Exception {
        SubjectsStatisticsResponse res = getSubjectsStatisticsMock();
        Mockito.doReturn(res).when(this.facade).getSubjectsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = SUBJECT_STATISTICS_ENDPOINT + "/mandatory" + DEFAULT_COURSE_CURRICULUM_QUERY;

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET, endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getMandatorySubjectsCsvTest() throws Exception {
        // set up
        SubjectsResponse response = getSubjectsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getSubjectsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT+ "/mandatory/csv" + DEFAULT_COURSE_CURRICULUM_QUERY, null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectCsvResponse(), res.getResponse().getContentAsString());

    }
    @Test
    public void testGetOptionalSubjects() throws Exception {
        SubjectsStatisticsResponse res = getSubjectsStatisticsMock();
        Mockito.doReturn(res).when(this.facade).getSubjectsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = SUBJECT_STATISTICS_ENDPOINT + "/optional" + DEFAULT_COURSE_CURRICULUM_QUERY;

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET, endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getOptionalSubjectsCsvTest() throws Exception {
        // set up
        SubjectsResponse response = getSubjectsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getSubjectsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT+ "/optional/csv" + DEFAULT_COURSE_CURRICULUM_QUERY, null, "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectCsvResponse(), res.getResponse().getContentAsString());

    }
    @Test
    public void getSubjectSummaryTest() throws Exception {
        Mockito.doReturn(getSubjectStatisticsSummaryMock()).when(this.facade).getSubjectsStatisticsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        int expectedStatusCode = HttpStatus.OK.value();

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, SUBJECT_STATISTICS_ENDPOINT + "/summary" + TestUtils.DEFAULT_COURSE_CURRICULUM_QUERY, null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assert.assertEquals(expectedStatusCode, res.getResponse().getStatus());
    }

    private SubjectsStatisticsSummaryResponse getSubjectStatisticsSummaryMock() {
        MetricStatistics metrics = new MetricStatistics(1, 3, 5, 7, 9, 6, 100);
        SubjectMetricsStatistics subjectMetrics = new SubjectMetricsStatistics(metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectsStatisticsSummary mandatory = new SubjectsStatisticsSummary("2018.1", "2020.1", 30, subjectMetrics);
        SubjectsStatisticsSummary optional = new SubjectsStatisticsSummary("2018.1", "2020.1", 10, subjectMetrics);
        SubjectsStatisticsSummary elective = new SubjectsStatisticsSummary("2018.1", "2020.1", 15, subjectMetrics);
        SubjectsStatisticsSummary complementary = new SubjectsStatisticsSummary("2018.1", "2020.1", 10, subjectMetrics);
        SubjectsStatisticsSummaryResponse summary = new SubjectsStatisticsSummaryResponse("14102100", "2017", mandatory, optional, elective, complementary);
        return summary;
    }

    private SubjectsStatisticsResponse getSubjectsStatisticsMock(){
        List<SubjectMetricsPerTerm> listCollection = new ArrayList<>();
        List<SubjectMetricsPerTermSummary> listSummary = new ArrayList<>();
        SubjectMetrics metrics = new SubjectMetrics(0,0,0,0,0,0,0,0,0);
        SubjectMetricsPerTerm metricsResponse = new SubjectMetricsPerTerm("2017",metrics);
        listCollection.add(metricsResponse);
        SubjectMetricsPerTermSummary  metricsSummaryResponse = new SubjectMetricsPerTermSummary("x","y","x","x",listCollection);
        listSummary.add(metricsSummaryResponse);
        SubjectsStatisticsResponse response = new SubjectsStatisticsResponse(listSummary,"14102100","2017");

        return response;
    }

}
