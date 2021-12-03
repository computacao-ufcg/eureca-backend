package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsMetricsPerTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsMetricsPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import br.edu.ufcg.computacao.eureca.backend.util.TestUtils;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ufcg.computacao.eureca.backend.util.TestUtils.getMockedActiveSummaryResponse;


@WebMvcTest(value = EnrollmentsStatistics.class, secure = false)
public class EnrollmentsStatisticsTest extends EndpointTest {

    private static final String ENROLLMENTS_STATISTICS_ENDPOINT = EnrollmentsStatistics.ENDPOINT;

    @Test
    public void testGetElectiveEnrollments() throws Exception {
        EnrollmentsStatisticsResponse res = mockEnrollmentsStatisticsResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = ENROLLMENTS_STATISTICS_ENDPOINT + "/elective?courseCode=" +
                "14102100&curriculumCode=2017&from=1950.0&to=2049.9";
        System.out.println(endpoint);

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET,endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void testGetMandatoryEnrollments() throws Exception {
        EnrollmentsStatisticsResponse res = mockEnrollmentsStatisticsResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = ENROLLMENTS_STATISTICS_ENDPOINT + "/mandatory?courseCode=" +
                "14102100&curriculumCode=2017&from=1950.0&to=2049.9";
        System.out.println(endpoint);

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET,endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void testGetOptionalEnrollments() throws Exception {
        EnrollmentsStatisticsResponse res = mockEnrollmentsStatisticsResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectEnrollmentsStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(SubjectType.class));
        String endpoint = ENROLLMENTS_STATISTICS_ENDPOINT + "/optional?courseCode=" +
                "14102100&curriculumCode=2017&from=1950.0&to=2049.9";
        System.out.println(endpoint);

        HttpHeaders headers = TestUtils.getTokenHeaders();
        RequestBuilder request = TestUtils.createRequestBuilder(HttpMethod.GET,endpoint, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    private EnrollmentsStatisticsResponse mockEnrollmentsStatisticsResponse(){
        List<EnrollmentsMetricsPerTerm> listCollection = new ArrayList<>();
        List<EnrollmentsMetricsPerTermSummary> listSummary = new ArrayList<>();
        EnrollmentsMetricsPerTerm metricsResponse = new EnrollmentsMetricsPerTerm("2017",0,0,0);
        listCollection.add(metricsResponse);
        EnrollmentsMetricsPerTermSummary metricsSummaryResponse = new EnrollmentsMetricsPerTermSummary("1950.0","2049.9","1411192","SISTEMAS OPERACIONAIS",listCollection);
        listSummary.add(metricsSummaryResponse);
        EnrollmentsStatisticsResponse response = new EnrollmentsStatisticsResponse(listSummary,"14102100","2017");

        return response;
    }
}
