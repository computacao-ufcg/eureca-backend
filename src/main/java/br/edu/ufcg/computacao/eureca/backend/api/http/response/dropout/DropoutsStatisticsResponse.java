package br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class DropoutsStatisticsResponse extends Range {
    private String courseCode;
    private String curriculumCode;
    private Collection<DropoutPerTermSummary> dropoutPerTermSummaries;

    public DropoutsStatisticsResponse(Collection<DropoutPerTermSummary> dropoutPerTermSummaries, String courseCode, String curriculumCode, String from, String to) {
        super(from, to);
        this.dropoutPerTermSummaries = dropoutPerTermSummaries;
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
    }

    public Collection<DropoutPerTermSummary> getDropoutPerTermSummaries() {
        return dropoutPerTermSummaries;
    }

    public void setDropoutPerTermSummaries(Collection<DropoutPerTermSummary> dropoutPerTermSummaries) {
        this.dropoutPerTermSummaries = dropoutPerTermSummaries;
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
