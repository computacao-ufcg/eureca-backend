package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Objects;

public class Enrollment implements Comparable {
    private String subjectName;
    private String classCode;

    public Enrollment(String subjectName, String classCode) {
        this.subjectName = subjectName;
        this.classCode = classCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return getSubjectName().equals(that.getSubjectName()) &&
                getClassCode().equals(that.getClassCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectName(), getClassCode());
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "subjectName='" + subjectName + '\'' +
                ", classCode='" + classCode + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Enrollment other = (Enrollment) o;
        return this.subjectName.compareTo(other.getSubjectName());
    }
}
