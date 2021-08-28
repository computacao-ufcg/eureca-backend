package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class TeacherStatistics extends Range implements Comparable {
    private String teacherId;
    private String teacherName;
    Collection<TeacherStatisticsPerTerm> terms;

    public TeacherStatistics(String from, String to, String teacherId, String teacherName,
                             Collection<TeacherStatisticsPerTerm> terms) {
        super(from, to);
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.terms = terms;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Collection<TeacherStatisticsPerTerm> getTerms() {
        return terms;
    }

    public void setTerms(Collection<TeacherStatisticsPerTerm> terms) {
        this.terms = terms;
    }

    @Override
    public int compareTo(Object o) {
        TeacherStatistics other = (TeacherStatistics) o;
        return this.getTeacherId().compareTo(other.getTeacherId());
    }
}
