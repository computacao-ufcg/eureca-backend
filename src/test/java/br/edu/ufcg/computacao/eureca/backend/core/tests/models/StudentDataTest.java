package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.models.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentDataTest {

    private StudentData studentDataAlumnus;
    private StudentData studentDataDropout;
    private StudentData studentDataActive;

    @BeforeEach
    public void setUp() {
        this.studentDataAlumnus = new StudentData();
        this.studentDataAlumnus.setStatusStr("Inativo (GRADUADO 2004.1)");

        this.studentDataDropout = new StudentData();
        this.studentDataDropout.setStatusStr("Inativo (CANCELADO 3 REPROV MESMA DISCIPLINA 2014.2)");

        this.studentDataActive = new StudentData();
        this.studentDataActive.setStatusStr("Ativo");
    }

    @Test
    public void testIsActive() {
        Assertions.assertTrue(this.studentDataActive.isActive());
        Assertions.assertEquals("Current", this.studentDataActive.getStatusTerm());
        Assertions.assertEquals(StudentStatus.ACTIVE, this.studentDataActive.getStatus());
    }

    @Test
    public void testIsAlumnus() {
        Assertions.assertTrue(this.studentDataAlumnus.isAlumnus());
        Assertions.assertEquals(StudentStatus.ALUMNUS, this.studentDataAlumnus.getStatus());
    }

    @Test
    public void testIsDropout() {
        Assertions.assertTrue(this.studentDataDropout.isDropout());
        Assertions.assertEquals(StudentStatus.DROPOUT, this.studentDataDropout.getStatus());
    }

    @Test
    public void testGetCompletedCredits() {
        this.studentDataActive.setMandatoryCredits(10);
        this.studentDataActive.setElectiveCredits(10);
        this.studentDataActive.setComplementaryCredits(5); // case 1: complementary < 8
        Assertions.assertEquals(25, this.studentDataActive.getCompletedCredits());
    }

    @Test
    public void testGetCompletedCredits_WhenComplementaryMoreThan8() {
        this.studentDataActive.setMandatoryCredits(10);
        this.studentDataActive.setElectiveCredits(10);
        this.studentDataActive.setComplementaryCredits(10); // case 2: complementary > 8
        Assertions.assertEquals(28, this.studentDataActive.getCompletedCredits());
    }

    @Test
    public void testGetStatusIndex() {
        Assertions.assertEquals(0, this.studentDataDropout.getStatusIndex());
        Assertions.assertEquals(11, this.studentDataActive.getStatusIndex());
    }
}
