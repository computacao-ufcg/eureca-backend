//package br.edu.ufcg.computacao.eureca.backend.core;
//
//import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
//import br.edu.ufcg.computacao.eureca.backend.core.holders.MapsHolder;
//import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
//import br.edu.ufcg.computacao.eureca.backend.core.models.abstractions.Student;
//import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated.Cpf;
//import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.CpfRegistration;
//import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated.StudentCourse;
//import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.deprecated.StudentPersonalData;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
////private Map<String, Student> actives;
////private Map<String, Collection<String>> activeByAdmissionTerm;
////private Map<String, Student> alumni;
////private Map<String, Collection<String>> alumniByAdmissionTerm;
////private Map<String, Collection<String>> alumniByGraduationTerm;
////private Map<String, Student> dropouts;
////private Map<String, Collection<String>> dropoutByAdmissionTerm;
////private Map<String, Collection<String>> dropoutByLeaveTerm;
////private Map<String, Collection<String>> dropoutByReasonAndAdmissionTerm;
////private Map<String, Collection<String>> dropoutByReasonAndLeaveTerm;
//
//public class DataAccessFacade {
//
//    private void retrieveActive() {
//        Map<String, Student> actives = new HashMap<>();
//        Map<String, Collection<String>> activeByAdmissionTerm = new HashMap<>();
//
//        String codeStr = PropertiesHolder.getInstance().getProperty(SystemConstants.ACTIVE);
//        int activeCode = Integer.parseInt(codeStr);
//        Map<CpfRegistration, StudentCourse> mapStudentCourse = MapsHolder.getInstance().getMap("DiscenteVinculo");
//        Map<Cpf, StudentPersonalData> mapStudentPersonalData = MapsHolder.getInstance().getMap("Discente");
//        mapStudentCourse.forEach((k, v) -> {
//            if (v.getStatus_id() == activeCode) { // active
//                StudentPersonalData personalData = mapStudentPersonalData.get(new Cpf(k.getNational_id()));
//                actives.put(k.getNational_id(), new Student(k, personalData, v));
//                String admissionTerm = v.getAdmission_term();
//                Collection<String> list = activeByAdmissionTerm.get(admissionTerm);
//                if (list == null) list = new ArrayList<>();
//                list.add(k.getNational_id());
//                activeByAdmissionTerm.put(admissionTerm, list);
//            }
//        });
//    }
//
//    private void retrieveAlumni() {
//        this.alumni = new HashMap<>();
//        this.alumniByAdmissionTerm = new HashMap<>();
//        this.alumniByGraduationTerm = new HashMap<>();
//        String codeStr = PropertiesHolder.getInstance().getProperty(SystemConstants.GRADUATED);
//        int graduatedCode = Integer.parseInt(codeStr);
//        Map<CpfRegistration, StudentCourse> mapStudentCourse = MapsHolder.getInstance().getMap("DiscenteVinculo");
//        Map<Cpf, StudentPersonalData> mapStudentPersonalData = MapsHolder.getInstance().getMap("Discente");
//        mapStudentCourse.forEach((k, v) -> {
//            if (v.getDetailed_status_id() == graduatedCode) { // graduated
//                StudentPersonalData personalData = mapStudentPersonalData.get(new Cpf(k.getNational_id()));
//                this.alumni.put(k.getNational_id(), new Student(k, personalData, v));
//                String admissionTerm = v.getAdmission_term();
//                String graduationTerm = v.getTerm_status();
//                Collection<String> list = this.alumniByAdmissionTerm.get(admissionTerm);
//                if (list == null) list = new ArrayList<>();
//                list.add(k.getNational_id());
//                this.alumniByAdmissionTerm.put(admissionTerm, list);
//                list = this.alumniByGraduationTerm.get(graduationTerm);
//                if (list == null) list = new ArrayList<>();
//                list.add(k.getNational_id());
//                this.alumniByGraduationTerm.put(graduationTerm, list);
//            }
//        });
//    }
//
//    private void retrieveDropouts() {
//        this.dropouts = new HashMap<>();
//        this.dropoutByAdmissionTerm = new HashMap<>();
//        this.dropoutByLeaveTerm = new HashMap<>();
//        this.dropoutByReasonAndAdmissionTerm = new HashMap<>();
//        this.dropoutByReasonAndLeaveTerm = new HashMap<>();
//        String codeStr = PropertiesHolder.getInstance().getProperty(SystemConstants.GRADUATED);
//        int graduatedCode = Integer.parseInt(codeStr);
//        codeStr = PropertiesHolder.getInstance().getProperty(SystemConstants.REGULAR);
//        int regularCode = Integer.parseInt(codeStr);
//        Map<CpfRegistration, StudentCourse> mapStudentCourse = MapsHolder.getInstance().getMap("DiscenteVinculo");
//        Map<Cpf, StudentPersonalData> mapStudentPersonalData = MapsHolder.getInstance().getMap("Discente");
//        mapStudentCourse.forEach((k, v) -> {
//            if (v.getDetailed_status_id() != graduatedCode && v.getDetailed_status_id() != regularCode) { // dropout
//                StudentPersonalData personalData = mapStudentPersonalData.get(new Cpf(k.getNational_id()));
//                this.dropouts.put(k.getNational_id(), new Student(k, personalData, v));
//                String admissionTerm = v.getAdmission_term();
//                String leaveTerm = v.getTerm_status();
//                String reason = Integer.toString(v.getStatus_id());
//                Collection<String> list = this.dropoutByAdmissionTerm.get(admissionTerm);
//                if (list == null) list = new ArrayList<>();
//                list.add(k.getNational_id());
//                this.dropoutByAdmissionTerm.put(admissionTerm, list);
//                list = this.dropoutByLeaveTerm.get(leaveTerm);
//                if (list == null) list = new ArrayList<>();
//                list.add(k.getNational_id());
//                this.dropoutByLeaveTerm.put(leaveTerm, list);
//                list = this.dropoutByReasonAndAdmissionTerm.get((reason+admissionTerm));
//                if (list == null) list = new ArrayList<>();
//                list.add(k.getNational_id());
//                this.dropoutByReasonAndAdmissionTerm.put((reason+admissionTerm), list);
//                list = this.dropoutByReasonAndLeaveTerm.get((reason+leaveTerm));
//                if (list == null) list = new ArrayList<>();
//                list.add(k.getNational_id());
//                this.dropoutByReasonAndLeaveTerm.put((reason+leaveTerm), list);            }
//        });
//    }
//}
