package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudentTest {

    private Student student;
    private StudentData fakeStudentData;
    private CpfRegistration fakeCpfRegistration;

    @Before
    public void setUp() {
        this.fakeStudentData = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Inativo (GRADUADO 2011.2)",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x", 0,0,0,
                0,0,0,0,
                0,0,0,0,0,
                0,0,0);
        this.fakeCpfRegistration = new CpfRegistration("nationalId", "registration");
        this.student = new Student(fakeCpfRegistration, fakeStudentData);

    }

    // test case: Call the getId method and tests a successfully return.
    @Test
    public void getIdTest() {
        // set up
        CpfRegistration expected = this.fakeCpfRegistration;

        // exercise
        CpfRegistration id = this.student.getId();

        // verify
        assertEquals(expected, id);
    }

    // test case: Call the getStudentData method and tests a successfully return.
    @Test
    public void getStudentDataTest() {
        // set up
        StudentData expected = this.fakeStudentData;

        // exercise
        StudentData studentData = this.student.getStudentData();

        // verify
        assertEquals(expected, studentData);
    }

}
