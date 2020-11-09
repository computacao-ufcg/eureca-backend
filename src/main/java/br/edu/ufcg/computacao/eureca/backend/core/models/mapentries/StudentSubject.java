package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class StudentSubject extends EurecaMapValue {
    int num_faltas;
    double nota1;
    double nota2;
    double nota3;
    double nota4;
    double nota5;
    double nota6;
    double nota7;
    double nota8;
    double media_parcial;
    double prova_final;
    double media_final;
    int id_situacao;

    public StudentSubject(int num_faltas, double nota1, double nota2, double nota3, double nota4, double nota5,
                          double nota6, double nota7, double nota8, double media_parcial, double prova_final,
                          double media_final, int id_situacao) {
        this.num_faltas = num_faltas;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.nota5 = nota5;
        this.nota6 = nota6;
        this.nota7 = nota7;
        this.nota8 = nota8;
        this.media_parcial = media_parcial;
        this.prova_final = prova_final;
        this.media_final = media_final;
        this.id_situacao = id_situacao;
    }

    public StudentSubject() {
    }

    public int getNum_faltas() {
        return num_faltas;
    }

    public void setNum_faltas(int num_faltas) {
        this.num_faltas = num_faltas;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getNota4() {
        return nota4;
    }

    public void setNota4(double nota4) {
        this.nota4 = nota4;
    }

    public double getNota5() {
        return nota5;
    }

    public void setNota5(double nota5) {
        this.nota5 = nota5;
    }

    public double getNota6() {
        return nota6;
    }

    public void setNota6(double nota6) {
        this.nota6 = nota6;
    }

    public double getNota7() {
        return nota7;
    }

    public void setNota7(double nota7) {
        this.nota7 = nota7;
    }

    public double getNota8() {
        return nota8;
    }

    public void setNota8(double nota8) {
        this.nota8 = nota8;
    }

    public double getMedia_parcial() {
        return media_parcial;
    }

    public void setMedia_parcial(double media_parcial) {
        this.media_parcial = media_parcial;
    }

    public double getProva_final() {
        return prova_final;
    }

    public void setProva_final(double prova_final) {
        this.prova_final = prova_final;
    }

    public double getMedia_final() {
        return media_final;
    }

    public void setMedia_final(double media_final) {
        this.media_final = media_final;
    }

    public int getId_situacao() {
        return id_situacao;
    }

    public void setId_situacao(int id_situacao) {
        this.id_situacao = id_situacao;
    }

    @Override
    public String toString() {
        return num_faltas + ":" + nota1 + ":" + nota2 + ":" + nota3 + ":" + nota4 + ":" + nota5 + ":" + +nota6 +
                ":" + nota7 + ":" + +nota8 + ":" + media_parcial + ":" + prova_final + ":" + media_final + ":" + id_situacao;
    }
}
