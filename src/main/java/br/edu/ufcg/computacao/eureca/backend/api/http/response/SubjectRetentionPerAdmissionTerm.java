package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectRetentionPerAdmissionTerm implements Comparable, SummaryPerTerm {
    private String admissionTerm;
    private int adequate;
    private int possible;

    public SubjectRetentionPerAdmissionTerm(String admissionTerm, int adequate, int possible) {
        this.admissionTerm = admissionTerm;
        this.adequate = adequate;
        this.possible = possible;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public int getAdequate() {
        return adequate;
    }

    public void setAdequate(int adequate) {
        this.adequate = adequate;
    }

    public int getPossible() {
        return possible;
    }

    public void setPossible(int possible) {
        this.possible = possible;
    }

    @Override
    public String getTerm() {
        return this.getAdmissionTerm();
    }

    @Override
    public int compareTo(Object o) {
        SubjectRetentionPerAdmissionTerm other = (SubjectRetentionPerAdmissionTerm) o;
        return this.getAdmissionTerm().compareTo(other.getAdmissionTerm());
    }

    public void incrementAdequate() {
        this.adequate++;
    }

    public void incrementPossible() {
        this.possible++;
    }
}
