package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class StudentSubject extends EurecaMapValue {
    int absences;
    double grade1;
    double grade2;
    double grade3;
    double grade4;
    double grade5;
    double grade6;
    double grade7;
    double grade8;
    double partial_average;
    double final_exam;
    double final_average;
    int status_id;

    public StudentSubject(int absences, double grade1, double grade2, double grade3, double grade4, double grade5,
                          double grade6, double grade7, double grade8, double partial_average, double final_exam,
                          double final_average, int status_id) {
        this.absences = absences;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.grade4 = grade4;
        this.grade5 = grade5;
        this.grade6 = grade6;
        this.grade7 = grade7;
        this.grade8 = grade8;
        this.partial_average = partial_average;
        this.final_exam = final_exam;
        this.final_average = final_average;
        this.status_id = status_id;
    }

    public StudentSubject() {
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public double getGrade1() {
        return grade1;
    }

    public void setGrade1(double grade1) {
        this.grade1 = grade1;
    }

    public double getGrade2() {
        return grade2;
    }

    public void setGrade2(double grade2) {
        this.grade2 = grade2;
    }

    public double getGrade3() {
        return grade3;
    }

    public void setGrade3(double grade3) {
        this.grade3 = grade3;
    }

    public double getGrade4() {
        return grade4;
    }

    public void setGrade4(double grade4) {
        this.grade4 = grade4;
    }

    public double getGrade5() {
        return grade5;
    }

    public void setGrade5(double grade5) {
        this.grade5 = grade5;
    }

    public double getGrade6() {
        return grade6;
    }

    public void setGrade6(double grade6) {
        this.grade6 = grade6;
    }

    public double getGrade7() {
        return grade7;
    }

    public void setGrade7(double grade7) {
        this.grade7 = grade7;
    }

    public double getGrade8() {
        return grade8;
    }

    public void setGrade8(double grade8) {
        this.grade8 = grade8;
    }

    public double getPartial_average() {
        return partial_average;
    }

    public void setPartial_average(double partial_average) {
        this.partial_average = partial_average;
    }

    public double getFinal_exam() {
        return final_exam;
    }

    public void setFinal_exam(double final_exam) {
        this.final_exam = final_exam;
    }

    public double getFinal_average() {
        return final_average;
    }

    public void setFinal_average(double final_average) {
        this.final_average = final_average;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return absences + ":" + grade1 + ":" + grade2 + ":" + grade3 + ":" + grade4 + ":" + grade5 + ":" + +grade6 +
                ":" + grade7 + ":" + +grade8 + ":" + partial_average + ":" + final_exam + ":" + final_average + ":" +
                status_id;
    }
}
