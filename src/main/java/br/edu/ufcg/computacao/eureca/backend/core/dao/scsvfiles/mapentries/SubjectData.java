package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SubjectData implements EurecaMapValue {
    private String academicUnitId;
    private String type;
    private int credits;
    private int hours;
    private String name;
    private String equivalentCodes;
    private int idealTerm;
    private String preRequirements;
    private String coRequirements;

    public SubjectData(String academicUnitId, String type, int credits, int hours, String name, String equivalentCodes,
                       int idealTerm, String preRequirements, String coRequirements) {
        this.academicUnitId = academicUnitId;
        this.type = type;
        this.credits = credits;
        this.hours = hours;
        this.name = name;
        this.equivalentCodes = equivalentCodes;
        this.idealTerm = idealTerm;
        this.preRequirements = preRequirements;
        this.coRequirements = coRequirements;
    }

    public SubjectData() {
    }

    public String getAcademicUnitId() {
        return academicUnitId;
    }

    public void setAcademicUnitId(String academicUnitId) {
        this.academicUnitId = academicUnitId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquivalentCodes() {
        return equivalentCodes;
    }

    public void setEquivalentCodes(String equivalentCodes) {
        this.equivalentCodes = equivalentCodes;
    }

    public int getIdealTerm() {
        return idealTerm;
    }

    public void setIdealTerm(int idealTerm) {
        this.idealTerm = idealTerm;
    }

    public String getPreRequirements() {
        return preRequirements;
    }

    public void setPreRequirements(String preRequirements) {
        this.preRequirements = preRequirements;
    }

    public Collection<String> getEquivalentCodesList() {
        return extractStrList(getEquivalentCodes());
    }

    public Collection<String> getPreRequirementsList() {
        return extractStrList(getPreRequirements());
    }

    public String getCoRequirements() {
        return coRequirements;
    }

    public void setCoRequirements(String coRequirements) {
        this.coRequirements = coRequirements;
    }

    public Collection<String> getCoRequirementsList() {
        return extractStrList(getCoRequirements());
    }

    public Subject createSubject(SubjectKey key) {
        return new Subject(key.getCourseCode(), key.getCurriculumCode(), key.getSubjectCode(), getAcademicUnitId(),
                getType(), getCredits(), getHours(), getName(), extractStrList(getEquivalentCodes()), getIdealTerm(),
                extractStrList(getPreRequirements()), extractStrList(getCoRequirements()));
    }

    private Collection<String> extractStrList(String subjectsStr) {
        ArrayList<String> ret = new ArrayList<>();
        if (subjectsStr.equals("")) return ret;
        String[] creditsArray = subjectsStr.split(",");
        return Arrays.asList(creditsArray);
    }

    @Override
    public String toString() {
        return "SubjectData{" +
                "academicUnitId='" + academicUnitId + '\'' +
                ", type='" + type + '\'' +
                ", credits=" + credits +
                ", hours=" + hours +
                ", name='" + name + '\'' +
                ", equivalentCodes='" + equivalentCodes + '\'' +
                ", idealTerm=" + idealTerm +
                ", preRequirements='" + preRequirements + '\'' +
                ", coRequirements='" + coRequirements + '\'' +
                '}';
    }
}
