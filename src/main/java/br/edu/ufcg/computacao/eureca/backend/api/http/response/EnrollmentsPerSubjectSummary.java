package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class EnrollmentsPerSubjectSummary implements Comparable {
    private String subjectCode;
    private int enrollmentsCount;
    private int classesCount;
    private double averageEnrollmentsPerClass;

    public EnrollmentsPerSubjectSummary(String subjectCode, int enrollmentsCount, int classesCount, double averageEnrollmentsPerClass) {
        this.subjectCode = subjectCode;
        this.enrollmentsCount = enrollmentsCount;
        this.classesCount = classesCount;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getEnrollmentsCount() {
        return enrollmentsCount;
    }

    public void setEnrollmentsCount(int enrollmentsCount) {
        this.enrollmentsCount = enrollmentsCount;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(int classesCount) {
        this.classesCount = classesCount;
    }

    public double getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public void setAverageEnrollmentsPerClass(double averageEnrollmentsPerClass) {
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    @Override
    public int compareTo(Object o) {
        EnrollmentsPerSubjectSummary other = (EnrollmentsPerSubjectSummary) o;
        return this.getSubjectCode().compareTo(other.getSubjectCode());
    }

    @Override
    public String toString() {
        return "EnrollmentsPerSubjectSummary{" +
                "subjectCode='" + subjectCode + '\'' +
                ", enrollmentsCount=" + enrollmentsCount +
                ", classesCount=" + classesCount +
                ", averageEnrollmentsPerClass=" + averageEnrollmentsPerClass +
                '}';
    }
}
