package br.edu.ufcg.computacao.eureca.backend.api.http.response.alumni;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class AlumniStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<AlumniPerTermSummary> alumniPerTermSummaries;

    public AlumniStatisticsResponse(Collection<AlumniPerTermSummary> alumniPerTermSummaries, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.alumniPerTermSummaries = alumniPerTermSummaries;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<AlumniPerTermSummary> getAlumniPerTermSummaries() {
        return alumniPerTermSummaries;
    }

    public void setAlumniPerTermSummaries(Collection<AlumniPerTermSummary> alumniPerTermSummaries) {
        this.alumniPerTermSummaries = alumniPerTermSummaries;
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
