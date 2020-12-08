package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class Enrolment extends EurecaMapValue {
    int subject_id;
    int enrollment_id;
    String term;
    int timetable_id;
    int room_id;

    public Enrolment(int subject_id, int enrollment_id, String term, int timetable_id, int room_id) {
        this.subject_id = subject_id;
        this.enrollment_id = enrollment_id;
        this.term = term;
        this.timetable_id = timetable_id;
        this.room_id = room_id;
    }

    public Enrolment() {}

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getTimetable_id() {
        return timetable_id;
    }

    public void setTimetable_id(int timetable_id) {
        this.timetable_id = timetable_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return this.subject_id + ":" + this.enrollment_id + ":" + this.term + ":" + this.timetable_id + ":" + this.room_id;
    }
}
