package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Curriculum;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.AttemptsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ScsvFilesDataAccessFacade implements DataAccessFacade {
    private MapsHolder mapsHolder;
    private IndexesHolder indexesHolder;

    public ScsvFilesDataAccessFacade(String mapsListFile) {
        this.mapsHolder = new MapsHolder(mapsListFile);
        this.indexesHolder = new IndexesHolder(this.mapsHolder);
    }

    @Override
    public Collection<Student> getActives(String from, String to) {
        Collection<Student> filteredActives = new TreeSet<>();
        Collection<Student> actives = this.indexesHolder.getAllActives();
        actives.forEach(item -> {
            String admission = item.getStudentData().getAdmissionTerm();
            if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                filteredActives.add(item);
            }
        });
        return filteredActives;
    }

    @Override
    public Collection<ActiveSummary> getActiveSummary(String from, String to) {
        Collection<ActiveSummary> activeStudentsSummary = new TreeSet<>();
        Collection<Student> actives = this.indexesHolder.getAllActives();
        actives.forEach(item -> {
            String admission = item.getStudentData().getAdmissionTerm();
            if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                ActiveSummary studentSummary = new ActiveSummary(
                        item.getId().getRegistration(),
                        item.getStudentData().getAdmissionTerm(),
                        item.getStudentData().getCompletedTerms(),
                        computePercentage(item));
                activeStudentsSummary.add(studentSummary);
            }
        });
        return activeStudentsSummary;
    }

    private double computePercentage(Student item) {
        StudentData data = item.getStudentData();
        double totalCreditsFulfilled = data.getMandatoryCredits() + data.getElectiveCredits() + data.getComplementaryCredits();
        return totalCreditsFulfilled/ Curriculum.TOTAL_CREDITS_NEEDED;
    }

    @Override
    public Collection<Student> getAlumni(String from, String to) {
        Collection<Student> filteredAlumni = new TreeSet<>();
        Collection<Student> alumni = this.indexesHolder.getAllAlumni();
        alumni.forEach(item -> {
            String graduationTerm = item.getStudentData().getStatusTerm();
            if (graduationTerm != null && graduationTerm.compareTo(from) >= 0 && graduationTerm.compareTo(to) <= 0) {
                filteredAlumni.add(item);
            }
        });
        return filteredAlumni;
    }

    @Override
    public Collection<AlumniPerTermSummary> getAlumniPerTermSummary(String from, String to) {
        Collection<AlumniPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<CpfRegistration>> map = indexesHolder.getAlumniByGraduationTerm();
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
                AlumniPerTermSummary termData = new AlumniPerTermSummary(termAccumulatedGPA/termAlumniCount, term,
                        termAlumniCount);
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public Collection<Student> getDropouts(String from, String to) {
        Collection<Student> filteredDropouts = new TreeSet<>();
        Collection<Student> dropouts = this.indexesHolder.getAllDropouts();
        dropouts.forEach(item -> {
            String dropoutTerm = item.getStudentData().getStatusTerm();
            if (dropoutTerm != null && dropoutTerm.compareTo(from) >= 0 && dropoutTerm.compareTo(to) <= 0) {
                filteredDropouts.add(item);
            }
        });
        return filteredDropouts;
    }

    @Override
    public Collection<DropoutPerTermSummary> getDropoutsSummary(String from, String to) {
        Collection<DropoutPerTermSummary> dropoutSummaryResponses = new TreeSet<>();
        Map<String, Collection<CpfRegistration>> dropouts = this.indexesHolder.getDropoutByLeaveTerm();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        dropouts.forEach((k, v) -> {
            if (k.compareTo(from) >= 0 && k.compareTo(to) <= 0) {
                int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
                v.forEach(item -> {
                    StudentData dropout = studentsMap.get(item);
                    dropoutsCount[dropout.getDetailedStatusId()]++;
                });
                DropoutClassification dropoutClassification = new DropoutClassification(dropoutsCount);
                dropoutSummaryResponses.add(new DropoutPerTermSummary(k, dropoutClassification));
            }
        });
        return dropoutSummaryResponses;
    }

    @Override
    public Collection<AlumniPerStudentSummary> getAlumniPerStudentSummary(String from, String to) {
        String parsedFrom = "1" + from.substring(2,4) + from.substring(5,6) + "00000";
        String parsedTo = "1" + to.substring(2,4) + to.substring(5,6) + "99999";
        Collection<AlumniPerStudentSummary> alumniBasicData = new TreeSet<>();
        Collection<CpfRegistration> alumni = this.indexesHolder.getAlumni();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        for (CpfRegistration item : alumni) {
            if (new Registration(item.getRegistration()).compareTo(new Registration(parsedFrom)) >= 0 &&
                    new Registration(item.getRegistration()).compareTo(new Registration(parsedTo)) <= 0) {
                StudentData alumnus = studentsMap.get(item);
                AlumniPerStudentSummary basicData = new AlumniPerStudentSummary(item.getRegistration(), alumnus.getName(),
                        2, 1, alumnus.getAdmissionTerm(), alumnus.getStatusTerm());
                alumniBasicData.add(basicData);
            }
        }
        return alumniBasicData;
    }

    @Override
    public Collection<AttemptsSummary> getAttemptsSummary() {
        Collection<AttemptsSummary> summary = new TreeSet<>();
        Map<Registration, Integer> attemptsSummary = new HashMap<>();
        Map<RegistrationCodeTerm, Subject> enrollments = this.mapsHolder.getMap("enrollments");
        enrollments.forEach((k, v) -> {
            if (!v.getStatus().equals(SystemConstants.EM_CURSO)) {
                Integer currentCount = attemptsSummary.get(new Registration(k.getRegistration()));
                if (currentCount == null) {
                    currentCount = v.getCredits();
                } else {
                    currentCount += v.getCredits();
                }
                attemptsSummary.put(new Registration(k.getRegistration()), currentCount);
            }
        });
        attemptsSummary.forEach((k, v) -> {
            AttemptsSummary item = new AttemptsSummary(k, v);
            summary.add(item);
        });
        return summary;
    }

    @Override
    public Student getStudent(String registration) {
        Map<String, CpfRegistration> registrationMap = this.indexesHolder.getRegistrationMap();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        CpfRegistration key = registrationMap.get(registration);
        StudentData studentData = studentsMap.get(key);
        return new Student(key, studentData);
    }
}
