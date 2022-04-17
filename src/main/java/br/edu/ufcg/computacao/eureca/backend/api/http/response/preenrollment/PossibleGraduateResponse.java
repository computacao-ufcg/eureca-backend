package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Collection;
import java.util.TreeSet;

public class PossibleGraduateResponse {

    private Collection<String> possibleGraduateList;

    public PossibleGraduateResponse(PreEnrollments preEnrollments) {
        this.possibleGraduateList = new TreeSet<>();
        preEnrollments.getActivesPreEnrollment().forEach(student -> {
            if (student.isPossibleGraduate()) {
                possibleGraduateList.add(student.getStudentRegistration());
            }
        });
    }

    public Collection<String> getPossibleGraduateList() {
        return possibleGraduateList;
    }

    @Override
    public String toString() {
        return "PossibleGraduateResponse{" +
                "possibleGraduateList=" + possibleGraduateList +
                '}';
    }

    public void setPossibleGraduateList(Collection<String> possibleGraduateList) {
        this.possibleGraduateList = possibleGraduateList;
    }
}
