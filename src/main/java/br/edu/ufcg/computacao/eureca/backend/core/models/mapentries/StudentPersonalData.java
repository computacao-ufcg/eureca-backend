package br.edu.ufcg.computacao.eureca.backend.core.models.mapentries;

public class StudentPersonalData extends EurecaMapValue {
    String name;
    String birth_year;
    String email;
    int gender_id;
    int marital_status_id;
    int nationality_id;
    int place_of_birth_id;
    int race_id;

    public StudentPersonalData(String name, String birth_year, String email, int gender_id, int marital_status_id,
                               int nationality_id, int place_of_birth_id, int race_id) {
        this.name = name;
        this.birth_year = birth_year;
        this.email = email;
        this.gender_id = gender_id;
        this.marital_status_id = marital_status_id;
        this.nationality_id = nationality_id;
        this.place_of_birth_id = place_of_birth_id;
        this.race_id = race_id;
    }

    public StudentPersonalData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public int getMarital_status_id() {
        return marital_status_id;
    }

    public void setMarital_status_id(int marital_status_id) {
        this.marital_status_id = marital_status_id;
    }

    public int getNationality_id() {
        return nationality_id;
    }

    public void setNationality_id(int nationality_id) {
        this.nationality_id = nationality_id;
    }

    public int getPlace_of_birth_id() {
        return place_of_birth_id;
    }

    public void setPlace_of_birth_id(int place_of_birth_id) {
        this.place_of_birth_id = place_of_birth_id;
    }

    public int getRace_id() {
        return race_id;
    }

    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    @Override
    public String toString() {
        return name + ":" + birth_year + ":" + email + ":" + gender_id + ":" + marital_status_id + ":" + nationality_id + ":" +
                place_of_birth_id + ":" + race_id;
    }
}
