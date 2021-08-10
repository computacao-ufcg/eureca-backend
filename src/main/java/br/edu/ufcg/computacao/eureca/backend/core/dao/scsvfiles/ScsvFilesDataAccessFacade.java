package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.StudentClassification;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.*;

public class ScsvFilesDataAccessFacade implements DataAccessFacade {
    private final Logger LOGGER = Logger.getLogger(ScsvFilesDataAccessFacade.class);
    private MapsHolder mapsHolder;
    private IndexesHolder indexesHolder;

    public ScsvFilesDataAccessFacade(String mapsListFile) {
        this.mapsHolder = new MapsHolder(mapsListFile);
        this.indexesHolder = new IndexesHolder(this.mapsHolder);
    }

    @Override
    public Collection<Student> getActives(String from, String to) {
        return getFilteredStudents(StudentClassification.ACTIVE, from, to);
    }

    @Override
    public Collection<Student> getAlumni(String from, String to) {
        return getFilteredStudents(StudentClassification.ALUMNUS, from, to);
    }

    @Override
    public Collection<Student> getDropouts(String from, String to) {
        return getFilteredStudents(StudentClassification.DROPOUT, from, to);
    }

    @Override
    public Map<String, Collection<Student>> getActivesPerAdmissionTerm(String from, String to) {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getActivesPerAdmissionTerm();
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getAlumniPerGraduationTerm(String from, String to) {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getAlumniPerGraduationTerm();
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Map<String, Collection<Student>> getDropoutsPerDropoutTerm(String from, String to) {
        Map<String, Collection<NationalIdRegistrationKey>> index = indexesHolder.getDropoutsPerDropoutTerm();
        return getStudentMapFromIndex(from, to, index);
    }

    @Override
    public Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to) {
        String parsedFrom = "1" + from.substring(2,4) + from.substring(5,6) + "00000";
        String parsedTo = "1" + to.substring(2,4) + to.substring(5,6) + "99999";
        Collection<AlumniDigestResponse> alumniBasicData = new TreeSet<>();
        Collection<NationalIdRegistrationKey> alumni = this.indexesHolder.getAlumni();
        Map<NationalIdRegistrationKey, StudentData> studentsMap = this.mapsHolder.getMap("students");
        for (NationalIdRegistrationKey item : alumni) {
            if (new Registration(item.getRegistration()).compareTo(new Registration(parsedFrom)) >= 0 &&
                    new Registration(item.getRegistration()).compareTo(new Registration(parsedTo)) <= 0) {
                StudentData alumnus = studentsMap.get(item);
                AlumniDigestResponse basicData = new AlumniDigestResponse(item.getRegistration(), alumnus.getName(),
                        2, 1, alumnus.getAdmissionTerm(), alumnus.getStatusTerm());
                alumniBasicData.add(basicData);
            }
        }
        return alumniBasicData;
    }

    @Override
    public Curriculum getCurriculum(String course, String code) {
        Map<CurriculumKey, CurriculumData> curriculumMap = this.mapsHolder.getMap("curriculum");
        CurriculumKey key = new CurriculumKey(course, code);
        CurriculumData ret = curriculumMap.get(key);
        return ret.getCurriculum(key);
    }

    @Override
    public Collection<String> getCurriculumCodes(String courseCode) {
        return this.indexesHolder.getCurricula(courseCode);
    }

    @Override
    public ProfileResponse getProfile(String userId) {
        Map<UserKey, ProfileData> profileMap = this.mapsHolder.getMap("profile");
        ProfileData profileData = profileMap.get(new UserKey(userId));
        return new ProfileResponse(profileData.getCourseCode(), profileData.getCourseName());
    }

    @Override
    public Collection<SubjectMetricsPerTermSummary> getSubjectMetricsPerTermSummary(String from, String to, String courseCode, String curriculumCode, SubjectType subjectType) throws InvalidParameterException {
        Collection<String> subjects = getSubjectCodes(courseCode, curriculumCode, subjectType);
        Collection<SubjectMetricsPerTermSummary> subjectMetricsPerTerms = new TreeSet<>();
        for (String subjectCode : subjects) {
            Collection<SubjectMetricsPerTerm> response = new TreeSet<>();
            Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getSubjectMetricsPerTerm(from, to, courseCode, curriculumCode, subjectCode);
            for (String term : terms.keySet()) {
                Map<String, ClassEnrollments> classes = terms.get(term);
                SubjectMetrics metrics = computeSubjectMetrics(classes.values());
                SubjectMetricsPerTerm metricsPerTerm = new SubjectMetricsPerTerm(term, metrics);
                response.add(metricsPerTerm);
            }
            Subject subject = getSubject(courseCode, curriculumCode, subjectCode);
            subjectMetricsPerTerms.add(new SubjectMetricsPerTermSummary(subjectCode, subject.getName(), response));
        }
        return subjectMetricsPerTerms;
    }

    @Override
    public SubjectsStatisticsSummaryResponse getSubjectStatisticsSummary(String courseCode, String curriculumCode, String from, String to) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        SubjectStatisticsSummary mandatory = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getMandatorySubjectsList());
        SubjectStatisticsSummary optional = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getOptionalSubjectsList());
        SubjectStatisticsSummary elective = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getElectiveSubjectsList());
        SubjectStatisticsSummary complementary = buildSummary(from, to, courseCode, curriculumCode,
                curriculum.getComplementarySubjectsList());
        TreeSet<String> terms = getTermsForCurriculum(courseCode, curriculumCode);
        String first = terms.first();
        String last = terms.last();
        from = (from.compareTo(first) < 0 ? first : from);
        to = (to.compareTo(last) < 0 ? to : last);
        SubjectsStatisticsSummaryResponse ret = new SubjectsStatisticsSummaryResponse(courseCode, curriculumCode, from, to, mandatory,
                optional, elective, complementary);
        return ret;
    }

    @Override
    public Subject getSubject(String courseCode, String curriculumCode, String subjectCode) {
        Map<SubjectKey, SubjectData> subjectMap = this.mapsHolder.getMap("subjects");
        SubjectKey key = new SubjectKey(courseCode, curriculumCode, subjectCode);
        SubjectData subjectData = subjectMap.get(key);
        return subjectData.createSubject(key);
    }

    @Override
    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubject(String from, String to, String courseCode, String curriculumCode) {
        return this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass(from, to);
    }

    @Override
    public Collection<SubjectRetentionDigest> getSubjectsRetentionSummary(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionDigest> response = new TreeSet<>();
        Collection<String> subjectCodes = getMandatorySubjectsList(courseCode, curriculumCode);
        subjectCodes.forEach(item -> {
            int retention = getRetentionCount(courseCode, curriculumCode, item);
            Subject subject = getSubject(courseCode, curriculumCode, item);
            response.add(new SubjectRetentionDigest(subject.getIdealTerm(), item, subject.getName(), retention));
        });
        return response;
    }

    @Override
    public Collection<SubjectRetentionCSV> getSubjectsRetention(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionCSV> response = new TreeSet<>();
        Collection<String> subjectCodes = getMandatorySubjectsList(courseCode, curriculumCode);
        subjectCodes.forEach(item -> {
            response.addAll(this.indexesHolder.getRetention(courseCode, curriculumCode, item));
        });
        return response;
    }

    private Map<String, Collection<Student>> getStudentMapFromIndex(String from, String to,
                                                                    Map<String, Collection<NationalIdRegistrationKey>> index) {
        Map<NationalIdRegistrationKey, StudentData> studentsMap = mapsHolder.getMap("students");
        Map<String, Collection<Student>> termsMap = new HashMap<>();
        for (Map.Entry<String, Collection<NationalIdRegistrationKey>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<Student> actives = new TreeSet<>();
                entry.getValue().forEach(cpfRegistration -> {
                    StudentData studentData = studentsMap.get(cpfRegistration);
                    actives.add(studentData.createStudent(cpfRegistration));
                });
                termsMap.put(term, actives);
            }
        }
        return termsMap;
    }

    private Collection<String> getSubjectCodes(String courseCode, String curriculumCode, SubjectType subjectType) throws InvalidParameterException {
        Curriculum curriculum = getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, curriculumCode, courseCode));
        }        Collection<String> subjects = null;
        switch (subjectType) {
            case MANDATORY:
                subjects = curriculum.getMandatorySubjectsList();
                break;
            case OPTIONAL:
                subjects = curriculum.getOptionalSubjectsList();
                break;
            case ELECTIVE:
                subjects = curriculum.getElectiveSubjectsList();
                break;
            case COMPLEMENTARY:
                subjects = curriculum.getComplementarySubjectsList();
                break;
        }
        return subjects;
    }

    private SubjectMetrics computeSubjectMetrics(Collection<ClassEnrollments> classes) {
        int failedDueToAbsences = 0;
        int failedDueToGrade = 0;
        int cancelled = 0;
        int succeeded = 0;
        int ongoing = 0;
        int exempted = 0;
        int suspended = 0;
        int numberOfClasses = 0;
        int totalEnrolled = 0;

        for (ClassEnrollments enrollments : classes) {
            failedDueToAbsences += enrollments.getNumberFailedDueToAbsence();
            failedDueToGrade += enrollments.getNumberFailedDueToGrade();
            cancelled += enrollments.getNumberCancelled();
            succeeded += enrollments.getNumberSucceeded();
            ongoing += enrollments.getNumberOngoing();
            exempted += enrollments.getNumberExempted();
            suspended += enrollments.getNumberSuspended();
            numberOfClasses++;
            totalEnrolled += enrollments.getNumberOfEnrolleds();
        }
        return new SubjectMetrics(failedDueToAbsences, failedDueToGrade, cancelled,
                succeeded, ongoing, exempted, suspended, numberOfClasses, totalEnrolled);
    }

    private SubjectStatisticsSummary buildSummary(String from, String to, String courseCode, String curriculumCode,
                                                  Collection<String> subjects) {

        Collection<SubjectMetrics> metricsPerSubject = new ArrayList<>();
        for(String subjectCode : subjects) {
            SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
            Map<String, Map<String, ClassEnrollments>> enrollments = this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass().get(subjectKey);
            if (enrollments != null) {
                SubjectMetrics subjectMetrics = getEnrollmentsStatistics(from, to, enrollments);
                if (subjectMetrics != null) metricsPerSubject.add(subjectMetrics);
            }
        }
        SubjectMetricsStatistics metrics = computeSubjectMetricsStatistics(metricsPerSubject);
        return new SubjectStatisticsSummary(subjects.size(), metrics);
    }

    private SubjectMetricsStatistics computeSubjectMetricsStatistics(Collection<SubjectMetrics> metricsPerSubject) {
        List<Double> failedDueToAbsencesList = new ArrayList<>();
        List<Double> failedDueToGradeList = new ArrayList<>();
        List<Double> cancelledList = new ArrayList<>();
        List<Double> succeededList = new ArrayList<>();
        List<Double> ongoingList = new ArrayList<>();
        List<Double> exemptedList = new ArrayList<>();
        List<Double> suspendedList = new ArrayList<>();
        List<Double> totalEnrolledList = new ArrayList<>();
        for (SubjectMetrics subjectMetrics : metricsPerSubject) {
            failedDueToAbsencesList.add((double) subjectMetrics.getFailedDueToAbsences());
            failedDueToGradeList.add((double) subjectMetrics.getFailedDueToAbsences());
            cancelledList.add((double) subjectMetrics.getCancelled());
            succeededList.add((double) subjectMetrics.getSucceeded());
            exemptedList.add((double) subjectMetrics.getExempted());
            suspendedList.add((double) subjectMetrics.getSuspended());
            totalEnrolledList.add((double) subjectMetrics.getTotalEnrolled());
        }
        MetricStatistics failedDueToAbsences = new MetricStatistics(failedDueToAbsencesList);
        MetricStatistics failedDueToGrade = new MetricStatistics(failedDueToGradeList);
        MetricStatistics cancelled = new MetricStatistics(cancelledList);
        MetricStatistics succeeded = new MetricStatistics(succeededList);
        MetricStatistics ongoing = new MetricStatistics(ongoingList);
        MetricStatistics exempted = new MetricStatistics(exemptedList);
        MetricStatistics suspended = new MetricStatistics(suspendedList);
        MetricStatistics totalEnrolled = new MetricStatistics(totalEnrolledList);

        return new SubjectMetricsStatistics(failedDueToAbsences, failedDueToGrade, cancelled, succeeded, ongoing,
                exempted, suspended, totalEnrolled);
    }

    private TreeSet<String> getTermsForCurriculum(String courseCode, String curriculum) {
        return this.indexesHolder.getTermsPerCurriculum(courseCode, curriculum);
    }

    private int getRetentionCount(String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getRetentionCount(courseCode, curriculumCode, subjectCode);
    }

    private SubjectMetrics getEnrollmentsStatistics(String from, String to, @NotNull Map<String, Map<String, ClassEnrollments>> enrollments) {
        SubjectMetrics subjectMetrics = null;
        for (String term : enrollments.keySet()) {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Map<String, ClassEnrollments> classes = enrollments.get(term);
                subjectMetrics = computeSubjectMetrics(classes.values());
            }
        }
        return subjectMetrics;
    }

    private Collection<Student> getFilteredStudents(StudentClassification status, String from, String to) {
        Collection<Student> filteredStudents = new TreeSet<>();
        Collection<Student> allStudents = getAllStudentsByStatus(status);
        allStudents.forEach(item -> {
            String studentTerm = getGroupingTerm(status, item);
            if (studentTerm != null && studentTerm.compareTo(from) >= 0 && studentTerm.compareTo(to) <= 0) {
                filteredStudents.add(item);
            }
        });
        return filteredStudents;
    }

    private Collection<String> getMandatorySubjectsList(String courseCode, String curriculumCode) throws InvalidParameterException {
        Curriculum curriculum = this.getCurriculum(courseCode, curriculumCode);
        if (curriculum == null) {
            throw new InvalidParameterException(String.format(Messages.INEXISTENT_CURRICULUM_S_S, courseCode, curriculumCode));
        }
        return curriculum.getMandatorySubjectsList();
    }

    private String getGroupingTerm(StudentClassification status, Student item) {
        switch(status) {
            case ALUMNUS:
            case DROPOUT:
                return item.getStatusTerm();
            case ACTIVE:
            case DELAYED:
            default:
                return item.getAdmissionTerm();
        }
    }

    private Collection<Student> getAllStudentsByStatus(StudentClassification status) {
        switch(status) {
            case ALUMNUS:
                return this.indexesHolder.getAllAlumni();
            case DROPOUT:
                return this.indexesHolder.getAllDropouts();
            case ACTIVE:
            case DELAYED:
            default:
                return this.indexesHolder.getAllActives();
        }
    }
}
