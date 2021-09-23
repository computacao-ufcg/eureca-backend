package br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment;

import br.edu.ufcg.computacao.eureca.backend.constants.EnrollmentsGlossaryFields;

public class EnrollmentsStatisticsSummaryResponse {
    private String courseCode;
    private String curriculumCode;
    private EnrollmentsSummary mandatory;
    private EnrollmentsSummary optional;
    private EnrollmentsSummary elective;
    private EnrollmentsSummary complementary;
    private EnrollmentsGlossaryFields glossary;

    public EnrollmentsStatisticsSummaryResponse(String courseCode, String curriculumCode,
                                                EnrollmentsSummary mandatory, EnrollmentsSummary optional,
                                                EnrollmentsSummary elective, EnrollmentsSummary complementary) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.mandatory = mandatory;
        this.optional = optional;
        this.elective = elective;
        this.complementary = complementary;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public EnrollmentsSummary getMandatory() {
        return mandatory;
    }

    public void setMandatory(EnrollmentsSummary mandatory) {
        this.mandatory = mandatory;
    }

    public EnrollmentsSummary getOptional() {
        return optional;
    }

    public void setOptional(EnrollmentsSummary optional) {
        this.optional = optional;
    }

    public EnrollmentsSummary getElective() {
        return elective;
    }

    public void setElective(EnrollmentsSummary elective) {
        this.elective = elective;
    }

    public EnrollmentsSummary getComplementary() {
        return complementary;
    }

    public void setComplementary(EnrollmentsSummary complementary) {
        this.complementary = complementary;
    }

    public EnrollmentsGlossaryFields getGlossary() {
        return glossary;
    }

    public void setGlossary(EnrollmentsGlossaryFields glossary) {
        this.glossary = glossary;
    }

    @Override
    public String toString() {
        return "EnrollmentsStatisticsSummaryResponse{" +
                "courseCode='" + courseCode + '\'' +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", mandatory=" + mandatory +
                ", optional=" + optional +
                ", elective=" + elective +
                ", complementary=" + complementary +
                ", glossary=" + glossary +
                '}';
    }
}
