package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.EnrollmentsPerSubjectData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
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
                                                                    String enrolledCredits)
            throws InvalidParameterException {

        Collection<Student> students = this.getStudentsByStatus(courseCode, curriculumCode, "1970.1","2021.1", status);
        return this.getEmailsSearch(students, admissionTerm, studentName, gender, craOperation, cra, enrolledCreditsOperation, enrolledCredits);
    }

    private synchronized Collection<Student> getStudentsByStatus(String courseCode, String curriculumCode, String from, String to, String status) throws InvalidParameterException {
        Collection<Student> students = null;
        if (status.equals("Ativos")) {
            students = this.dataAccessFacade.getActives(courseCode, curriculumCode, from, to);
        } else if(status.equals("Evadidos")){
            students = this.dataAccessFacade.getDropouts(courseCode, curriculumCode, from, to);
        } else if(status.equals("Egressos")) {
            students = this.dataAccessFacade.getAlumni(courseCode, curriculumCode, from, to);
        } else if(status.equals("Todos")) {
            students = Stream.concat(
                    Stream.concat(this.dataAccessFacade.getActives(courseCode, curriculumCode, from, to).stream(),
                            this.dataAccessFacade.getDropouts(courseCode, curriculumCode, from, to).stream()),
                    this.dataAccessFacade.getAlumni(courseCode, curriculumCode, from, to).stream())
                    .collect(Collectors.toList());
        }
        return students;
    }

    private synchronized Map<String, EmailSearchResponse> getEmailsSearch (Collection<Student> students, String admissionTerm, String studentName,
                                                              String gender, String craOperation, String cra, String enrolledCreditsOperation, String enrolledCredits) {
        Collection<Student> studentsCollection = students;
        Map<String, EmailSearchResponse> search =  new HashMap<>();

        Pattern admissionPattern = Pattern.compile(admissionTerm, Pattern.CASE_INSENSITIVE);
        Pattern namePattern = Pattern.compile(studentName, Pattern.CASE_INSENSITIVE);
        Pattern genderPattern = Pattern.compile(gender, Pattern.CASE_INSENSITIVE);

        for( Student student: studentsCollection) {

            Matcher admissionMatcher = admissionPattern.matcher(student.getAdmissionTerm());
            Matcher nameMatcher = namePattern.matcher(student.getName());
            Matcher genderMatcher = genderPattern.matcher(student.getGender());

            List<Matcher> list = new ArrayList<>();
            list.add(admissionMatcher);
            list.add(genderMatcher);
            list.add(nameMatcher);

            boolean isStudentGpaMatchingRequest = this.compareStudentValueWithRequiredValue(craOperation, cra, student.getGpa());
            boolean isStudentCreditsMatchingRequest = this.compareStudentValueWithRequiredValue(enrolledCreditsOperation, enrolledCredits, student.getEnrolledCredits());

            if(list.get(0).find() && list.get(1).find() && list.get(2).find() && isStudentCreditsMatchingRequest && isStudentGpaMatchingRequest) {
                EmailSearchResponse emailSearchResponse = new EmailSearchResponse(student.getName(), student.getEmail());
                search.put(student.getRegistration().getRegistration(), emailSearchResponse);
            }

        }
        return search;
    }

    public Map<String, EmailSearchResponse> getSubjectEmailsSearch(String courseCode, String curriculumCode, String subjectName,
                                                                   String subjectType, String academicUnit, String term) throws InvalidParameterException {

        Map<String, EmailSearchResponse> search =  new HashMap<>();
        Collection<Student> students = this.getStudentsByStatus(courseCode, curriculumCode, "1970.1","2021.1", "Todos");
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

    private Collection<EnrollmentsPerSubjectData> filterSubjects(String courseCode, String curriculumCode, String academicUnit, Collection<EnrollmentsPerSubjectData> allSubjects, String subjectName) {
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

    private boolean compareStudentValueWithRequiredValue (String operation, String valueToCompare, double value) {
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
