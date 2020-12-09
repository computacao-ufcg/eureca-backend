package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.CpfRegistration;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.StudentData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ScsvFilesDataAccessFacade implements DataAccessFacade {
    private MapsHolder mapsHolder;
    private StatisticsHolder statisticsHolder;

    public ScsvFilesDataAccessFacade(String mapsListFile) {
        this.mapsHolder = new MapsHolder(mapsListFile);
        this.statisticsHolder = new StatisticsHolder(this.mapsHolder);
    }

    @Override
    public Collection<StudentDataResponse> getAllActiveStudents(String from, String to) {
        Collection<StudentDataResponse> activeStudentsData = new ArrayList<>();
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
    public Collection<ActiveSummaryResponse> getActiveStudentsSummary(String from, String to) {
        Collection<ActiveSummaryResponse> activeStudentsSummary = new ArrayList<>();
        Collection<Student> actives = this.statisticsHolder.getAllActives();
        actives.forEach(item -> {
            String admission = item.getStudentData().getAdmissionTerm();
            if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                ActiveSummaryResponse studentSummary = new ActiveSummaryResponse(
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
    public Collection<AlumniDataResponse> getAllAlumni(String from, String to) {
        Collection<AlumniDataResponse> terms = new ArrayList<>();
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
                AlumniDataResponse termData = new AlumniDataResponse(termAccumulatedGPA/termAlumniCount, term,
                        termAlumniCount);
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public AlumniSummaryResponse getAlumniSummary(String from, String to) {
        double accumulatedGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        Collection<AlumniDataResponse> terms = this.getAllAlumni(from, to);
        for (AlumniDataResponse item : terms) {
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

        AlumniSummaryResponse alumniSummaryResponse = new AlumniSummaryResponse(
                (totalAlumniCount == 0 ? -1.0 : accumulatedGPA/totalAlumniCount), maxAlumniCount,
                (terms.size() == 0 ? -1.0 : (1.0*totalAlumniCount)/terms.size()), minAlumniCount, minAlumniCountTerm,
                maxAlumniCountTerm, terms, totalAlumniCount);
        return alumniSummaryResponse;
    }

    @Override
    public Collection<DropoutDataResponse> getAllDropouts(String from, String to) {
        Collection<DropoutDataResponse> dropoutDataResponses = new ArrayList<>();
        Collection<CpfRegistration> dropouts = this.statisticsHolder.getDropouts();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        dropouts.forEach(item -> {
            try {
                StudentData dropout = studentsMap.get(item);
                String leaveTerm = dropout.getStatusTerm();
                if (leaveTerm != null && leaveTerm.compareTo(from) >= 0 && leaveTerm.compareTo(to) <= 0) {
                    String dropoutReason = dropout.getStatusStr();
                    StudentDataResponse student = new StudentDataResponse(item.getRegistration(), dropout);
                    DropoutDataResponse summary = new DropoutDataResponse(student, dropoutReason);
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
        Collection<DropoutSummaryResponse> dropoutSummaryResponses = new ArrayList<>();
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
    public Collection<AlumnusBasicData> getAlumniBasicData() {
        Collection<AlumnusBasicData> alumniBasicData = new ArrayList<>();
        Collection<CpfRegistration> alumni = this.statisticsHolder.getAlumni();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        alumni.forEach(item -> {
            StudentData alumnus = studentsMap.get(item);
            AlumnusBasicData basicData = new AlumnusBasicData(item.getRegistration(), alumnus.getName(),
                    2, 1, alumnus.getAdmissionTerm(), alumnus.getStatusTerm());
            alumniBasicData.add(basicData);
        });
        return alumniBasicData;
    }

    private double computePercentage(Student item) {
        StudentData data = item.getStudentData();
        double totalCreditsFulfilled = data.getMandatoryCredits() + data.getElectiveCredits() + data.getComplementaryCredits();
        return totalCreditsFulfilled/ Curriculum.totalCreditsNeeded;
    }
}
