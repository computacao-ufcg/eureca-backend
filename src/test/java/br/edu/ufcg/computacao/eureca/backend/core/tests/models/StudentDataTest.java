package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.models.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentDataTest {

    private StudentData studentData;

    @BeforeEach
    public void setUp() {
//        this.studentData = new StudentData("TEST NAME", "18/08/2001", "test@test.com", "Masculino", "Solteiro(a)",
//                "Brasileiro(a)", "Campina Grande", "Pardo(a)", "Ativo",
//                "2016.1", "affirmativePolicy", "Test School",
//                "2015", "2016", 0,
//                0, 0, 0, 0,
//                0, 0, 0, 0, 0, 0,
//                0, 0, 0, 0);
        this.studentData = new StudentData();
    }

    @Test
    public void testIsActive() {
        this.studentData.setStatusStr("Ativo");
        Assertions.assertTrue(this.studentData.isActive());
        Assertions.assertEquals("Current", this.studentData.getStatusTerm());
        Assertions.assertEquals(StudentStatus.ACTIVE, this.studentData.getStatus());
    }

    @Test
    public void testIsAlumnus() {
        this.studentData.setStatusStr("CONCLUIDO - NAO COLOU GRAU");
        Assertions.assertTrue(this.studentData.isAlumnus());
        Assertions.assertEquals(StudentStatus.ALUMNUS, this.studentData.getStatus());
    }

    @Test
    public void testIsDropout() {
        this.studentData.setStatusStr("CANCELADO 3 REPROV MESMA DISCIPLINA");
        Assertions.assertTrue(this.studentData.isDropout());
        Assertions.assertEquals(StudentStatus.DROPOUT, this.studentData.getStatus());
    }

    @Test
    public void testGetCompletedCredits() {
        this.studentData.setMandatoryCredits(10);
        this.studentData.setElectiveCredits(10);
        this.studentData.setComplementaryCredits(10); // case 1: complementary > 8
        Assertions.assertEquals(28, this.studentData.getCompletedCredits());

        this.studentData.setComplementaryCredits(5); // case 2: complementary < 8
        Assertions.assertEquals(25, this.studentData.getCompletedCredits());
    }

    @Test
    public void testGetStatusIndex() {
    }
}
