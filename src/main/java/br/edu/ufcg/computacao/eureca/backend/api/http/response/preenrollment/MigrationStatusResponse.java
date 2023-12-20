package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import java.util.Collection;

public class MigrationStatusResponse {
    private Collection<MigrationStatus> studentsStatus;

    public MigrationStatusResponse(Collection<MigrationStatus> studentsStatus) {
        this.studentsStatus = studentsStatus;
    }

    public Collection<MigrationStatus> getStudentsStatus() {
        return studentsStatus;
    }

    public void setStudentsStatus(Collection<MigrationStatus> studentsStatus) {
        this.studentsStatus = studentsStatus;
    }

    @Override
    public String toString() {
        return "MigrationStatusResponse{" +
                "studentsStatus=" + studentsStatus +
                '}';
    }
}
