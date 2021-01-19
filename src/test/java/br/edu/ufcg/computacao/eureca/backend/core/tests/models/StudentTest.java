package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudentTest {

    // instance of a student.
    private Student student;

    // instance of a StudentData object.
    private StudentData fakeStudentData;

    // instance of a CpfRegistration.
    private CpfRegistration fakeCpfRegistration;

    // setup: creation of a base object of the type Student that will be used in the tests.
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

    // test case: check if the National Id is correct.
    @Test
    public void getNationalIdTest() {
        String expectedNationalId = "nationalId";
        assertEquals(fakeCpfRegistration.getNationalId(), expectedNationalId);
    }

    // test case: changes the National Id of the Student instance.
    @Test
    public void setNationalIdTest() {
        String expectedNationalId = "newNationalId";
        fakeCpfRegistration.setNationalId("newNationalId");
        assertEquals(fakeCpfRegistration.getNationalId(), expectedNationalId);
    }

    // test case: check if the Registration is correct.
    @Test
    public void getRegistrationTest() {
        String expectedRegistration = "registration";
        assertEquals(fakeCpfRegistration.getRegistration(), expectedRegistration);
    }

    // test case: changes the Registration of the Student instance.
    @Test
    public void setRegistrationTest() {
        String expectedRegistration = "newRegistration";
        fakeCpfRegistration.setRegistration("newRegistration");
        assertEquals(fakeCpfRegistration.getRegistration(), expectedRegistration);
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
