package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated;

import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaMapValue;

public class Lecture extends EurecaMapValue {
    int lecture;

    public int getLecture() {
        return lecture;
    }

    public void setLecture(int lecture) {
        this.lecture = lecture;
    }

    public Lecture(int lecture) {
        this.lecture = lecture;
    }

    public Lecture() {}

    @Override
    public String toString() {
        return Integer.toString(this.lecture);
    }
}
