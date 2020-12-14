package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Subject;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ScsvFilesDataAccessFacade implements DataAccessFacade {
    private MapsHolder mapsHolder;
    private StatisticsHolder statisticsHolder;

    public ScsvFilesDataAccessFacade(String mapsListFile) {
        this.mapsHolder = new MapsHolder(mapsListFile);
        this.statisticsHolder = new StatisticsHolder(this.mapsHolder);
    }

    @Override
    public Collection<StudentDataResponse> getActiveStudents(String from, String to) {
        Collection<StudentDataResponse> activeStudentsData = new TreeSet<>();
        Collection<Student> actives = this.statisticsHolder.getAllActives();
        actives.forEach(item -> {
            try {
                String admission = item.getStudentData().getAdmissionTerm();
                if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                    StudentDataResponse studentData = new StudentDataResponse(item.getId().getRegistration(),
                            item.getStudentData());
                    activeStudentsData.add(studentData);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        return activeStudentsData;
    }

    @Override
    public Collection<ActiveData> getActiveStudentsSummary(String from, String to) {
        Collection<ActiveData> activeStudentsSummary = new TreeSet<>();
        Collection<Student> actives = this.statisticsHolder.getAllActives();
        actives.forEach(item -> {
            String admission = item.getStudentData().getAdmissionTerm();
            if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                ActiveData studentSummary = new ActiveData(
                        item.getId().getRegistration(),
                        item.getStudentData().getAdmissionTerm(),
                        item.getStudentData().getTermsCount(),
                        computePercentage(item));
                activeStudentsSummary.add(studentSummary);
            }
        });
        return activeStudentsSummary;
    }

    @Override
    public Collection<AlumniData> getAllAlumni(String from, String to) {
        Collection<AlumniData> terms = new TreeSet<>();
        Map<String, Collection<CpfRegistration>> map = statisticsHolder.getAlumniByGraduationTerm();
        Map<CpfRegistration, StudentData> studentsMap = mapsHolder.getMap("students");
        for (Map.Entry<String, Collection<CpfRegistration>> entry : map.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<CpfRegistration> studentIds = entry.getValue();
                int termAlumniCount = studentIds.size();
                double termAccumulatedGPA = 0;
                for (CpfRegistration id : studentIds) {
                    StudentData alumnus = studentsMap.get(id);
                    termAccumulatedGPA += alumnus.getGpa();
                }
                AlumniData termData = new AlumniData(termAccumulatedGPA/termAlumniCount, term,
                        termAlumniCount);
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public AlumniDataResponse getAlumniSummary(String from, String to) {
        Collection<String> sliderLabel = new TreeSet<>();
        double accumulatedGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        Collection<AlumniData> terms = this.getAllAlumni(from, to);
        for (AlumniData item : terms) {
            sliderLabel.add(item.getGraduationTerm());
            double averageGPA = item.getAverageGpa();
            int termAlumniCount = item.getAlumniCount();
            String term = item.getGraduationTerm();
            totalAlumniCount += termAlumniCount;
            if (termAlumniCount >= maxAlumniCount) {
                maxAlumniCount = termAlumniCount;
                maxAlumniCountTerm = term;
            }
            if (termAlumniCount <= minAlumniCount) {
                minAlumniCount = termAlumniCount;
                minAlumniCountTerm = term;
            }
            accumulatedGPA += averageGPA * termAlumniCount;
        }

        AlumniDataResponse alumniSummaryResponse = new AlumniDataResponse(sliderLabel,
                (totalAlumniCount == 0 ? -1.0 : accumulatedGPA/totalAlumniCount), maxAlumniCount,
                (terms.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/terms.size()), minAlumniCount, maxAlumniCountTerm,
                minAlumniCountTerm, terms, totalAlumniCount);
        return alumniSummaryResponse;
    }

    @Override
    public Collection<DropoutData> getAllDropouts(String from, String to) {
        Collection<DropoutData> dropoutDataResponses = new TreeSet<>();
        Collection<CpfRegistration> dropouts = this.statisticsHolder.getDropouts();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        dropouts.forEach(item -> {
            try {
                StudentData dropout = studentsMap.get(item);
                String leaveTerm = dropout.getStatusTerm();
                if (leaveTerm != null && leaveTerm.compareTo(from) >= 0 && leaveTerm.compareTo(to) <= 0) {
                    String dropoutReason = dropout.getStatusStr();
                    StudentDataResponse student = new StudentDataResponse(item.getRegistration(), dropout);
                    DropoutData summary = new DropoutData(student, dropoutReason);
                    dropoutDataResponses.add(summary);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        return dropoutDataResponses;
    }

    @Override
    public Collection<DropoutSummaryResponse> getDropoutsSummary(String from, String to) {
        Collection<DropoutSummaryResponse> dropoutSummaryResponses = new TreeSet<>();
        Map<String, Collection<CpfRegistration>> dropouts = this.statisticsHolder.getDropoutByLeaveTerm();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        dropouts.forEach((k, v) -> {
            if (k.compareTo(from) >= 0 && k.compareTo(to) <= 0) {
                int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
                v.forEach(item -> {
                    StudentData dropout = studentsMap.get(item);
                    dropoutsCount[dropout.getDetailedStatusId()]++;
                });
                DropoutClassification dropoutClassification = new DropoutClassification(dropoutsCount);
                dropoutSummaryResponses.add(new DropoutSummaryResponse(k, dropoutClassification));
            }
        });
        return dropoutSummaryResponses;
    }

    @Override
    public Collection<AlumnusBasicData> getAlumniBasicData(String from, String to) {
        String parsedFrom = "1" + from.substring(2,4) + from.substring(5,6) + "00000";
        String parsedTo = "1" + to.substring(2,4) + to.substring(5,6) + "99999";
        Collection<AlumnusBasicData> alumniBasicData = new TreeSet<>();
        Collection<CpfRegistration> alumni = this.statisticsHolder.getAlumni();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        for (CpfRegistration item : alumni) {
            if (new Registration(item.getRegistration()).compareTo(new Registration(parsedFrom)) >= 0 &&
                    new Registration(item.getRegistration()).compareTo(new Registration(parsedTo)) <= 0) {
                StudentData alumnus = studentsMap.get(item);
                AlumnusBasicData basicData = new AlumnusBasicData(item.getRegistration(), alumnus.getName(),
                        2, 1, alumnus.getAdmissionTerm(), alumnus.getStatusTerm());
                alumniBasicData.add(basicData);
            }
        }
        return alumniBasicData;
    }

    @Override
    public Map<String, Integer> getEnrollments() {
        Map<String, Integer> enrollmentsSummary = new HashMap<>();
        Map<Registration, Subject> enrollments = this.mapsHolder.getMap("enrollments");
        enrollments.forEach((k, v) -> {
            Integer currentCount = enrollmentsSummary.get(k.getRegistration());
            if (currentCount == null) {
                currentCount = new Integer(v.getCredits());
            } else {
                currentCount += v.getCredits();
            }
            enrollmentsSummary.put(k.getRegistration(), currentCount);
        });
        return enrollmentsSummary;
    }

    private double computePercentage(Student item) {
        StudentData data = item.getStudentData();
        double totalCreditsFulfilled = data.getMandatoryCredits() + data.getElectiveCredits() + data.getComplementaryCredits();
        return totalCreditsFulfilled/ Curriculum.TOTAL_CREDITS_NEEDED;
    }
}
