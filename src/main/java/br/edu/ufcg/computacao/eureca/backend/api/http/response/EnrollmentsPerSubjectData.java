package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.models.ClassEnrollments;

import java.util.Map;

public class EnrollmentsPerSubjectData implements Comparable {
    private String subjectCode;
    private String subjectName;
    private Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm;

    public EnrollmentsPerSubjectData(String subjectCode, String subjectName, Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.enrollmentsPerTerm = enrollmentsPerTerm;
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

    public Map<String, Map<String, ClassEnrollments>> getEnrollmentsPerTerm() {
        return enrollmentsPerTerm;
    }

    public void setEnrollmentsPerTerm(Map<String, Map<String, ClassEnrollments>> enrollmentsPerTerm) {
        this.enrollmentsPerTerm = enrollmentsPerTerm;
    }

    @Override
    public int compareTo(Object o) {
        EnrollmentsPerSubjectData other = (EnrollmentsPerSubjectData) o;
        return this.getSubjectCode().compareTo(other.getSubjectCode());
    }
}
