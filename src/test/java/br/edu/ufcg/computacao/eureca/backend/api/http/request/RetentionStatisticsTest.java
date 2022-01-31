package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.RetentionStatisticsSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionPerAdmissionTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectsRetentionResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectsRetentionStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentMetrics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentMetricsSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ufcg.computacao.eureca.backend.util.TestUtils.*;

@WebMvcTest(value = RetentionStatistics.class, secure = false)
public class RetentionStatisticsTest extends EndpointTest {

    private static final String RETENTION_STATISTICS_ENDPOINT = RetentionStatistics.ENDPOINT;

    @Test
    public void getStudentRetentionTest() throws Exception {
        // set up
        StudentsRetentionStatisticsResponse res = getMockStudentRetentionResponse();
        Mockito.doReturn(res).when(this.facade).getStudentsRetentionStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, RETENTION_STATISTICS_ENDPOINT + "/students" + DEFAULT_COURSE_CURRICULUM_QUERY);


        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentsRetentionStatisticsResponse(), result.getResponse().getContentAsString());
    }


    @Test
    public void getSubjectRetentionTest() throws Exception {
        // set up
        SubjectsRetentionStatisticsResponse res = getMockedSubjectRetentionResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectsRetentionStatistics(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, RETENTION_STATISTICS_ENDPOINT + "/subjects" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectsRetentionStatisticsResponse(), result.getResponse().getContentAsString());
    }

    @Test
    public void getStudentsRetentionCsvTest() throws Exception {
        StudentsResponse res = getStudentsCsvResponse();
        Mockito.doReturn(res).when(this.facade).getStudentsRetentionCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, RETENTION_STATISTICS_ENDPOINT + "/students/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result = this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedStudentCsvResponse(), result.getResponse().getContentAsString());
    }

    @Test
    public void getSubjectRetentionCsvTest() throws Exception {
        // set up
        SubjectsRetentionResponse res = getSubjectsRetentionCsvResponse();
        Mockito.doReturn(res).when(this.facade).getSubjectsRetentionCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, RETENTION_STATISTICS_ENDPOINT + "/subjects/csv" + DEFAULT_COURSE_CURRICULUM_QUERY);

        // exercise
        MvcResult result= this.mockMvc.perform(req).andReturn();

        // verify
        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedSubjectRetentionCsvResponse(), result.getResponse().getContentAsString());

    }

    @Test
    public void getRetentionStatisticsSummaryTest() throws Exception {
        RetentionStatisticsSummaryResponse res = new RetentionStatisticsSummaryResponse("","",null,null);
        Mockito.doReturn(res).when(this.facade).getRetentionStatisticsSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, RETENTION_STATISTICS_ENDPOINT + "/summary" + DEFAULT_COURSE_CURRICULUM_QUERY);
        MvcResult result= this.mockMvc.perform(req).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assert.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assert.assertEquals(getMockedRetentionSummaryResponse(), result.getResponse().getContentAsString());
    }

    private StudentsRetentionStatisticsResponse getMockStudentRetentionResponse(){
        List<StudentsRetentionPerTermSummary> terms = new ArrayList<>();
        StudentMetrics metrics = new StudentMetrics(0,0,0,0,0,0,0,0);
        StudentMetricsSummary metricsSummary = new StudentMetricsSummary(0,metrics,0,0);
        StudentsRetentionPerTermSummary studentsRetentionSummary = new StudentsRetentionPerTermSummary("0",metricsSummary);
        terms.add(studentsRetentionSummary);
        StudentsRetentionStatisticsResponse response= new StudentsRetentionStatisticsResponse(terms,"14112100","2017","x","y");
        return response;
    }

    private SubjectsRetentionStatisticsResponse getMockedSubjectRetentionResponse(){
        List<SubjectRetentionPerAdmissionTerm> retention = new ArrayList<>();
        List<SubjectRetentionPerAdmissionTermSummary > subjectRetentionSummary = new ArrayList<>();
        SubjectRetentionPerAdmissionTerm subjectRetentionPerAdmissionTerm = new SubjectRetentionPerAdmissionTerm("0",0,0);
        retention.add(subjectRetentionPerAdmissionTerm);
        SubjectRetentionPerAdmissionTermSummary subjectRetentionPerAdmissionTermSummary = new SubjectRetentionPerAdmissionTermSummary("x","y","0","x",1,retention);
        subjectRetentionSummary.add(subjectRetentionPerAdmissionTermSummary);
        SubjectsRetentionStatisticsResponse response = new SubjectsRetentionStatisticsResponse(subjectRetentionSummary,"14112100","2017");
        return response;
    }

}
