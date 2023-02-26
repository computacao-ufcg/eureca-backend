package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "MUNICIPIOS", schema = "SCA")
public class ScaoPlaceOfBirth {

    @Id
    @Column(name="MUN_CODIGO")
    private String code;

    @Column(name="MUN_DESCRICAO")
    private String description;

    @Column(name="MUN_UF")
    private String county;

    public ScaoPlaceOfBirth(){
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaoPlaceOfBirth that = (ScaoPlaceOfBirth) o;
        return Objects.equals(code, that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
