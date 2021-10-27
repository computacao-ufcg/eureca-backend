package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.EmailSearchResponse;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
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
                                                       String craOperation, String cra, String enrolledCredits)
            throws InvalidParameterException {

        Collection<Student> students = this.getStudentsByStatus(courseCode, curriculumCode, "1970.1","2021.1", status);
        return this.getEmailsSearch(students, admissionTerm, studentName, gender, enrolledCredits);
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
                                                              String gender, String enrolledCredits) {
        Collection<Student> studentsCollection = students;
        Map<String, EmailSearchResponse> search =  new HashMap<>();

        Pattern admissionPattern = Pattern.compile(admissionTerm, Pattern.CASE_INSENSITIVE);
        Pattern namePattern = Pattern.compile(studentName, Pattern.CASE_INSENSITIVE);
        Pattern genderPattern = Pattern.compile(gender, Pattern.CASE_INSENSITIVE);
        Pattern enrolledCreditsPattern = Pattern.compile(enrolledCredits, Pattern.CASE_INSENSITIVE);

        for( Student student: studentsCollection) {

            Matcher admissionMatcher = admissionPattern.matcher(student.getAdmissionTerm());
            Matcher nameMatcher = namePattern.matcher(student.getName());
            Matcher genderMatcher = genderPattern.matcher(student.getGender());
            Matcher enrolledCreditsMatcher = enrolledCreditsPattern.matcher(String.valueOf(student.getEnrolledCredits()));

            List<Matcher> list = new ArrayList<>();
            if(!studentName.equals("^$")) {
                list.add(nameMatcher);
            } if (!gender.equals("^$")) {
                list.add(genderMatcher);
            }  if (!admissionTerm.equals("^$")) {
                list.add(admissionMatcher);
            } if (!enrolledCredits.equals("^$")) {
                list.add(enrolledCreditsMatcher);
            }

            EmailSearchResponse emailSearchResponse = null;
            if(list.size() == 1) {
                if(list.get(0).find()) {
                    emailSearchResponse = new EmailSearchResponse(student.getName(), student.getEmail());
                    search.put(student.getRegistration().getRegistration(), emailSearchResponse);
                }
            } else if (list.size() == 2) {
                if(list.get(0).find() && list.get(1).find()) {
                    emailSearchResponse = new EmailSearchResponse(student.getName(), student.getEmail());
                    search.put(student.getRegistration().getRegistration(), emailSearchResponse);
                }
            } else if (list.size() == 3) {
                if(list.get(0).find() && list.get(1).find() && list.get(2).find()) {
                    emailSearchResponse = new EmailSearchResponse(student.getName(), student.getEmail());
                    search.put(student.getRegistration().getRegistration(), emailSearchResponse);
                }
            } else if (list.size() == 4) {
                if(list.get(0).find() && list.get(1).find() && list.get(2).find() && list.get(3).find()) {
                    emailSearchResponse = new EmailSearchResponse(student.getName(), student.getEmail());
                    search.put(student.getRegistration().getRegistration(), emailSearchResponse);
                }
            }
        }
        return search;
    }
}
