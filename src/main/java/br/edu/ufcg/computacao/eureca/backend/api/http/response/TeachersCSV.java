package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class TeachersCSV implements Comparable {
    private String teacherId;
    private String teacherName;
    private double averageSucceeded;
    private double averageSuspended;
    private double averageFailDueToGrade;
    private double averageFailDueToAbsences;
    private int subjectsCount;
    private int classesCount;
    private int totalEnrollments;
    private double averageEnrollmentsPerClass;

    public TeachersCSV(String teacherId, String teacherName, double averageSucceeded, double averageSuspended,
                       double averageFailDueToGrade, double averageFailDueToAbsences, int subjectsCount,
                       int classesCount, int totalEnrollments, double averageEnrollmentsPerClass) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.averageSucceeded = averageSucceeded;
        this.averageSuspended = averageSuspended;
        this.averageFailDueToGrade = averageFailDueToGrade;
        this.averageFailDueToAbsences = averageFailDueToAbsences;
        this.subjectsCount = subjectsCount;
        this.classesCount = classesCount;
        this.totalEnrollments = totalEnrollments;
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
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

    public double getAverageSucceeded() {
        return averageSucceeded;
    }

    public void setAverageSucceeded(double averageSucceeded) {
        this.averageSucceeded = averageSucceeded;
    }

    public double getAverageSuspended() {
        return averageSuspended;
    }

    public void setAverageSuspended(double averageSuspended) {
        this.averageSuspended = averageSuspended;
    }

    public double getAverageFailDueToGrade() {
        return averageFailDueToGrade;
    }

    public void setAverageFailDueToGrade(double averageFailDueToGrade) {
        this.averageFailDueToGrade = averageFailDueToGrade;
    }

    public double getAverageFailDueToAbsences() {
        return averageFailDueToAbsences;
    }

    public void setAverageFailDueToAbsences(double averageFailDueToAbsences) {
        this.averageFailDueToAbsences = averageFailDueToAbsences;
    }

    public int getSubjectsCount() {
        return subjectsCount;
    }

    public void setSubjectsCount(int subjectsCount) {
        this.subjectsCount = subjectsCount;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(int classesCount) {
        this.classesCount = classesCount;
    }

    public int getTotalEnrollments() {
        return totalEnrollments;
    }

    public void setTotalEnrollments(int totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
    }

    public double getAverageEnrollmentsPerClass() {
        return averageEnrollmentsPerClass;
    }

    public void setAverageEnrollmentsPerClass(double averageEnrollmentsPerClass) {
        this.averageEnrollmentsPerClass = averageEnrollmentsPerClass;
    }

    @Override
    public String toString() {
        return "TeachersCSV{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", averageSucceeded=" + averageSucceeded +
                ", averageSuspended=" + averageSuspended +
                ", averageFailDueToGrade=" + averageFailDueToGrade +
                ", averageFailDueToAbsences=" + averageFailDueToAbsences +
                ", subjectsCount=" + subjectsCount +
                ", classesCount=" + classesCount +
                ", totalEnrollments=" + totalEnrollments +
                ", averageEnrollmentsPerClass=" + averageEnrollmentsPerClass +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
