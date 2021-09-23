package br.edu.ufcg.computacao.eureca.backend.api.http.response.active;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class ActivesStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<ActivesPerTermSummary> activesPerTermSummaries;

    public ActivesStatisticsResponse(Collection<ActivesPerTermSummary> activesPerTermSummaries, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.activesPerTermSummaries = activesPerTermSummaries;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<ActivesPerTermSummary> getActivesPerTermSummaries() {
        return activesPerTermSummaries;
    }

    public void setActivesPerTermSummaries(Collection<ActivesPerTermSummary> activesPerTermSummaries) {
        this.activesPerTermSummaries = activesPerTermSummaries;
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
