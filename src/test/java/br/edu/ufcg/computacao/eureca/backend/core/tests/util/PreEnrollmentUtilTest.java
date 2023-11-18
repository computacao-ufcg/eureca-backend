package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

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
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms,
                completedMandatoryCredits, completedOptionalCredits, 0,
                completedComplementaryCredits, 0, 0, 0,
                0, 0);

        PreEnrollmentUtil.sanitizeSubject(curriculum.getCourseCode(), curriculum.getCurriculumCode(), subjectSchedule, progress);

        Assert.assertNull(subjectSchedule.getSchedule("02"));
        Assert.assertNotNull(subjectSchedule.getSchedule("01"));
    }

    @Test
    public void testGetIdealCreditsPerSubjectTypeWithMaxCredits() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 5,
                54, 0, 8, 0,
                0, 0, 0, 0, 0);
        int maxCredits = 24;
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress, maxCredits);

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

    // Test for term 1
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm1() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 0,
                0, 0,0, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,20);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(0, actualTerm);
        Assert.assertEquals(1, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 16; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    // Test for term 2
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm2() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 1,
                16, 0,4, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,20);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(1, actualTerm);
        Assert.assertEquals(2, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 16; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    // Test for term 3
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm3() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 2,
                32, 0,8, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,24);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(2, actualTerm);
        Assert.assertEquals(3, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 24; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    // Test for term 4
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm4() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 3,
                56, 0,8, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,24);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(3, actualTerm);
        Assert.assertEquals(4, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 24; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    // Test for term 5
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm5() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 4,
                80, 0,8, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,24);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(4, actualTerm);
        Assert.assertEquals(5, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 24; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    // Test for term 6
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm6() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 5,
                104, 0,8, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,20);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(5, actualTerm);
        Assert.assertEquals(6, nextTerm);

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

    // Test for term 7
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm7() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 6,
                116, 8,8, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,20);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(6, actualTerm);
        Assert.assertEquals(7, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 8; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 8; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }


    // Test for term 8
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm8() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 7,
                124, 16,12, 0,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,20);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(7, actualTerm);
        Assert.assertEquals(8, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 8; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    // Test for term 9
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTerm9() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 8,
                128, 24,16, 4,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,24);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(8, actualTerm);
        Assert.assertEquals(9, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 4; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 16; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    // Test for term 9
    @Test
    public void testGetIdealCreditsPerSubjectTypeWithoutMaxCreditsTermGreaterThan9() {
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", 9,
                132, 40,16, 8,
                0, 0, 0, 0, 0);
        Map<SubjectType, Integer> response = PreEnrollmentUtil.getIdealCreditsPerSubjectType(this.curriculum, progress,24);

        int actualTerm = PreEnrollmentUtil.getActualTerm(this.curriculum, progress);
        int nextTerm = Math.min(curriculum.getMinNumberOfTerms(), (actualTerm + 1));

        Assert.assertEquals(9, actualTerm);
        Assert.assertEquals(9, nextTerm);

        Integer mandatoryIdealCredits = response.get(SubjectType.MANDATORY);
        Integer optionalIdealCredits = response.get(SubjectType.OPTIONAL);
        Integer complementaryIdealCredits = response.get(SubjectType.COMPLEMENTARY);
        Integer electiveIdealCredits = response.get(SubjectType.ELECTIVE);

        Integer expectedMandatoryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedComplementaryIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedOptionalIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)
        Integer expectedElectiveIdealCredits = 0; // curriculum.getIdealMandatoryCredits(nextTerm - 1)

        Assert.assertEquals(expectedComplementaryIdealCredits, complementaryIdealCredits);
        Assert.assertEquals(expectedMandatoryIdealCredits, mandatoryIdealCredits);
        Assert.assertEquals(expectedElectiveIdealCredits, electiveIdealCredits);
        Assert.assertEquals(expectedOptionalIdealCredits, optionalIdealCredits);
    }

    @Test
    public void testGetActualTerm0() {
        int completedTerms = 0; // not important
        int completedMandatoryCredits = 0;
        int completedOptionalCredits = 0;
        int completedComplementaryCredits = 0;
        int enrolledCredits = 0;
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 0;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm0delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(0);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 0;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm1() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(0) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 1;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm1delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(1);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 1;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm2() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(1) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 2;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm2delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(2);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 2;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm3() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(2) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 3;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm3delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(3);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 3;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm4() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(3) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 4;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm4delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(4);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 4;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm5() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(4) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 5;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm5delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(5);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 5;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm6() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(5) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 6;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm6delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(6);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 6;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm7() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(6) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 7;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm7delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(7);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 7;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm8() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(7) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 8;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm8delayed() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(8);
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 8;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTerm9() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = curriculum.getExpectedMinAccumulatedCredits(8) + 1;
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = 9;

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }

    @Test
    public void testGetActualTermOverMinNumberOfTerms() {
        int completedTerms = 1; // not important
        int completedMandatoryCredits = 1000; // way over possible
        int completedOptionalCredits = 0; // not important
        int completedComplementaryCredits = 0; // not important
        int enrolledCredits = 0; // not important
        StudentCurriculumProgress progress = new StudentCurriculumProgress("2017", completedTerms, completedMandatoryCredits,
                completedOptionalCredits, 0, completedComplementaryCredits,
                0, 0, 0, 0, 0);

        int actualTerm = PreEnrollmentUtil.getActualTerm(curriculum, progress);
        int expectedActualTerm = curriculum.getMinNumberOfTerms(); // should never be above

        Assert.assertEquals(actualTerm, expectedActualTerm);
    }
}
