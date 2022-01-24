package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.core.models.EmailSearchResponse;
import com.sun.xml.internal.ws.server.UnsupportedMediaException;
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

    // Test case: Call the getStudentsEmailSearch method and tests a successfully return.
    @Test
    public void getStudentsEmailSearch() throws Exception {
        // set up
        String STUDENTS_EMAIL_SEARCH_ENDPOINT = "/studentsEmailSearch?admissionTerm=&affirmativePolicy=&courseCode=cra=&craOperation=" +
                "&curriculumCode=&enrolledCredits=&enrolledCreditsOperation=&gender=&status=&studentName=";

        Map<String, EmailSearchResponse> response = new HashMap<>();
        Mockito.doReturn(response).when(this.facade).getStudentsEmailsSearch(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
                , Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
        );
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + STUDENTS_EMAIL_SEARCH_ENDPOINT);

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }

    // Test case: Call the getStudentsEmailSearch method with invalid course code or curriculum.
    @Test
    public void getStudentsEmailSearchCourseOrCurriculumNotFound() throws Exception {
        // set up
        String STUDENTS_EMAIL_SEARCH_ENDPOINT = "/studentsEmailSearch?admissionTerm=&affirmativePolicy=&courseCode=cra=&craOperation=" +
                "&curriculumCode=&enrolledCredits=&enrolledCreditsOperation=&gender=&status=&studentName=";

        Mockito.doThrow(UnsupportedMediaException.class).when(this.facade).getStudentsEmailsSearch(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
                , Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()
        );
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + STUDENTS_EMAIL_SEARCH_ENDPOINT);

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), res.getResponse().getStatus());

    }

    // Test case: Call the getSubjectEmailsSearch method and tests a successfully return.
    @Test
    public void getSubjectsEmailSearch() throws Exception {
        // set up
        String SUBJECTS_EMAIL_SEARCH_ENDPOINT = "/subjectEmailSearch?academicUnit=&courseCode=&curriculumCode=&subjectName=&subjectType=&term=";

        Map<String, EmailSearchResponse> response = new HashMap<>();
        Mockito.doReturn(response).when(this.facade).getSubjectEmailsSearch(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + SUBJECTS_EMAIL_SEARCH_ENDPOINT);

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }

    // Test case: Call the getSubjectEmailsSearch method with invalid course code or curriculum.
    @Test
    public void getSubjectsEmailSearchCourseOrCurriculumNotFound() throws Exception {
        // set up
        String SUBJECTS_EMAIL_SEARCH_ENDPOINT = "/subjectEmailSearch?academicUnit=&courseCode=&curriculumCode=&subjectName=&subjectType=&term=";

        Mockito.doThrow(UnsupportedMediaException.class).when(this.facade).getSubjectEmailsSearch(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + SUBJECTS_EMAIL_SEARCH_ENDPOINT);

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), res.getResponse().getStatus());
    }

    // Test case: Call the getTeacherEmailsSearch method and tests a successfully return.
    @Test
    public void getTeachersEmailSearch() throws Exception {
        // set up
        String TEACHERS_EMAIL_SEARCH_ENDPOINT =  "/teacherEmailSearch?academicUnit=&courseCode=&curriculumCode=&teacherId=&teacherName=&term=";

        Map<String, EmailSearchResponse> response = new HashMap<>();
        Mockito.doReturn(response).when(this.facade).getTeacherEmailsSearch(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + TEACHERS_EMAIL_SEARCH_ENDPOINT);

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
    }

    // Test case: Call the getTeacherEmailsSearch method with invalid course code or curriculum.
    @Test
    public void getTeachersEmailSearchCourseOrCurriculumNotFound() throws Exception {
        // set up
        String TEACHERS_EMAIL_SEARCH_ENDPOINT =  "/teacherEmailSearch?academicUnit=&courseCode=&curriculumCode=&teacherId=&teacherName=&term=";

        Mockito.doThrow(UnsupportedMediaException.class).when(this.facade).getTeacherEmailsSearch(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),
                Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, COMMUNICATION_ENDPOINT + TEACHERS_EMAIL_SEARCH_ENDPOINT);

        // exercise
        MvcResult res = this.mockMvc.perform(req).andReturn();

        // verify
        Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), res.getResponse().getStatus());
    }

}
