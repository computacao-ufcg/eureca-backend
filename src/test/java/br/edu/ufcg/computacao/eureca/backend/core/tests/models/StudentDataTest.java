package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDataTest {

    private StudentData studentDataAlumnus;
    private StudentData studentDataDropout;
    private StudentData studentDataActive;

    @Before
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
        Assert.assertTrue(this.studentDataActive.isActive());
        Assert.assertEquals("Current", this.studentDataActive.getStatusTerm());
        Assert.assertEquals(StudentStatus.ACTIVE, this.studentDataActive.getStatus());
    }

    @Test
    public void testIsAlumnus() {
        Assert.assertTrue(this.studentDataAlumnus.isAlumnus());
        Assert.assertEquals(StudentStatus.ALUMNUS, this.studentDataAlumnus.getStatus());
    }

    @Test
    public void testIsDropout() {
        Assert.assertTrue(this.studentDataDropout.isDropout());
        Assert.assertEquals(StudentStatus.DROPOUT, this.studentDataDropout.getStatus());
    }

    @Test
    public void testGetCompletedCredits() {
        this.studentDataActive.setMandatoryCredits(10);
        this.studentDataActive.setElectiveCredits(10);
        this.studentDataActive.setComplementaryCredits(5);
        Assert.assertEquals(25, this.studentDataActive.getCompletedCredits());
    }
}
