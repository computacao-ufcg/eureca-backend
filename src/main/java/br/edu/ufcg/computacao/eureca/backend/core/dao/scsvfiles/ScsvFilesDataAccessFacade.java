package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.AttemptsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ScsvFilesDataAccessFacade implements DataAccessFacade {
    private MapsHolder mapsHolder;
    private IndexesHolder indexesHolder;

    public ScsvFilesDataAccessFacade(String mapsListFile) {
        this.mapsHolder = new MapsHolder(mapsListFile);
        this.indexesHolder = new IndexesHolder(this.mapsHolder);
    }

    @Override
    public Collection<Student> getActives(String from, String to) {
        return getFilteredStudents(StudentStatus.ACTIVE, from, to);
    }

    @Override
    public Collection<Student> getAlumni(String from, String to) {
        return getFilteredStudents(StudentStatus.ALUMNI, from, to);
    }

    @Override
    public Collection<Student> getDropouts(String from, String to) {
        return getFilteredStudents(StudentStatus.DROPOUT, from, to);
    }

    @Override
    public Collection<Student> getDelayed(String from, String to) {
        return this.getActives(from, to)
                .stream()
                .filter(item -> item.getRiskClass().equals(RiskClass.CRITICAL) ||
                        item.getRiskClass().equals(RiskClass.LATE) || item.getRiskClass().equals(RiskClass.UNFEASIBLE))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<ActivesPerTermSummary> getActivesPerTermSummary(String from, String to) {
        Collection<ActivesPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<CpfRegistration>> index = indexesHolder.getActiveByAdmissionTerm();
        Map<CpfRegistration, StudentData> studentsMap = mapsHolder.getMap("students");

        for (Map.Entry<String, Collection<CpfRegistration>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                RiskClassCountSummary riskClassCount = getRiskClassCountSummary(entry.getValue(), studentsMap);
                ActivesPerTermSummary termData = new ActivesPerTermSummary(term, riskClassCount);
                terms.add(termData);
            }
        }
        return terms;
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
                double aggregateGPA = 0.0;
                double aggregateTermsCount = 0.0;
                double aggregateCost = 0.0;
                for (CpfRegistration id : studentIds) {
                    StudentData alumnus = studentsMap.get(id);
                    aggregateGPA += alumnus.getGpa();
                    aggregateTermsCount += alumnus.getCompletedTerms();
                    aggregateCost += (MetricsCalculator.getInstance().computeMetrics(new Student(id, alumnus)).getCost());
                }
                AlumniPerTermSummary termData = new AlumniPerTermSummary(term, termAlumniCount,
                        (termAlumniCount == 0 ? 0.0 : aggregateGPA/termAlumniCount),
                        (termAlumniCount == 0 ? 0.0 : aggregateTermsCount/termAlumniCount),
                        (termAlumniCount == 0 ? 0.0 : aggregateCost/termAlumniCount)
                );
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public Collection<DropoutPerTermSummary> getDropoutsPerTermSummary(String from, String to) {
        Collection<DropoutPerTermSummary> dropoutSummaryResponses = new TreeSet<>();
        Map<String, Collection<CpfRegistration>> dropouts = this.indexesHolder.getDropoutByLeaveTerm();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        dropouts.forEach((k, v) -> {
            if (k.compareTo(from) >= 0 && k.compareTo(to) <= 0) {
                int dropoutsCount[] = new int[SystemConstants.DROPOUT_TYPES_COUNT];
                double aggregateTermsCount = 0.0;
                double aggregateCost = 0.0;
                for (CpfRegistration id : v) {
                    StudentData dropout = studentsMap.get(id);
                    dropoutsCount[dropout.getStatusIndex()]++;
                    aggregateTermsCount += dropout.getCompletedTerms();
                    aggregateCost += (MetricsCalculator.getInstance().computeMetrics(new Student(id, dropout)).getCost());
                }
                DropoutReasonSummary dropoutReasonSummary = new DropoutReasonSummary(dropoutsCount);
                int size = dropouts.size();
                double averageTerms = (size == 0 ? 0.0 : aggregateTermsCount/size);
                double averageCost = (size == 0 ? 0.0 : aggregateCost/size);
                dropoutSummaryResponses.add(new DropoutPerTermSummary(k, size, dropoutReasonSummary, averageTerms, averageCost));
            }
        });
        return dropoutSummaryResponses;
    }

    @Override
    public Collection<DelayedPerTermSummary> getDelayedPerTermSummary(String from, String to) {
        Collection<DelayedPerTermSummary> terms = new TreeSet<>();
        Map<String, Collection<CpfRegistration>> index = indexesHolder.getActiveByAdmissionTerm();
        Map<CpfRegistration, StudentData> studentsMap = mapsHolder.getMap("students");

        for (Map.Entry<String, Collection<CpfRegistration>> entry : index.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                RiskClassCountSummary riskClassCount = getRiskClassCountSummary(entry.getValue(), studentsMap);
                DelayedPerTermSummary termData = new DelayedPerTermSummary(term, riskClassCount);
                terms.add(termData);
            }
        }
        return terms;
    }

    @Override
    public Collection<AlumniDigestResponse> getAlumniPerStudentSummary(String from, String to) {
        String parsedFrom = "1" + from.substring(2,4) + from.substring(5,6) + "00000";
        String parsedTo = "1" + to.substring(2,4) + to.substring(5,6) + "99999";
        Collection<AlumniDigestResponse> alumniBasicData = new TreeSet<>();
        Collection<CpfRegistration> alumni = this.indexesHolder.getAlumni();
        Map<CpfRegistration, StudentData> studentsMap = this.mapsHolder.getMap("students");
        for (CpfRegistration item : alumni) {
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

    private Collection<Student> getFilteredStudents(StudentStatus status, String from, String to) {
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

    private String getGroupingTerm(StudentStatus status, Student item) {
        switch(status) {
            case ALUMNI:
            case DROPOUT:
                return item.getStudentData().getStatusTerm();
            case ACTIVE:
            case DELAYED:
            default:
                return item.getStudentData().getAdmissionTerm();
        }
    }

    private Collection<Student> getAllStudentsByStatus(StudentStatus status) {
        switch(status) {
            case ALUMNI:
                return this.indexesHolder.getAllAlumni();
            case DROPOUT:
                return this.indexesHolder.getAllDropouts();
            case ACTIVE:
            case DELAYED:
            default:
                return this.indexesHolder.getAllActives();
        }
    }

    private RiskClassCountSummary getRiskClassCountSummary(Collection<CpfRegistration> studentIds, Map<CpfRegistration,
            StudentData> studentsMap) {
        int unfeasible = 0;
        int critical = 0;
        int late = 0;
        int normal = 0;
        int advanced = 0;
        int notApplicable = 0;
        for (CpfRegistration id : studentIds) {
            Student student = new Student(id, studentsMap.get(id));
            RiskClass riskClass = student.getRiskClass();
            switch (riskClass) {
                case UNFEASIBLE:
                    unfeasible++;
                    break;
                case CRITICAL:
                    critical++;
                    break;
                case LATE:
                    late++;
                    break;
                case NORMAL:
                    normal++;
                    break;
                case ADVANCED:
                    advanced++;
                    break;
                default:
                    notApplicable++;
                    break;
            }
        }
        RiskClassCountSummary riskClassCount = new RiskClassCountSummary(unfeasible, critical, late, normal,
                advanced, notApplicable);
        return riskClassCount;
    }
}
