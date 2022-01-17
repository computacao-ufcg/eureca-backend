package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsPerSubjectData;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeachersStatisticsResponse;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentClassification;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommunicationController {
    private Logger LOGGER = Logger.getLogger(CommunicationController.class);

    private final DataAccessFacade dataAccessFacade;

    public CommunicationController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public Map<String, EmailSearchResponse> getStudentsEmailsSearch(String courseCode, String curriculumCode, String admissionTerm,
                                                       String studentName, String gender, String status,
                                                       String craOperation, String cra, String enrolledCreditsOperation,
                                                                    String enrolledCredits, String affirmativePolicy)
            throws EurecaException {

        Collection<Student> students = this.getStudentsByStatus(courseCode, curriculumCode, status);
        return this.getEmailsSearch(students, admissionTerm, studentName, gender, craOperation, cra, enrolledCreditsOperation, enrolledCredits, affirmativePolicy);
    }

    private synchronized Collection<Student> getStudentsByStatus(String courseCode, String curriculumCode, String status) throws EurecaException {
        Collection<Student> students = null;
        if (status.equals("Ativos")) {
            students = this.dataAccessFacade.getAllStudentsPerStatus(StudentClassification.ACTIVE, courseCode, curriculumCode);
        } else if(status.equals("Evadidos")){
            students = this.dataAccessFacade.getAllStudentsPerStatus(StudentClassification.DROPOUT, courseCode, curriculumCode);
        } else if(status.equals("Egressos")) {
            students = this.dataAccessFacade.getAllStudentsPerStatus(StudentClassification.ALUMNUS, courseCode, curriculumCode);
        } else if(status.equals("Todos")) {
            students = Stream.concat(
                    Stream.concat(this.dataAccessFacade.getAllStudentsPerStatus(StudentClassification.ACTIVE, courseCode, curriculumCode).stream(),
                            this.dataAccessFacade.getAllStudentsPerStatus(StudentClassification.DROPOUT, courseCode, curriculumCode).stream()),
                            this.dataAccessFacade.getAllStudentsPerStatus(StudentClassification.ALUMNUS, courseCode, curriculumCode).stream())
                            .collect(Collectors.toList());
        }
        return students;
    }

    private synchronized Map<String, EmailSearchResponse> getEmailsSearch (Collection<Student> students, String admissionTerm, String studentName,
                                                              String gender, String craOperation, String cra, String enrolledCreditsOperation, String enrolledCredits,
                                                                           String affirmativePolicy) {
        Collection<Student> studentsCollection = students;
        Map<String, EmailSearchResponse> search =  new HashMap<>();
        String affirmativePolicyResult = this.checkAffirmativePolicy(affirmativePolicy);

        Pattern admissionPattern = Pattern.compile(admissionTerm, Pattern.CASE_INSENSITIVE);
        Pattern namePattern = Pattern.compile(studentName, Pattern.CASE_INSENSITIVE);
        Pattern genderPattern = Pattern.compile(gender, Pattern.CASE_INSENSITIVE);
        Pattern affirmativePolicyPattern = Pattern.compile(affirmativePolicyResult, Pattern.CASE_INSENSITIVE);

        for( Student student: studentsCollection) {
            Matcher admissionMatcher = admissionPattern.matcher(student.getAdmissionTerm());
            Matcher nameMatcher = namePattern.matcher(student.getName());
            Matcher genderMatcher = genderPattern.matcher(student.getGender());
            Matcher affirmativePolicyMatcher = affirmativePolicyPattern.matcher(student.getAffirmativePolicy());

            boolean isStudentGpaMatchingRequest = this.compareStudentMetricWithRequiredValue(craOperation, cra, student.getGpa());
            boolean isStudentCreditsMatchingRequest = this.compareStudentMetricWithRequiredValue(enrolledCreditsOperation, enrolledCredits, student.getEnrolledCredits());

            if(admissionMatcher.find() && genderMatcher.find() && nameMatcher.find() && affirmativePolicyMatcher.find() && isStudentCreditsMatchingRequest && isStudentGpaMatchingRequest) {
                EmailSearchResponse emailSearchResponse = new EmailSearchResponse(student.getName(), student.getEmail());
                search.put(student.getRegistration().getRegistration(), emailSearchResponse);
            }

        }
        return search;
    }

    private String checkAffirmativePolicy(String affirmativePolicy) {
        String L1 = "Candidato com renda familiar bruta per capita igual ou inferior a 1,5 salrio mnimo que tenha cursado integralmente o ensino mdio em escola pblica.";
        String L2 = "Candidato autodeclarado preto, pardo ou indgena, com renda familiar bruta per capita igual ou inferior a 1,5 salrio mnimo que tenha cursado integralmente o ensino mdio em escola pblica.";
        String L5 = "Candidato que, independentemente da renda, tenha cursado integralmente o ensino mdio em escola pblica.";
        String L6 = "Candidato autodeclarado preto, pardo ou indgena que, independentemente da renda, tenha cursado integralmente o ensino mdio em escola pblica.";
        String L9 = "Candidato com deficincia com renda familiar bruta per capita igual ou inferior a 1,5 salrio mnimo que tenha cursado integralmente o ensino mdio em escola pblica.";
        String L10 = "Candidato com deficincia autodeclarado preto, pardo ou indgena, com renda familiar bruta per capita igual ou inferior a 1,5 salrio mnimo que tenha cursado integralmente o ensino mdio em escola pblica.";
        String L13 = "Candidato com deficincia que, independentemente da renda, tenha cursado integralmente o ensino mdio em escola pblica.";
        String L14 = "Candidato com deficincia autodeclarado preto, pardo ou indgena que, independentemente da renda, tenha cursado integralmente o ensino mdio em escola pblica.";
        String AC = "^$";
        String all = ".*?";

        Map<String, String> affirmativePolicyMap = new HashMap<>();
        affirmativePolicyMap.put("L1", L1);
        affirmativePolicyMap.put("L2", L2);
        affirmativePolicyMap.put("L5", L5);
        affirmativePolicyMap.put("L6", L6);
        affirmativePolicyMap.put("L9", L9);
        affirmativePolicyMap.put("L10", L10);
        affirmativePolicyMap.put("L13", L13);
        affirmativePolicyMap.put("L14", L14);
        affirmativePolicyMap.put("AC", AC);
        affirmativePolicyMap.put("all", all);

        return affirmativePolicyMap.get(affirmativePolicy);
    }

    public Map<String, EmailSearchResponse> getSubjectEmailsSearch(String courseCode, String curriculumCode, String subjectName,
                                                                   String subjectType, String academicUnit, String term) throws EurecaException {

        Map<String, EmailSearchResponse> search =  new HashMap<>();
        Collection<Student> students = this.getStudentsByStatus(courseCode, curriculumCode,"Todos");
        SubjectType type = null;
        Collection<EnrollmentsPerSubjectData> enrollmentsPerSubjectPerTerm = null;

        try {
            type = SubjectType.valueOf(subjectType);
            enrollmentsPerSubjectPerTerm = this.dataAccessFacade.getEnrollmentsPerSubjectPerTerm(courseCode, curriculumCode, term, term, type);
        } catch (Exception e) {
            SubjectType mandatory = SubjectType.valueOf("MANDATORY");
            SubjectType optional = SubjectType.valueOf("OPTIONAL");
            SubjectType elective = SubjectType.valueOf("ELECTIVE");
            SubjectType complementary = SubjectType.valueOf("COMPLEMENTARY");
            enrollmentsPerSubjectPerTerm = Stream.concat(Stream.concat(Stream.concat(this.dataAccessFacade.getEnrollmentsPerSubjectPerTerm(courseCode, curriculumCode, term, term, mandatory).stream(),
                                    this.dataAccessFacade.getEnrollmentsPerSubjectPerTerm(courseCode, curriculumCode, term, term, optional).stream()),
                            this.dataAccessFacade.getEnrollmentsPerSubjectPerTerm(courseCode, curriculumCode, term, term, elective).stream()),
                    this.dataAccessFacade.getEnrollmentsPerSubjectPerTerm(courseCode, curriculumCode, term, term, complementary).stream())
                    .collect(Collectors.toList());
        }

        Collection<EnrollmentsPerSubjectData> subjects = this.filterSubjects(courseCode, curriculumCode, academicUnit, enrollmentsPerSubjectPerTerm, subjectName);

        Map<String, EmailSearchResponse> emails = this.getStudentEmails(students);

        for (EnrollmentsPerSubjectData enrollmentsPerSubjectData : subjects) {
            for (String t : enrollmentsPerSubjectData.getEnrollmentsPerTerm().keySet()) {
                Map<String, ClassEnrollments> map = enrollmentsPerSubjectData.getEnrollmentsPerTerm().get(t);
                for (String i: map.keySet()) {
                    Set<String> enrollments = map.get(i).getEnrolleds();
                    for (String e: enrollments) {
                        search.put(e, emails.get(e));
                    }
                }
            }
        }

        return search;
    }

    public Map<String, EmailSearchResponse> getTeacherEmailsSearch(String courseCode, String curriculumCode, String teacherName,
                                                                   String teacherId, String academicUnit, String term) throws EurecaException {

        Pattern teacherIdPattern = Pattern.compile(teacherId, Pattern.CASE_INSENSITIVE);
        Pattern namePattern = Pattern.compile(teacherName, Pattern.CASE_INSENSITIVE);

        TeachersStatisticsResponse teachersSummary = this.dataAccessFacade.getTeachersPerTermSummary(courseCode, curriculumCode, term, term, academicUnit);
        Map<String, EmailSearchResponse> emails = new HashMap<>();

        teachersSummary.getTeachers().forEach(teacher -> {
            Matcher teacherIdMatcher = teacherIdPattern.matcher(teacher.getTeacherId());
            Matcher nameMatcher = namePattern.matcher(teacher.getTeacherName());

            if(teacherIdMatcher.find() && nameMatcher.find()) {
                EmailSearchResponse emailSearchResponse = new EmailSearchResponse(teacher.getTeacherName(), teacher.getTeacherEmail());
                emails.put(teacher.getTeacherId(), emailSearchResponse );
            }
        });
        return emails;

    }

    private Collection<EnrollmentsPerSubjectData> filterSubjects(String courseCode, String curriculumCode, String academicUnit, Collection<EnrollmentsPerSubjectData> allSubjects, String subjectName) throws EurecaException {
        Collection<EnrollmentsPerSubjectData> subjects = new ArrayList<>();
        Pattern subjectNamePattern = Pattern.compile(subjectName, Pattern.CASE_INSENSITIVE);
        Pattern academicUnitPattern = Pattern.compile(academicUnit, Pattern.CASE_INSENSITIVE);

        for (EnrollmentsPerSubjectData e: allSubjects) {
            Subject subject = this.dataAccessFacade.getSubject(courseCode, curriculumCode, e.getSubjectCode());
            Matcher subjectNameMatcher = subjectNamePattern.matcher(e.getSubjectName());
            Matcher academicUnitMatcher = academicUnitPattern.matcher(subject.getAcademicUnit());
            if(subjectNameMatcher.find() && academicUnitMatcher.find()) {
                subjects.add(e);
            }
        }
        return subjects;
    }

    private Map<String, EmailSearchResponse> getStudentEmails(Collection<Student> students) {
        Map<String, EmailSearchResponse> emails =  new HashMap<>();
        for (Student student: students) {
            EmailSearchResponse emailSearchResponse = new EmailSearchResponse(student.getName(), student.getEmail());
            emails.put(student.getRegistration().getRegistration(), emailSearchResponse);
        }
        return emails;
    }

    private boolean compareStudentMetricWithRequiredValue (String operation, String valueToCompare, double value) {
        double v = Double.parseDouble(valueToCompare);

        switch (operation) {
            case ">":
                return value > v;
            case "<":
                return value < v;
            case ">=":
                return value >= v;
            case "<=":
                return value <= v;
            case "=":
                return value == v;
            default:
                return true;
        }
    }
}
