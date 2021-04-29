package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentData;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.Collection;

@WebMvcTest(value = StudentsStatistics.class, secure = false)
public class StudentStatisticsTest extends EndpointTest {

    private static final String STUDENT_STATISTICS_ENDPOINT = StudentsStatistics.ENDPOINT;

    @Test
    public void testGetActives() throws Exception {
        ActivesSummaryResponse response = getActivesSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getActiveSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assertions.assertEquals(this.getMockedActiveSummaryResponse(), res.getResponse().getContentAsString());
    }

    @Test
    public void testGetActivesCsv() throws Exception {
        Collection<StudentDataResponse> response = this.getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getActiveCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives/csv", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assertions.assertEquals(this.getMockedStudentCsvResponse(), res.getResponse().getContentAsString());
    }

    @Test
    public void testGetAlumni() throws Exception {
        AlumniSummaryResponse response = this.getAlumniSummaryResponse();
        Mockito.doReturn(response).when(this.facade).getAlumniSummary(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/alumni", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assertions.assertEquals(this.getMockedAlumniSummaryResponse(), res.getResponse().getContentAsString());
    }

    @Test
    public void testGetAlumniCsv() throws Exception {
        Collection<StudentDataResponse> response = this.getStudentsCsvResponse();
        Mockito.doReturn(response).when(this.facade).getAlumniCSV(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        RequestBuilder req = this.getRequestBuilder(HttpMethod.GET, STUDENT_STATISTICS_ENDPOINT + "/actives/csv", null, "");
        MvcResult res = this.mockMvc.perform(req).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), res.getResponse().getStatus());
        Assertions.assertEquals(this.getMockedStudentCsvResponse(), res.getResponse().getContentAsString());
    }

    private ActivesSummaryResponse getActivesSummaryResponse() {
        RiskClassCountSummary riskClassCountSummary = new RiskClassCountSummary(0, 0, 0, 0, 0, 0, 0);
        ActivesPerTermSummary activesPerTerm = new ActivesPerTermSummary("", riskClassCountSummary);
        return new ActivesSummaryResponse(Arrays.asList("slider", "label"), Arrays.asList(activesPerTerm));
    }

    private AlumniSummaryResponse getAlumniSummaryResponse() {
        AlumniPerTermSummary alumniPerTerm = new AlumniPerTermSummary("x", 10, 5, 4, 1);
        return new AlumniSummaryResponse(Arrays.asList("slider", "label"), Arrays.asList(alumniPerTerm));
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

    private String getMockedActiveSummaryResponse() {
        return "{" +
                "\"sliderLabel\":[\"slider\",\"label\"]" +
                ",\"terms\":[{\"admissionTerm\":\"\",\"riskClassCount\":{\"inaccurate\":0,\"safe\":0,\"low\":0,\"average\":0,\"high\":0,\"unfeasible\":0,\"notApplicable\":0}}]" +
                "}";
    }

    private String getMockedAlumniSummaryResponse() {
        return "{" +
                "\"sliderLabel\":[\"slider\",\"label\"]" +
                ",\"terms\":[{\"graduationTerm\":\"x\",\"alumniCount\":10,\"averageGpa\":5.0,\"averageTerms\":4.0,\"averageCost\":1.0}]" +
                "}";
    }

    private String getMockedStudentCsvResponse() {
        return "[{\"registration\":\"fake-registration\"," +
                "\"name\":\"x\",\"gender\":\"x\",\"maritalStatus\":\"x\",\"curriculum\":\"x\",\"affirmativePolicy\":\"x\"," +
                "\"admissionType\":\"VESTIBULAR\",\"admissionTerm\":\"2007.2\",\"statusStr\":\"Ativo\",\"statusTerm\":\"Current\"," +
                "\"entryGrade\":0.0,\"gpa\":5.68,\"iea\":1.69,\"mc\":7.0,\"mandatoryCredits\":120,\"complementaryCredits\":26,\"electiveCredits\":58," +
                "\"completedTerms\":14,\"attemptedCredits\":0.0,\"institutionalEnrollments\":0,\"mobilityTerms\":0,\"suspendedTerms\":1," +
                "\"feasibility\":2.5,\"successRate\":-1.0,\"averageLoad\":0.0,\"cost\":-1.0,\"pace\":13.285714285714286,\"courseDurationPrediction\":15.0,\"risk\":1.4634146341463414," +
                "\"riskClass\":\"UNFEASIBLE\",\"costClass\":\"NOT_APPLICABLE\"}]";
    }
}
