package br.edu.ufcg.computacao.eureca.backend.util;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.RiskClassCountSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.active.ActivesStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni.AlumniStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutReasonSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutsStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.student.StudentsRetentionStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectsRetentionResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetrics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectsResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.*;
import br.edu.ufcg.computacao.eureca.backend.constants.TeachersGlossaryFields;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.EnrollmentData;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.students.StudentsResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.NationalIdRegistrationKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.RegistrationSubjectCodeTermKey;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

public class TestUtils {

    private static final String DEFAULT_COURSE_CODE = "14102100";
    private static final String DEFAULT_CURRICULUM_CODE = "2017";
    public static final String DEFAULT_COURSE_CURRICULUM_QUERY = String.format("?courseCode=%s&curriculumCode=%s", DEFAULT_COURSE_CODE, DEFAULT_CURRICULUM_CODE);

    public static RequestBuilder createRequestBuilder(HttpMethod method, String url, HttpHeaders headers, String content) {
        switch (method) {
            case GET:
                return MockMvcRequestBuilders.get(url)
                        .headers(headers)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON);
            case POST:
                return null;
            default:
                return null;
        }
    }

    public static HttpHeaders getTokenHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CommonKeys.AUTHENTICATION_TOKEN_KEY, "fake-token");
        return headers;
    }

    public static ActivesStatisticsResponse getActivesSummaryResponse() {
        RiskClassCountSummary riskClassCountSummary = new RiskClassCountSummary(0, 0, 0, 0, 0, 0, 0);
        ActivesPerTermSummary activesPerTerm = new ActivesPerTermSummary("", riskClassCountSummary);
        return new ActivesStatisticsResponse(Arrays.asList(activesPerTerm), "", "", "x", "y");
    }

    public static AlumniStatisticsResponse getAlumniSummaryResponse() {
        AlumniPerTermSummary alumniPerTerm = new AlumniPerTermSummary("x", 10, 5, 4, 1);
        return new AlumniStatisticsResponse(Arrays.asList(alumniPerTerm), "", "", "x", "y");
    }

    public static StudentsResponse getStudentsCsvResponse() {
        StudentData mockedStudentData = new StudentData("x", "Ativo", "VESTIBULAR 2007.2", "x", "09/12/1965", "x", "x",
                "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", 1980,196,840,
                56,450,30,5.68,
                7,1.69,14,1,0,
                0,0,0);
        StudentCSV mockedStudentDataResponse = new StudentCSV(mockedStudentData.
                createStudent(new NationalIdRegistrationKey("fake-national-id", "fake-registration"), getCurriculum()));
        return new StudentsResponse(Arrays.asList(mockedStudentDataResponse));
    }

    public static EnrollmentsResponse getEnrollmentsCsvResponse(){
        EnrollmentsCSV mockedEnrollmentDataResponse = new EnrollmentsCSV("x","x","x","x","x","x",0,0,0,0,0,0,0,0);
        return new EnrollmentsResponse(Arrays.asList(mockedEnrollmentDataResponse));
    }

    public static SubjectsResponse getSubjectsCsvResponse(){
        SubjectMetrics metrics = new SubjectMetrics(0,0,0,0,0,0,0,0,0);
        SubjectCSV mockSubjectDataResponse = new SubjectCSV("x","x","x","x","x", metrics);
        return new SubjectsResponse(Arrays.asList(mockSubjectDataResponse));
    }

    public static SubjectsRetentionResponse getSubjectsRetentionCsvResponse(){
        SubjectMetrics metrics = new SubjectMetrics(0,0,0,0,0,0,0,0,0);
        SubjectRetentionCSV mockSubjectDataResponse = new SubjectRetentionCSV("x","x",0,"x","x","x",0,0,0,0,0,0,0,0,0,0);
        return new SubjectsRetentionResponse(Arrays.asList(mockSubjectDataResponse));
    }

    public static String getMockedSubjectRetentionCsvResponse(){
        return "{\"subjectRetention\"" +
                ":[{\"courseCode\":\"x\",\"curriculumCode\":\"x\",\"idealTerm\":0,\"subjectCode\":\"x\",\"subjectName\":\"x\",\"registration\":\"x\",\"attemptedCredits\":0," +
                "\"mandatoryCredits\":0,\"optionalCredits\":0,\"electiveCredits\":0,\"complementaryCredits\":0,\"completedTerms\":0,\"suspendedTerms\":0,\"institutionalTerms\":0," +
                "\"mobilityTerms\":0,\"gpa\":0.0}]}";
    }

    public static String getMockedActiveSummaryResponse() {
        return "{" +
                "\"from\":\"x\",\"to\":\"y\",\"courseCode\":\"\",\"curriculumCode\":\"\"," +
                "\"activesPerTermSummaries\":[{\"admissionTerm\":\"\",\"riskClassCount\":{\"inaccurate\":0,\"safe\":0,\"low\":0,\"average\":0,\"high\":0,\"unfeasible\":0,\"notApplicable\":0},\"term\":\"\"}]" +
                "}";
    }

    public static String getMockedStudentStatisticsSummaryResponse() {
        return "{\"courseCode\":\"\",\"curriculumCode\":\"\",\"activesSummary\":null,\"alumniSummary\":null,\"dropoutsSummary\":null,\"glossary\":null}";
    }

    public static String getMockedRetentionSummaryResponse(){
        return "{\"courseCode\":\"\",\"curriculumCode\":\"\",\"studentsRetentionSummary\":null,\"subjectsRetentionSummary\":null,\"glossary\":null}";
    }

    public static String getMockedAlumniStatisticsResponse() {
        return "{\"from\":\"x\",\"to\":\"y\",\"courseCode\":\"\",\"curriculumCode\":\"\",\"alumniPerTermSummaries\":[{\"graduationTerm\":\"x\",\"alumniCount\":10,\"averageGpa\":5.0,\"averageTerms\":4.0,\"averageCost\":1.0,\"term\":\"x\"}]}";
    }

    public static String getMockedStudentCsvResponse() {
        return "{\"students\"" +
                ":[{\"registration\":\"fake-registration\",\"courseCode\":\"x\",\"curriculumCode\":\"x\",\"name\":\"x\",\"gender\":\"x\",\"age\":57,\"maritalStatus\":\"x\"," +
                "\"affirmativePolicy\":\"A0\",\"admissionType\":\"VESTIBULAR\",\"admissionTerm\":\"2007.2\",\"statusStr\":\"Ativo\",\"statusTerm\":\"Current\"," +
                "\"entryGrade\":0.0,\"gpa\":5.68,\"iea\":1.69,\"mc\":7.0,\"mandatoryCredits\":196,\"complementaryCredits\":30,\"electiveCredits\":56," +
                "\"completedTerms\":14,\"attemptedCredits\":0.0,\"institutionalEnrollments\":0,\"mobilityTerms\":0,\"suspendedTerms\":1,\"feasibility\":0.0," +
                "\"successRate\":-1.0,\"averageLoad\":0.0,\"cost\":-1.0,\"pace\":20.142857142857142,\"courseDurationPrediction\":10.0,\"risk\":0.975609756097561," +
                "\"riskClass\":\"SAFE\",\"costClass\":\"NOT_APPLICABLE\"}]}";
    }

    public static String getMockedSubjectEnrollmentSummaryResponse() {
        return "{\"courseCode\":\"\",\"curriculumCode\":\"\",\"mandatory\":null,\"optional\":null,\"elective\":null,\"complementary\":null,\"glossary\":null}";
    }

    public static String getMockedSubjectEnrollmentCsvResponse() {
        return "{\"enrollments\""+
                ":[{\"courseCode\":\"x\",\"curriculumCode\":\"x\",\"subjectCode\":\"x\",\"subjectName\":\"x\",\"term\":\"x\",\"classId\":\"x\","+
                "\"enrollmentsCount\":0,\"succeededCount\":0,\"cancelledCount\":0,\"exemptedCount\":0,\"ongoingCount\":0,\"failedDueToGradeCount\":0,"+
                "\"failedDueToAbsenceCount\":0,"+ "\"suspendedCount\":0}]}";
    }

    public static String getMockedSubjectCsvResponse() {
        return "{\"subjects\""+
                ":[{\"courseCode\":\"x\",\"curriculumCode\":\"x\",\"subjectCode\":\"x\",\"subjectName\":\"x\",\"term\":\"x\","+
                "\"metrics\""+
                ":{\"failedDueToAbsences\":0,\"failedDueToGrade\":0,\"cancelled\":0,\"succeeded\":0,\"ongoing\":0,\"exempted\":0,\"suspended\":0," +
                "\"numberOfClasses\":0,\"totalEnrolled\":0}}]}";
    }

    public static String getMockedStudentsRetentionStatisticsResponse(){
     return "{\"from\":\"x\",\"to\":\"y\",\"courseCode\":\"14112100\",\"curriculumCode\":\"2017\",\"terms\":[{\"admissionTerm\":\"0\",\"metricsSummary\"" +
             ":{\"termsCount\":0.0,\"metrics\":{\"attemptedCredits\":0.0,\"feasibility\":0.0,\"successRate\":0.0,\"averageLoad\":0.0,\"cost\":0.0,\"pace\":0.0," +
             "\"courseDurationPrediction\":0.0,\"risk\":0.0},\"riskClass\":\"SAFE\",\"costClass\":\"INACCURATE\"},\"term\":\"0\"}]}";
    }

    public static String getMockedSubjectsRetentionStatisticsResponse(){
        return "{\"courseCode\":\"14112100\",\"curriculumCode\":\"2017\",\"subjectRetentionSummary\":[{\"from\":\"x\",\"to\":\"y\",\"subjectCode\":\"0\",\"" +
                "subjectName\":\"x\",\"idealTerm\":1,\"retention\":[{\"admissionTerm\":\"0\",\"adequate\":0,\"possible\":0,\"term\":\"0\"}]}]}";
    }

    public static String getMockedPreEnrollmentResponse() {
        return "{\"studentRegistration\":\"fake-registration\",\"enrollments\":[]," +
                "\"term\":4,\"maxCredits\":20,\"maxMandatoryCredits\":20,\"mandatoryCredits\":0,\"maxOptionalCredits\":0," +
                "\"optionalCredits\":0,\"maxComplementaryCredits\":0,\"complementaryCredits\":0," +
                "\"maxElectiveCredits\":0,\"electiveCredits\":0," +
                "\"possibleGraduate\":false}";
    }

    public static String getMockedPreEnrollmentsResponse() {
        return "{\"activesPreEnrollment\":[" + getMockedPreEnrollmentResponse() + "]," +
                "\"subjectDemandSummary\":{\"mandatoryDemand\":[],\"optionalDemand\":[],\"complementaryDemand\":[],\"electiveDemand\":[]}}";
    }

    public static DropoutsStatisticsResponse getDropoutsSummaryResponse() {
        DropoutReasonSummary reasonSummary = new DropoutReasonSummary(0,0,0,
                0,0,0,
                0,0,0,
                0,0);
        DropoutPerTermSummary dropouts = new DropoutPerTermSummary("", 0, reasonSummary, 0, 0);
        return new DropoutsStatisticsResponse(Arrays.asList(dropouts), "", "","x", "y");
    }

    public static Curriculum getCurriculum() {
        return new Curriculum("14102100", "2017", new ArrayList<>(Arrays.asList(16,16,24,24,24,12,8,4,4)), new ArrayList<>(Arrays.asList(0,0,0,0,0,8,8,8,16)), new ArrayList<>(Arrays.asList(4,4,0,0,0,0,4,4,0)), new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,4,4)), new ArrayList<>(Arrays.asList(10,30,52,76,100,122,142,162,184)), 132,
                40, 16, 8, 22, 9, 14, 16, 24, 4,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public static TeachersStatisticsResponse getTeachersStaticticsResponse() {
        List<TeacherStatisticsPerTerm> listTerms = new ArrayList();
        List<TeacherStatistics> list = new ArrayList();
        TeacherStatisticsSummary teacherStatisticsSummary = new TeacherStatisticsSummary(1,1,1,1,1,
                1,1,1,1,1,1);
        TeacherStatisticsPerTerm teacherStatisticsPerTerm = new TeacherStatisticsPerTerm("", teacherStatisticsSummary);
        TeacherStatistics teacherStatistics = new TeacherStatistics("2018.1","2019.1","","xxx","xxx",listTerms);
        list.add(teacherStatistics);
        listTerms.add(teacherStatisticsPerTerm);
        return new TeachersStatisticsResponse("x","x","x","14102100","2017",
                "1980.1","2019.1",list);
    }

    public static TeachersResponse getTeachersResponse() {
        TeacherStatisticsSummary teacherStatisticsSummary = new TeacherStatisticsSummary(1,1,1,1,1,
                1,1,1,1,1,1);

        List<TeacherCSV> listCsv = new ArrayList<>();
        TeacherCSV teacherCSV = new TeacherCSV("111","xxx","14102100","2017","2019.1",teacherStatisticsSummary);
        listCsv.add(teacherCSV);
        return new TeachersResponse(listCsv);
    }

    public static TeachersStatisticsSummaryResponse getTeachersStatisticsSummaryResponse() {
        TermCount termCount1 = new TermCount(0,"2019.1");
        TermCount termCount2 = new TermCount(0, "2020.1");
        Map<String, TeachersStatisticsSummary> map = new HashMap<>();
        TeachersStatisticsSummary teachersStatisticsSummary = new TeachersStatisticsSummary("2018.1","2020.1",0,termCount1,
                termCount2,0,0,0,0);

        map.put("x", teachersStatisticsSummary);
        return new TeachersStatisticsSummaryResponse("", "", map);

    }
}
