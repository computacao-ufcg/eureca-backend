package br.edu.ufcg.computacao.eureca.backend.api.http.request;

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


@WebMvcTest(value = Communication.class, secure = false)
public class CommunicationTest extends EndpointTest{

    private static final String COMMUNICATION_ENDPOINT = Communication.ENDPOINT;

    @Test
    public void getStudentsEmailSearch() throws Exception {
        // set up
        Map<String, EmailSearchResponse> response = new HashMap<>();
        Mockito.doReturn(response).when(this.facade).getStudentsEmailsSearch(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
                , Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
        );
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + "/studentsEmailSearch?admissionTerm=2019.1&affirmativePolicy=L1&courseCode=14102100&cra=0&craOperation=%3E%3D&curriculumCode=2017&enrolledCredits=0&enrolledCreditsOperation=%3E%3D&gender=Feminino&status=Todos&studentName=a");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }

    @Test
    public void getSubjectsEmailSearch() throws Exception {
        // set up
        Map<String, EmailSearchResponse> response = new HashMap<>();
        Mockito.doReturn(response).when(this.facade).getSubjectEmailsSearch(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + "/subjectEmailSearch?academicUnit=1141&courseCode=14102100&curriculumCode=2017&subjectName=Sistemas&subjectType=MANDATORY&term=2019.1");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }

    @Test
    public void getTeachersEmailSearch() throws Exception {
        // set up
        Map<String, EmailSearchResponse> response = new HashMap<>();
        Mockito.doReturn(response).when(this.facade).getTeacherEmailsSearch(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + "/teacherEmailSearch?academicUnit=1411&courseCode=14102100&curriculumCode=2017&teacherId=%5E%24&teacherName=%5E%24&term=2019.1");

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }
}
