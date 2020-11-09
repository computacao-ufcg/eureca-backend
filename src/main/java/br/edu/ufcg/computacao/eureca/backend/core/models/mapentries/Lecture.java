package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class Lecture extends EurecaMapValue {
    int num_aula;

    public int getNum_aula() {
        return num_aula;
    }

    public void setNum_aula(int num_aula) {
        this.num_aula = num_aula;
    }

    public Lecture(int num_aula) {
        this.num_aula = num_aula;
    }

    public Lecture() {}

    @Override
    public String toString() {
        return Integer.toString(this.num_aula);
    }
}
