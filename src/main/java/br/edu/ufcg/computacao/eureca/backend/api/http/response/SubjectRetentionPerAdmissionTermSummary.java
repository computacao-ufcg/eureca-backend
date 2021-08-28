package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.Range;

import java.util.Collection;

public class SubjectRetentionPerAdmissionTermSummary extends Range implements Comparable {
    private String subjectCode;
    private String subjectName;
    private int idealTerm;
    private Collection<SubjectRetentionPerAdmissionTerm> retention;

    public SubjectRetentionPerAdmissionTermSummary(String from, String to, String subjectCode, String subjectName,
                                             int idealTerm, Collection<SubjectRetentionPerAdmissionTerm> retention) {
        super(from, to);
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.idealTerm = idealTerm;
        this.retention = retention;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(int idealTerm) {
        this.idealTerm = idealTerm;
    }

    public Collection<SubjectRetentionPerAdmissionTerm> getRetention() {
        return retention;
    }

    public void setRetention(Collection<SubjectRetentionPerAdmissionTerm> retention) {
        this.retention = retention;
    }

    @Override
    public int compareTo(Object o) {
        SubjectRetentionPerAdmissionTermSummary other = (SubjectRetentionPerAdmissionTermSummary) o;
        return this.getSubjectCode().compareTo(other.getSubjectCode());
    }
}
