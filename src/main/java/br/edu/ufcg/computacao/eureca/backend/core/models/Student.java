package br.edu.ufcg.computacao.eureca.backend.core.models;

public class Student extends EurecaMapValue {
    String nome;
    String ano_nascimento;
    String email;
    int id_genero;
    int id_estado_civil;
    int id_nacionalidade;
    int id_naturalidade;
    int id_cor;

    public Student(String nome, String ano_nascimento, String email, int id_genero, int id_estado_civil,
                   int id_nacionalidade, int id_naturalidade, int id_cor) {
        this.nome = nome;
        this.ano_nascimento = ano_nascimento;
        this.email = email;
        this.id_genero = id_genero;
        this.id_estado_civil = id_estado_civil;
        this.id_nacionalidade = id_nacionalidade;
        this.id_naturalidade = id_naturalidade;
        this.id_cor = id_cor;
    }

    public Student() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno_nascimento() {
        return ano_nascimento;
    }

    public void setAno_nascimento(String ano_nascimento) {
        this.ano_nascimento = ano_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public int getId_estado_civil() {
        return id_estado_civil;
    }

    public void setId_estado_civil(int id_estado_civil) {
        this.id_estado_civil = id_estado_civil;
    }

    public int getId_nacionalidade() {
        return id_nacionalidade;
    }

    public void setId_nacionalidade(int id_nacionalidade) {
        this.id_nacionalidade = id_nacionalidade;
    }

    public int getId_naturalidade() {
        return id_naturalidade;
    }

    public void setId_naturalidade(int id_naturalidade) {
        this.id_naturalidade = id_naturalidade;
    }

    public int getId_cor() {
        return id_cor;
    }

    public void setId_cor(int id_cor) {
        this.id_cor = id_cor;
    }

    @Override
    public String toString() {
        return nome + ":" + ano_nascimento + ":" + email + ":" + id_genero + ":" + id_estado_civil + ":" + id_nacionalidade + ":" +
                id_naturalidade + ":" + id_cor;
    }
}
