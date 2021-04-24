package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.Metrics;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass.LATE;
import static br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass.NOT_APPLICABLE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MetricsCalculator.class, DataAccessFacadeHolder.class})
public class StudentTest {

    private MetricsCalculator metricsCalculator;
    private Student student;

    @Before
    public void setUp() {
        this.student = createNewStudent("nationalID","registration1", 0,120,0,
                58,0,26,5.68,
                7,1.69,14,1,0,
                0,0,0);
        mockDataAccessFacadeHolder();
        this.metricsCalculator = MetricsCalculator.getInstance();
    }

    // test case: Call the getId method and tests a successfully return.
    @Test
    public void getIdTest() {
        CpfRegistration expected = new CpfRegistration("nationalID","registration1");

        CpfRegistration result = this.student.getId();

        Assert.assertEquals(expected, result);
    }

    // test case: Call the getStudentData method and tests a successfully return.
    @Test
    public void getStudentDataTest() {
        StudentData expected = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Ativo",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x",0,120,0,
                58,0,26,5.68,
                7,1.69,14,1,0,
                0,0,0);

        StudentData result = this.student.getStudentData();
        Assert.assertEquals(expected, result);

    }

    // test case: Call the getRiskClass method and tests a successfully return.
    @Test
    public void getRiskClassTest() {
        //        mockMetricsCalculator();
        this.metricsCalculator.computeMetrics(this.student);
        RiskClass riskClassExpectedStudent = NOT_APPLICABLE;

        RiskClass resultStudent = this.student.getRiskClass();

        Assert.assertEquals(riskClassExpectedStudent, resultStudent);
    }

    @Test
    public void compareToTest() {
        Student student2 = createNewStudent("nationalID","registration2", 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0);
        Assert.assertEquals(0,this.student.compareTo(student2));
    }

//    private void mockMetricsCalculator() {
//        Metrics metrics = new Metrics(0,0,0,0,0,0,0,0);
//        MetricsCalculator metricsCalculator = mock(MetricsCalculator.class);
//        Mockito.when(metricsCalculator.computeMetrics(this.student)).thenReturn(metrics);
//        PowerMockito.mockStatic(MetricsCalculator.class);
//        BDDMockito.given(MetricsCalculator.getInstance()).willReturn(metricsCalculator);
//    }

    public void mockDataAccessFacadeHolder() {
        DataAccessFacadeHolder dataAccessFacadeHolder = DataAccessFacadeHolder.getInstance();
        DataAccessFacade dataAccessFacade = mock(DataAccessFacade.class);
        dataAccessFacadeHolder.setDataAccessFacade(dataAccessFacade);
        PowerMockito.mockStatic(DataAccessFacadeHolder.class);
        BDDMockito.given(DataAccessFacadeHolder.getInstance()).willReturn(dataAccessFacadeHolder);
    }

    private Student createNewStudent(String nationalId, String registration, int mandatoryHours, int mandatoryCredits, int electiveHours, int electiveCredits,
                                  int complementaryHours, int complementaryCredits, double gpa, double mc,
                                  double iea, int completedTerms, int suspendedTerms, int institutionalTerms,
                                  int mobilityTerms, int enrolledCredits, double admissionGrade) {

        CpfRegistration cpfRegistration = new CpfRegistration(nationalId, registration);
        StudentData studentData = new StudentData("x", "x", "x", "x", "x",
                "x", "x", "x", "Ativo",
                "VESTIBULAR 2007.2", "x", "x", "x",
                "x", mandatoryHours, mandatoryCredits, electiveHours, electiveCredits, complementaryHours, complementaryCredits,
                gpa, mc, iea, completedTerms, suspendedTerms, institutionalTerms, mobilityTerms, enrolledCredits, admissionGrade);
        return new Student(cpfRegistration, studentData);
    }
}
