package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.*;
import org.apache.log4j.Logger;

import java.util.*;

public class StatisticsHolder {
    private Logger LOGGER = Logger.getLogger(StatisticsHolder.class);

    private MapsHolder mapsHolder;
    private List<CpfRegistration> actives;
    private Map<String, Collection<CpfRegistration>> activeByAdmissionTerm;
    private List<CpfRegistration> alumni;
    private Map<String, Collection<CpfRegistration>> alumniByAdmissionTerm;
    private Map<String, Collection<CpfRegistration>> alumniByGraduationTerm;
    private List<CpfRegistration> dropouts;
    private Map<String, Collection<CpfRegistration>> dropoutByAdmissionTerm;
    private Map<String, Collection<CpfRegistration>> dropoutByLeaveTerm;
    private Map<String, Collection<CpfRegistration>> dropoutByReasonAndAdmissionTerm;
    private Map<String, Collection<CpfRegistration>> dropoutByReasonAndLeaveTerm;

    public StatisticsHolder(MapsHolder mapsHolder) {
        this.mapsHolder = mapsHolder;
        buildIndexes();
    }

    public List getActives() {
        return actives;
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
        this.actives = new ArrayList<>();
        this.activeByAdmissionTerm = new HashMap<>();
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
            if (v.isActive()) {
                LOGGER.info(String.format(Messages.INDEX_ACTIVE_S, v.getName()));
                this.actives.add(k);
                String admissionTerm = v.getAdmissionTerm();
                Collection<CpfRegistration> list = this.activeByAdmissionTerm.get(admissionTerm);
                if (list == null) list = new ArrayList<>();
                list.add(k);
                this.activeByAdmissionTerm.put(admissionTerm, list);
            }
            if (v.isAlumnus()) { // graduated
                LOGGER.info(String.format(Messages.INDEX_ALUMNUS_S, v.getName()));
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
                LOGGER.info(String.format(Messages.INDEX_DROPOUT_S, v.getName()));
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
}
