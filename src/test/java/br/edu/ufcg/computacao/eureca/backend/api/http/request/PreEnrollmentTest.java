package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectDemandSummary;
import br.edu.ufcg.computacao.eureca.backend.util.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@WebMvcTest(value = PreEnrollment.class, secure = false)
public class PreEnrollmentTest extends EndpointTest {

    private static final String PRE_ENROLLMENT_ENDPOINT = PreEnrollment.PRE_ENROLLMENT_ENDPOINT;

    @Test
    public void testGetBasicPreEnrollment() throws Exception {
        StudentPreEnrollmentResponse expectedResponse = new StudentPreEnrollmentResponse(new StudentPreEnrollment("fake-registration", "2017", 4, 20, 20, 0, 0, 0));
        Mockito.doReturn(expectedResponse).when(this.facade).getPreEnrollment(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.isNull(Integer.class), Mockito.isNull(String.class), Mockito.isNull(String.class), Mockito.isNull(String.class), Mockito.isNull(String.class));

        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, PRE_ENROLLMENT_ENDPOINT + TestUtils.DEFAULT_COURSE_CURRICULUM_QUERY + "&term=2021.1&studentRegistration=fake-registration");
        MvcResult response = this.mockMvc.perform(requestBuilder).andReturn();

        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = response.getResponse().getStatus();

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(TestUtils.getMockedPreEnrollmentResponse(), response.getResponse().getContentAsString());
    }

    @Test
    public void testGetPreEnrollmentWithNumCredits() throws Exception {
        StudentPreEnrollmentResponse expectedResponse = new StudentPreEnrollmentResponse(new StudentPreEnrollment("fake-registration", "2017", 4, 20, 20, 0, 0, 0));
        Mockito.doReturn(expectedResponse).when(this.facade).getPreEnrollment(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.isNull(String.class), Mockito.isNull(String.class), Mockito.isNull(String.class), Mockito.isNull(String.class));

        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, PRE_ENROLLMENT_ENDPOINT + TestUtils.DEFAULT_COURSE_CURRICULUM_QUERY + "&term=2021.1&studentRegistration=fake-registration");
        MvcResult response = this.mockMvc.perform(requestBuilder).andReturn();

        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = response.getResponse().getStatus();

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(TestUtils.getMockedPreEnrollmentResponse(), response.getResponse().getContentAsString());
    }

    @Test
    public void testGetPreEnrollmentWithPriorityList() throws Exception {
        StudentPreEnrollmentResponse expectedResponse = new StudentPreEnrollmentResponse(new StudentPreEnrollment("fake-registration", "2017", 4, 20, 20, 0, 0, 0));
        Mockito.doReturn(expectedResponse).when(this.facade).getPreEnrollment(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.isNull(Integer.class), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, PRE_ENROLLMENT_ENDPOINT + TestUtils.DEFAULT_COURSE_CURRICULUM_QUERY + "&term=2021.1&studentRegistration=fake-registration");
        MvcResult response = this.mockMvc.perform(requestBuilder).andReturn();

        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = response.getResponse().getStatus();

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(TestUtils.getMockedPreEnrollmentResponse(), response.getResponse().getContentAsString());
    }

    @Test
    public void testBatchPreEnrollment() throws Exception {
        StudentPreEnrollment preEnrollmentResponse = new StudentPreEnrollment("fake-registration", "2017", 4, 20, 20, 0, 0, 0);
        PreEnrollmentsResponse expectedResponse = new PreEnrollmentsResponse(Arrays.asList(preEnrollmentResponse), new SubjectDemandSummary(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        Mockito.doReturn(expectedResponse).when(this.facade).getPreEnrollments(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, PRE_ENROLLMENT_ENDPOINT + "/all" + TestUtils.DEFAULT_COURSE_CURRICULUM_QUERY + "&term=2021.1");
        MvcResult response = this.mockMvc.perform(requestBuilder).andReturn();

        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = response.getResponse().getStatus();

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals(TestUtils.getMockedPreEnrollmentsResponse(), response.getResponse().getContentAsString());
    }

    @Test
    public void testGetDemand() throws Exception {
        DemandResponse expectedResponse = new DemandResponse(new SubjectDemandSummary(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        Mockito.doReturn(expectedResponse).when(this.facade).getDemand(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder requestBuilder = this.getRequestBuilder(HttpMethod.GET, PRE_ENROLLMENT_ENDPOINT + "/demand" + TestUtils.DEFAULT_COURSE_CURRICULUM_QUERY + "&term=2021.1");
        MvcResult response = this.mockMvc.perform(requestBuilder).andReturn();

        int expectedStatusCode = HttpStatus.OK.value();
        int actualStatusCode = response.getResponse().getStatus();

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        Assert.assertEquals("{\"subjectDemandSummary\":{\"mandatoryDemand\":[],\"optionalDemand\":[],\"complementaryDemand\":[],\"electiveDemand\":[]}}", response.getResponse().getContentAsString());
    }
}
