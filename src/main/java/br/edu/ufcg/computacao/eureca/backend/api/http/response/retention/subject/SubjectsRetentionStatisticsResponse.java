package br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject;

import java.util.Collection;

public class SubjectsRetentionStatisticsResponse {
    private String courseCode;
    private String curriculumCode;
    private Collection<SubjectRetentionPerAdmissionTermSummary> subjectRetentionSummary;

    public SubjectsRetentionStatisticsResponse(Collection<SubjectRetentionPerAdmissionTermSummary>
                                           subjectRetentionSummary, String courseCode, String curriculumCode) {
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
