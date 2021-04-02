package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.RiskClassCountSummary;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.models.RiskClass;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import org.apache.log4j.Logger;

import java.util.*;

public class IndexesHolder {
    private Logger LOGGER = Logger.getLogger(IndexesHolder.class);

    private MapsHolder mapsHolder;
    private Map<String, CpfRegistration> registrationMap;
    private List<CpfRegistration> actives;
    private Map<String, Collection<CpfRegistration>> activeByAdmissionTerm;
    private Map<String, Collection<CpfRegistration>> delayedByAdmissionTerm;
    private List<CpfRegistration> alumni;
    private Map<String, Collection<CpfRegistration>> alumniByAdmissionTerm;
    private Map<String, Collection<CpfRegistration>> alumniByGraduationTerm;
    private List<CpfRegistration> dropouts;
    private Map<String, Collection<CpfRegistration>> dropoutByAdmissionTerm;
    private Map<String, Collection<CpfRegistration>> dropoutByLeaveTerm;
    private Map<String, Collection<CpfRegistration>> dropoutByReasonAndAdmissionTerm;
    private Map<String, Collection<CpfRegistration>> dropoutByReasonAndLeaveTerm;

    public IndexesHolder(MapsHolder mapsHolder) {
        this.mapsHolder = mapsHolder;
        buildIndexes();
    }

    public Map<String, CpfRegistration> getRegistrationMap() {
        return registrationMap;
    }

    public Map<String, Collection<CpfRegistration>> getActiveByAdmissionTerm() {
        return activeByAdmissionTerm;
    }

    public List<CpfRegistration> getAlumni() {
        return alumni;
    }

    public Map<String, Collection<CpfRegistration>> getAlumniByAdmissionTerm() {
        return alumniByAdmissionTerm;
    }

    public Map<String, Collection<CpfRegistration>> getAlumniByGraduationTerm() {
        return alumniByGraduationTerm;
    }

    public List<CpfRegistration> getDropouts() {
        return dropouts;
    }

    public Map<String, Collection<CpfRegistration>> getDropoutByAdmissionTerm() {
        return dropoutByAdmissionTerm;
    }

    public Map<String, Collection<CpfRegistration>> getDropoutByLeaveTerm() {
        return dropoutByLeaveTerm;
    }

    public Map<String, Collection<CpfRegistration>> getDropoutByReasonAndAdmissionTerm() {
        return dropoutByReasonAndAdmissionTerm;
    }

    public Map<String, Collection<CpfRegistration>> getDropoutByReasonAndLeaveTerm() {
        return dropoutByReasonAndLeaveTerm;
    }

    private void buildIndexes() {
        this.registrationMap = new HashMap<>();
        this.actives = new ArrayList<>();
        this.activeByAdmissionTerm = new HashMap<>();
        this.delayedByAdmissionTerm = new HashMap<>();
        this.alumni = new ArrayList<>();
        this.alumniByAdmissionTerm = new HashMap<>();
        this.alumniByGraduationTerm = new HashMap<>();
        this.dropouts = new ArrayList<>();
        this.dropoutByAdmissionTerm = new HashMap<>();
        this.dropoutByLeaveTerm = new HashMap<>();
        this.dropoutByReasonAndAdmissionTerm = new HashMap<>();
        this.dropoutByReasonAndLeaveTerm = new HashMap<>();
        Map<CpfRegistration, StudentData> mapStudents = this.mapsHolder.getMap("students");
        mapStudents.forEach((k, v) -> {
            this.registrationMap.put(k.getRegistration(), k);
            if (v.isActive()) {
                LOGGER.debug(String.format(Messages.INDEX_ACTIVE_S, v.getName()));
                this.actives.add(k);
                String admissionTerm = v.getAdmissionTerm();
                Collection<CpfRegistration> list = this.activeByAdmissionTerm.get(admissionTerm);
                if (list == null) list = new ArrayList<>();
                list.add(k);
                this.activeByAdmissionTerm.put(admissionTerm, list);

                switch ((new Student(k, v)).getRiskClass()) {
                    case CRITICAL:
                    case LATE:
                    case UNFEASIBLE:
                        Collection<CpfRegistration> listDelayed = this.delayedByAdmissionTerm.get(admissionTerm);
                        if (listDelayed == null) listDelayed = new ArrayList<>();
                        listDelayed.add(k);
                        this.delayedByAdmissionTerm.put(admissionTerm, listDelayed);
                        break;
                }

            }
            if (v.isAlumnus()) { // graduated
                LOGGER.debug(String.format(Messages.INDEX_ALUMNUS_S, v.getName()));
                this.alumni.add(k);
                String admissionTerm = v.getAdmissionTerm();
                String graduationTerm = v.getStatusTerm();
                Collection<CpfRegistration> listAdmissionTerm = this.alumniByAdmissionTerm.get(admissionTerm);
                if (listAdmissionTerm == null) listAdmissionTerm = new ArrayList<>();
                listAdmissionTerm.add(k);
                this.alumniByAdmissionTerm.put(admissionTerm, listAdmissionTerm);
                Collection<CpfRegistration> listGraduationTerm = this.alumniByGraduationTerm.get(graduationTerm);
                if (listGraduationTerm == null) listGraduationTerm = new ArrayList<>();
                listGraduationTerm.add(k);
                this.alumniByGraduationTerm.put(graduationTerm, listGraduationTerm);
            }
            if (v.isDropout()) { // dropout
                LOGGER.debug(String.format(Messages.INDEX_DROPOUT_S, v.getName()));
                this.dropouts.add(k);
                String admissionTerm = v.getAdmissionTerm();
                String leaveTerm = v.getStatusTerm();
                String reason = v.getStatusStr();
                Collection<CpfRegistration> listAdmissionTerm = this.dropoutByAdmissionTerm.get(admissionTerm);
                if (listAdmissionTerm == null) listAdmissionTerm = new ArrayList<>();
                listAdmissionTerm.add(k);
                this.dropoutByAdmissionTerm.put(admissionTerm, listAdmissionTerm);
                Collection<CpfRegistration> listLeaveTerm = this.dropoutByLeaveTerm.get(leaveTerm);
                if (listLeaveTerm == null) listLeaveTerm = new ArrayList<>();
                listLeaveTerm.add(k);
                this.dropoutByLeaveTerm.put(leaveTerm, listLeaveTerm);
                Collection <CpfRegistration> listReasonAndAdmissionTerm = this.dropoutByReasonAndAdmissionTerm.get((reason+admissionTerm));
                if (listReasonAndAdmissionTerm == null) listReasonAndAdmissionTerm = new ArrayList<>();
                listReasonAndAdmissionTerm.add(k);
                this.dropoutByReasonAndAdmissionTerm.put((reason+admissionTerm), listReasonAndAdmissionTerm);
                Collection<CpfRegistration> listReasonAndLeaveTerm = this.dropoutByReasonAndLeaveTerm.get((reason+leaveTerm));
                if (listReasonAndLeaveTerm == null) listReasonAndLeaveTerm = new ArrayList<>();
                listReasonAndLeaveTerm.add(k);
                this.dropoutByReasonAndLeaveTerm.put((reason+leaveTerm), listReasonAndLeaveTerm);
            }
        });
    }

    public Collection<Student> getAllActives() {
        Collection<Student> allActives = new ArrayList<>();
        Map<CpfRegistration, StudentData> mapStudents = this.mapsHolder.getMap("students");
        this.actives.forEach(k -> {
            allActives.add(new Student(k, mapStudents.get(k)));
        });
        return allActives;
    }

    public Collection<Student> getAllAlumni() {
        Collection<Student> allAlumni = new ArrayList<>();
        Map<CpfRegistration, StudentData> mapStudents = this.mapsHolder.getMap("students");
        this.alumni.forEach(k -> {
            allAlumni.add(new Student(k, mapStudents.get(k)));
        });
        return allAlumni;
    }

    public Collection<Student> getAllDropouts() {
        Collection<Student> allDropouts = new ArrayList<>();
        Map<CpfRegistration, StudentData> mapStudents = this.mapsHolder.getMap("students");
        this.dropouts.forEach(k -> {
            allDropouts.add(new Student(k, mapStudents.get(k)));
        });
        return allDropouts;
    }
}
