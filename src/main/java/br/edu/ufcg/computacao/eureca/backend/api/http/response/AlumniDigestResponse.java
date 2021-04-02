package br.edu.ufcg.computacao.eureca.backend.api.http.response;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;

public class AlumniDigestResponse implements Comparable {
    private String registration;
    private String name;
    private int course;
    private int level;
    private String admissionTerm;
    private String graduationTerm;

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAdmissionTerm() {
        return admissionTerm;
    }

    public void setAdmissionTerm(String admissionTerm) {
        this.admissionTerm = admissionTerm;
    }

    public String getGraduationTerm() {
        return graduationTerm;
    }

    public void setGraduationTerm(String graduationTerm) {
        this.graduationTerm = graduationTerm;
    }

    public AlumniDigestResponse(String registration, String name, int course, int level, String admissionTerm,
                                String graduationTerm) {
        this.registration = registration;
        this.name = name;
        this.course = course;
        this.level = level;
        this.admissionTerm = admissionTerm;
        this.graduationTerm = graduationTerm;
    }

    @Override
    public int compareTo(Object o) {
        AlumniDigestResponse other = (AlumniDigestResponse) o;
        return (new Registration(this.getRegistration())).compareTo((new Registration(other.getRegistration())));
    }
}
