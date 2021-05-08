package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.models.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import br.edu.ufcg.computacao.eureca.backend.core.models.StudentData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MetricsCalculator.class, DataAccessFacadeHolder.class})
public class StudentTest {

    private Student student;

    @Before
    public void setUp() {
        this.student = createNewStudent("nationalID","registration1", 1980,120,840,
                58,450,26,5.68,
                7,1.69,14,1,0,
                0,0,0);
    }

    // test case: Calls the getId method and tests a successfully return.
    @Test
    public void getIdTest() {
        // set up
        CpfRegistration expected = new CpfRegistration("nationalID","registration1");

        // exercise
        CpfRegistration result = this.student.getId();

        // verify
        Assert.assertEquals(expected, result);
    }

    // test case: Calls the getStudentData method and tests a successfully return.
    @Test
    public void getStudentDataTest() {
        // set up
        StudentData expected = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Ativo",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x",1980,120,840,
                58,450,26,5.68,
                7,1.69,14,1,0,
                0,0,0);

        // exercise
        StudentData result = this.student.getStudentData();

        // verify
        Assert.assertEquals(expected, result);

    }

    // test case: Calls the getRiskClass method and tests a successfully return.
    @Test
    public void getRiskClassTest() {
        // set up
        RiskClass riskClassExpectedStudent = UNFEASIBLE;

        // exercise
        RiskClass resultStudent = this.student.computeRiskClass();

        // verify
        Assert.assertEquals(riskClassExpectedStudent, resultStudent);

    }

    // test case: Calls the getRiskClass method and tests a successfully return.
    @Test
    public void getRiskClass2Test() {
        // set up
        RiskClass riskClassExpectedStudent = LOW;

        Student student3 = createNewStudent("nationalID","registration2",1980,
                120,840,
                58,450,26,5.68,
                7,1.69,10,2,0,
                0,4,0);

        // exercise
        RiskClass result = student3.computeRiskClass();

        // verify
        Assert.assertEquals(riskClassExpectedStudent, result);
    }

    private Student createNewStudent(String nationalId, String registration, int mandatoryHours, int mandatoryCredits, int electiveHours, int electiveCredits,
                                  int complementaryHours, int complementaryCredits, double gpa, double mc,
                                  double iea, int completedTerms, int suspendedTerms, int institutionalTerms,
                                  int mobilityTerms, int enrolledCredits, double admissionGrade) {

        CpfRegistration cpfRegistration = new CpfRegistration(nationalId,registration);
        StudentData studentData = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Ativo",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x", mandatoryHours, mandatoryCredits, electiveHours, electiveCredits, complementaryHours, complementaryCredits,
                gpa, mc, iea, completedTerms, suspendedTerms, institutionalTerms, mobilityTerms, enrolledCredits, admissionGrade);
        return new Student(cpfRegistration, studentData);
    }
}
