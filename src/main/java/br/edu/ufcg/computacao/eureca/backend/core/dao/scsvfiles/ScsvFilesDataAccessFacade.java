package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;
import br.edu.ufcg.computacao.eureca.backend.core.models.*;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import br.edu.ufcg.computacao.eureca.common.exceptions.InvalidParameterException;
import org.apache.log4j.Logger;

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
    public TreeSet<String> getTermsForCurriculum(String courseCode, String curriculum) {
        return this.indexesHolder.getTermsPerCurriculum(courseCode, curriculum);
    }

    @Override
    public Collection<String> getCurriculumCodes(String courseCode) {
        return this.indexesHolder.getCurricula(courseCode);
    }

    @Override
    public Collection<SubjectMetricsPerTerm> getSubjectMetricsPerTerm(String from, String to, String courseCode,
                                                                      String curriculumCode, String subjectCode) {
        Collection<SubjectMetricsPerTerm> response = new TreeSet<>();
        Map<String, Map<String, ClassEnrollments>> terms = this.indexesHolder.getSubjectMetricsPerTerm(from, to, courseCode, curriculumCode, subjectCode);
        for (String term : terms.keySet()) {
            int failedDueToAbsences = 0;
            int failedDueToGrade = 0;
            int cancelled = 0;
            int succeeded = 0;
            int ongoing = 0;
            int exempted = 0;
            int totalEnrolled = 0;
            Map<String, ClassEnrollments> classes = terms.get(term);
            for (ClassEnrollments enrollments: classes.values()) {
                failedDueToAbsences += enrollments.getNumberFailedDueToAbsence();
                failedDueToGrade += enrollments.getNumberFailedDueToGrade();
                cancelled += enrollments.getNumberCancelled();
                succeeded += enrollments.getNumberSucess();
                ongoing += enrollments.getNumberOngoing();
                exempted += enrollments.getNumberExempted();
                totalEnrolled += enrollments.getNumberOfEnrolleds();
            }
            SubjectMetrics metrics = new SubjectMetrics(failedDueToAbsences, failedDueToGrade, cancelled,
                    succeeded, ongoing, exempted, totalEnrolled);
            SubjectMetricsPerTerm metricsPerTerm = new SubjectMetricsPerTerm(term, metrics);
            response.add(metricsPerTerm);
        }
        return response;
    }

    @Override
    public Subject getSubject(String courseCode, String curriculumCode, String subjectCode) {
        Map<SubjectKey, SubjectData> subjectMap = this.mapsHolder.getMap("subjects");
        SubjectKey key = new SubjectKey(courseCode, curriculumCode, subjectCode);
        SubjectData subjectData = subjectMap.get(key);
        return subjectData.createSubject(key);
    }

    @Override
    public int getNumberOfClassesPerSubject(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getNumberOfClassesPerSubject(from, to, courseCode, curriculumCode, subjectCode);
    }

    @Override
    public Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> getEnrollmentsPerTermPerSubject(String from, String to, String courseCode, String curriculumCode) {
        return this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass(from, to);
    }

    @Override
    public MetricStatistics getSucceededStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_SUCCEEDED);
    }

    @Override
    public MetricStatistics getExemptedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_EXEMPTED);
    }

    @Override
    public MetricStatistics getOngoingStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_ONGOING);
    }

    @Override
    public MetricStatistics getFailedDueToGradeStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_FAILED_DUE_GRADE);
    }

    @Override
    public MetricStatistics getFailedDueToAbsencesStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_FAILED_DUE_ABSENCE);
    }

    @Override
    public MetricStatistics getSuspendedStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_SUSPENDED);
    }

    @Override
    public MetricStatistics getCancelledStatistics(String from, String to, String courseCode, String curriculumCode, String subjectCode) {
        return getEnrollmentsStatistics(from, to, courseCode, curriculumCode, subjectCode, SystemConstants.STATUS_CANCELLED);
    }

    @Override
    public Collection<SubjectRetentionSummaryResponse> getSubjectsRetentionSummary(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionSummaryResponse> response = new TreeSet<>();
        Collection<String> subjectCodes = getMandatorySubjectsList(courseCode, curriculumCode);
        subjectCodes.forEach(item -> {
            int retention = getRetentionCount(courseCode, curriculumCode, item);
            Subject subject = getSubject(courseCode, curriculumCode, item);
            response.add(new SubjectRetentionSummaryResponse(subject.getIdealTerm(), item, subject.getName(), retention));
        });
        return response;
    }

    @Override
    public Collection<SubjectRetentionResponse> getSubjectsRetention(String courseCode, String curriculumCode) throws InvalidParameterException {
        Collection<SubjectRetentionResponse> response = new TreeSet<>();
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

    private int getRetentionCount(String courseCode, String curriculumCode, String subjectCode) {
        return this.indexesHolder.getRetentionCount(courseCode, curriculumCode, subjectCode);
    }

    private MetricStatistics getEnrollmentsStatistics(String from, String to, String courseCode, String curriculumCode,
                                                      String subjectCode, String status) {
        final int[] min = {Integer.MAX_VALUE};
        final int[] max = {0};
        final int[] total = {0};
        Map<SubjectKey, Map<String, Map<String, ClassEnrollments>>> allEnrollments =
                this.indexesHolder.getEnrollmentsPerSubjectPerTermPerClass(courseCode, curriculumCode);
        SubjectKey subjectKey = new SubjectKey(courseCode, curriculumCode, subjectCode);
        if (allEnrollments != null) {
            Map<String, Map<String, ClassEnrollments>> enrollments = allEnrollments.get(subjectKey);
            if (enrollments != null) {
                enrollments.forEach((term, classes) -> {
                    if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                        classes.forEach((classId, classEnrollments) -> {
                            int numberOfEnrollments = getClassEnrollmentsPerStatus(classEnrollments, status);
                            if (min[0] > numberOfEnrollments) min[0] = numberOfEnrollments;
                            if (max[0] < numberOfEnrollments) max[0] = numberOfEnrollments;
                            total[0] += numberOfEnrollments;
                        });
                    }
                });
            }
        }
        MetricStatistics ret = new MetricStatistics(min[0], max[0], total[0]);
        return ret;
    }

    private int getClassEnrollmentsPerStatus(ClassEnrollments classEnrollments, String status) {
        switch(status) {
            case SystemConstants.STATUS_SUCCEEDED:
                return classEnrollments.getNumberSucess();
            case SystemConstants.STATUS_EXEMPTED:
                return classEnrollments.getNumberExempted();
            case SystemConstants.STATUS_ONGOING:
                return classEnrollments.getNumberOngoing();
            case SystemConstants.STATUS_FAILED_DUE_GRADE:
                return classEnrollments.getNumberFailedDueToGrade();
            case SystemConstants.STATUS_FAILED_DUE_ABSENCE:
                return classEnrollments.getNumberFailedDueToAbsence();
            case SystemConstants.STATUS_SUSPENDED:
                return classEnrollments.getNumberSuspended();
            default:
                return classEnrollments.getNumberCancelled();
        }
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
