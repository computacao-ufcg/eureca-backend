package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import java.util.Collection;

public class SubjectsRetentionStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetentionSummary;

    public SubjectsRetentionStatisticsResponse(Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetentionSummary, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.subjectRetentionSummary = subjectRetentionSummary;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<SubjectRetentionPerAdmissionTermSummary> getSubjectRetentionSummary() {
        return subjectRetentionSummary;
    }

    public void setSubjectRetentionSummary(Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetentionSummary) {
        this.subjectRetentionSummary = subjectRetentionSummary;
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
}
