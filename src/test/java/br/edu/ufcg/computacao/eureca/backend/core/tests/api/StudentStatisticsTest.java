package br.edu.ufcg.computacao.eureca.backend.core.tests.api;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.request.StudentsStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.request.SubjectsStatistics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentData;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.spy;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@WebMvcTest(value = StudentsStatistics.class, secure = false)
@PrepareForTest(ApplicationFacade.class)
public class StudentStatisticsTest {

    private static final String STUDENT_STATISTICS_ENDPOINT = SystemConstants.SERVICE_BASE_ENDPOINT + "statistics/students";

    @Autowired
    private MockMvc mockMvc;

    private ApplicationFacade facade;

    @Before
    public void setUp() {
        this.facade = spy(ApplicationFacade.class);
        PowerMockito.mockStatic(ApplicationFacade.class);
        BDDMockito.given(ApplicationFacade.getInstance()).willReturn(this.facade);
    }

    // test case: Call the getDropouts route and tests a successfully return.
    @Test
    public void getDropoutsTest() throws Exception {
        // set up
        DropoutsSummaryResponse response = getDropoutsSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getDropoutsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/dropouts", getHttpHeaders(), "");

        // exercise
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
    }

    @Test
    public void getDropoutsCsvTest() throws Exception {
        // set up
        Collection<StudentDataResponse> response = this.getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getDropoutsCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = createRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/dropouts/csv", getHttpHeaders(), "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());

    }

    // test case: Call the getDelayed route and tests a successfully return.
    @Test
    public void getDelayedTest() throws Exception {
        // set up
        DelayedSummaryResponse response = getDelayedSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getDelayedSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/delayed", getHttpHeaders(), "");

        // exercise
        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void getDelayedCsvTest() throws Exception {
        // set up
        Collection<StudentDataResponse> response = this.getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getDelayedCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = createRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/delayed/csv", getHttpHeaders(), "");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }

    private DropoutsSummaryResponse getDropoutsSummaryResponse() {
        DropoutReasonSummary reasonSummary = new DropoutReasonSummary(0,0,0,
                0,0,0,
                0,0,0,
                0,0);
        DropoutPerTermSummary dropouts = new DropoutPerTermSummary("", 0, reasonSummary, 0, 0);
        return new DropoutsSummaryResponse(Arrays.asList("slider", "label"), Arrays.asList(dropouts));
    }

    private DelayedSummaryResponse getDelayedSummaryResponse() {
        Metrics metrics = new Metrics(0,0,0,
                0,0,0,0,0);
        MetricsSummary metricsSummary = new MetricsSummary(0, metrics);
        DelayedPerTermSummary delayedPerTermSummary = new DelayedPerTermSummary("", metricsSummary);
        return new DelayedSummaryResponse(Arrays.asList("slider", "label"), Arrays.asList(delayedPerTermSummary));
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String fakeUserToken = "fake-access-id";
        headers.set(CommonKeys.AUTHENTICATION_TOKEN_KEY, fakeUserToken);
        return headers;
    }

    private RequestBuilder createRequestBuilder(HttpMethod method, String urlTemplate, HttpHeaders headers, String body) {
        switch (method) {
            case GET:
                return MockMvcRequestBuilders.get(urlTemplate)
                        .headers(headers)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON);
            default:
                return null;
        }
    }

    private Collection<StudentDataResponse> getStudentsCsvResponse() {
        StudentData mockedStudentData = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Ativo",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x",0,120,0,
                58,0,26,5.68,
                7,1.69,14,1,0,
                0,0,0);
        StudentDataResponse mockedStudentDataResponse = new StudentDataResponse("fake-registration", mockedStudentData);
        return Arrays.asList(mockedStudentDataResponse);
    }
}
