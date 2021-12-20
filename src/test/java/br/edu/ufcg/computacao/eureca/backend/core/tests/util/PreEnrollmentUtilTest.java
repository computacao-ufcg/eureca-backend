package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.core.controllers.PreEnrollmentController;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.ScsvFilesDataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.PreEnrollmentUtil;
import br.edu.ufcg.computacao.eureca.backend.util.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class PreEnrollmentUtilTest {

    private List<SubjectSchedule> subjectSchedules;
    private Curriculum curriculum;

    @Before
    public void setUp() {
        this.setUpSubjectSchedules();
        this.curriculum = TestUtils.getCurriculum();
    }

    private void setUpSubjectSchedules() {
        Subject s1 = new Subject("14102100", "2017", "1", "1411", "M", 4, 60, "mandatory1", new ArrayList<>(), 1, new ArrayList<>(), new ArrayList<>());
        Subject s2 = new Subject("14102100", "2017", "2", "1411", "M", 4, 60, "mandatory2", new ArrayList<>(), 2, new ArrayList<>(), new ArrayList<>());
        Subject s3 = new Subject("14102100", "2017", "3", "1411", "O", 4, 60, "optional", new ArrayList<>(), 3, new ArrayList<>(), new ArrayList<>());
        Subject s4 = new Subject("14102100", "2017", "4", "1411", "E", 4, 60, "elective", new ArrayList<>(), 4, new ArrayList<>(), new ArrayList<>());
        Subject s5 = new Subject("14102100", "2017", "5", "1411", "C", 4, 60, "complementary", new ArrayList<>(), 4, new ArrayList<>(), new ArrayList<>());

        Schedule sc1 = new Schedule(10, "01", Arrays.asList(new ClassSchedule(WeekDay.MONDAY, "8", "10"), new ClassSchedule(WeekDay.WEDNESDAY, "10", "12")));
        Schedule sc1_2 = new Schedule(0, "02", Arrays.asList(new ClassSchedule(WeekDay.MONDAY, "16", "18"), new ClassSchedule(WeekDay.WEDNESDAY, "10", "12")));
        Schedule sc2 = new Schedule(0, "03", Arrays.asList(new ClassSchedule(WeekDay.TUESDAY, "14", "16"), new ClassSchedule(WeekDay.FRIDAY, "10", "12")));
        Schedule sc3 = new Schedule(30, "01", Arrays.asList(new ClassSchedule(WeekDay.MONDAY, "8", "10"), new ClassSchedule(WeekDay.THURSDAY, "10", "12")));
        Schedule sc4 = new Schedule(20, "02", Arrays.asList(new ClassSchedule(WeekDay.MONDAY, "8", "10"), new ClassSchedule(WeekDay.WEDNESDAY, "10", "12")));

        Map<String, Schedule> schedules1 = new HashMap<>();
        schedules1.put("01", sc1);
        schedules1.put("02", sc1_2);

        Map<String, Schedule> schedules2 = new HashMap<>();
        schedules2.put("03", sc2);

        Map<String, Schedule> schedules3 = new HashMap<>();
        schedules3.put("01", sc3);

        Map<String, Schedule> schedules4 = new HashMap<>();
        schedules4.put("02", sc4);

        SubjectSchedule ss1 = new SubjectSchedule(s1, schedules1);
        SubjectSchedule ss2 = new SubjectSchedule(s2, schedules2);
        SubjectSchedule ss3 = new SubjectSchedule(s3, schedules3);
        SubjectSchedule ss4 = new SubjectSchedule(s4, schedules4);
        SubjectSchedule ss5 = new SubjectSchedule(s5, new HashMap<>());

        this.subjectSchedules = Arrays.asList(ss1, ss2, ss3, ss4, ss5);
    }

    @Test
    public void testGetMandatorySubjectsPerTerm() {
        Map<Integer, Collection<SubjectSchedule>> response = PreEnrollmentUtil.getSubjectsPerTerm(this.subjectSchedules, SubjectType.MANDATORY);
        Map<Integer, Collection<SubjectSchedule>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(this.subjectSchedules.get(0)));
        expected.put(2, Arrays.asList(this.subjectSchedules.get(1)));

        Assert.assertEquals(response.size(), expected.size());
        Assert.assertEquals(response.get(2), expected.get(2));
        Assert.assertEquals(response.get(1), expected.get(1));
        Assert.assertNull(response.get(3));
    }

    @Test
    public void testGetOptionalSubjectsPerTerm() {
        Map<Integer, Collection<SubjectSchedule>> response = PreEnrollmentUtil.getSubjectsPerTerm(this.subjectSchedules, SubjectType.OPTIONAL);
        Map<Integer, Collection<SubjectSchedule>> expected = new HashMap<>();
        expected.put(3, Arrays.asList(this.subjectSchedules.get(2)));

        Assert.assertEquals(response.size(), expected.size());
        Assert.assertFalse(response.get(3).isEmpty());
        Assert.assertEquals(response.get(3), expected.get(3));
    }

    @Test
    public void testGetComplementarySubjectsPerTerm() {
        Map<Integer, Collection<SubjectSchedule>> response = PreEnrollmentUtil.getSubjectsPerTerm(this.subjectSchedules, SubjectType.COMPLEMENTARY);
        Map<Integer, Collection<SubjectSchedule>> expected = new HashMap<>();
        expected.put(4, Arrays.asList(this.subjectSchedules.get(4)));

        Assert.assertEquals(response.size(), expected.size());
        Assert.assertFalse(response.get(4).isEmpty());
        Assert.assertEquals(response.get(4), expected.get(4));
    }

    @Test
    public void testGetElectiveSubjectsPerTerm() {
        Map<Integer, Collection<SubjectSchedule>> response = PreEnrollmentUtil.getSubjectsPerTerm(this.subjectSchedules, SubjectType.ELECTIVE);
        Map<Integer, Collection<SubjectSchedule>> expected = new HashMap<>();
        expected.put(4, Arrays.asList(this.subjectSchedules.get(3)));

        int expectedSize = 1;

        Assert.assertEquals(response.size(), expectedSize);
        Assert.assertFalse(response.get(4).isEmpty());
        Assert.assertEquals(response.get(4), expected.get(4));
    }

    @Test
    public void testGetSubjectCreditsSum() {
        int response = PreEnrollmentUtil.getSubjectCreditsSum(this.subjectSchedules);
        int expected = 20;

        Assert.assertEquals(response, expected);
    }

    @Test
    public void testExcludeUnavailableSubjects() {
        Collection<SubjectSchedule> prioritizedSubjects = new ArrayList<>();
        PreEnrollmentUtil.excludeUnavailableSubjects(prioritizedSubjects, this.subjectSchedules);
    }

    @Test
    public void testSanitizeSubject() {
        SubjectSchedule subjectSchedule = subjectSchedules.get(0);
        Assert.assertNotNull(subjectSchedule.getSchedule("02"));
        Assert.assertNotNull(subjectSchedule.getSchedule("01"));

        int completedTerms = 5;
        int completedMandatoryCredits = 72;
        int completedOptionalCredits = 8;
        int completedComplementaryCredits = 0;
        int enrolledCredits = 16;
        StudentCurriculumProgress progress = new StudentCurriculumProgress(completedTerms, completedMandatoryCredits, completedOptionalCredits, completedComplementaryCredits, enrolledCredits);

        PreEnrollmentUtil.sanitizeSubject(curriculum.getCourseCode(), curriculum.getCurriculumCode(), subjectSchedule, progress);

        Assert.assertNull(subjectSchedule.getSchedule("02"));
        Assert.assertNotNull(subjectSchedule.getSchedule("01"));
    }

    @Test
    public void testGetIdealCreditsPerSubjectTypeWithMaxCredits() {
        int maxCredits = 24;
        int nextTerm = 6;
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, maxCredits, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = Math.min(maxCredits, mandatoryIdealCredits);
        Integer expectedComplementaryIdealCredits = Math.min(maxCredits - expectedMandatoryIdealCredits, complementaryIdealCredits);
        Integer expectedOptionalIdealCredits = Math.min(maxCredits - expectedMandatoryIdealCredits - expectedComplementaryIdealCredits, optionalIdealCredits);
        Integer expectedElectiveIdealCredits = Math.min(maxCredits - expectedMandatoryIdealCredits - expectedComplementaryIdealCredits - expectedOptionalIdealCredits, electiveIdealCredits);

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCredits() {
        int nextTerm = 6;
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, null, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 12; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 8; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    @Test
    public void testGetActualTerm() {
        int completedTerms = 5;
        int completedMandatoryCredits = 72;
        int completedOptionalCredits = 8;
        int completedComplementaryCredits = 0;
        int enrolledCredits = 16;
        StudentCurriculumProgress progress = new StudentCurriculumProgress(completedTerms, completedMandatoryCredits, completedOptionalCredits, completedComplementaryCredits, enrolledCredits);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 5;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetNextTerm() {
        int completedTerms = 5;
        int completedMandatoryCredits = 72;
        int completedOptionalCredits = 8;
        int completedComplementaryCredits = 0;
        int enrolledCredits = 16;
        StudentCurriculumProgress progress = new StudentCurriculumProgress(completedTerms, completedMandatoryCredits, completedOptionalCredits, completedComplementaryCredits, enrolledCredits);

        int actualTerm = 5;
        int nextTerm = PreEnrollmentUtil.getNextTerm(actualTerm, progress.getEnrolledCredits(), curriculum.getMinNumberOfTerms());
        int expectedNextTerm = 6;

        Assert.assertEquals(nextTerm, expectedNextTerm);
    }
}
