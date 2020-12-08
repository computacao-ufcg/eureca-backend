package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.as.core.AuthenticationUtil;
import br.edu.ufcg.computacao.eureca.as.core.models.SystemUser;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.constants.*;
import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.MapsHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.StatisticsHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.abstractions.Student;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.*;
import br.edu.ufcg.computacao.eureca.backend.core.plugins.AuthorizationPlugin;
import br.edu.ufcg.computacao.eureca.common.exceptions.ConfigurationErrorException;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import br.edu.ufcg.computacao.eureca.common.util.CryptoUtil;
import br.edu.ufcg.computacao.eureca.common.util.ServiceAsymmetricKeysHolder;
import org.apache.log4j.Logger;

import java.security.GeneralSecurityException;

import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ApplicationFacade {
    private static final Logger LOGGER = Logger.getLogger(ApplicationFacade.class);
    private RSAPublicKey asPublicKey;
    private AuthorizationPlugin authorizationPlugin;
    private static ApplicationFacade instance;

    private ApplicationFacade() {
    }

    public static ApplicationFacade getInstance() {
        synchronized (ApplicationFacade.class) {
            if (instance == null) {
                instance = new ApplicationFacade();
            }
            return instance;
        }
    }

    public void setAuthorizationPlugin(AuthorizationPlugin authorizationPlugin) {
        this.authorizationPlugin = authorizationPlugin;
    }

    public Collection<ActiveSummaryResponse> getActives(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES);
        Collection<ActiveSummaryResponse> activeStudentsSummary = new ArrayList<>();
        Collection<Student> actives = StatisticsHolder.getInstance().getAllActives();
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

    public Collection<ActiveDataResponse> getActivesCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES_CSV);
        Collection<ActiveDataResponse> activeStudentsData = new ArrayList<>();
        Collection<Student> actives = StatisticsHolder.getInstance().getAllActives();
        actives.forEach(item -> {
            try {
                String admission = item.getStudentData().getAdmissionTerm();
                if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                    ActiveDataResponse studentData = new ActiveDataResponse(item.getId().getRegistration(),
                            item.getStudentData());
                    activeStudentsData.add(studentData);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        return activeStudentsData;
    }

    private double computePercentage(Student item) {
        StudentData data = item.getStudentData();
        double totalCreditsFulfilled = data.getMandatoryCredits() + data.getElectiveCredits() + data.getComplementaryCredits();
        return totalCreditsFulfilled/Curriculum.totalCreditsNeeded;
    }

    public AlumniSummaryResponse getAlumni(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI);
        double accumulatedGPA = 0;
        int maxAlumniCount = 0;
        String maxAlumniCountTerm = "";
        int minAlumniCount = Integer.MAX_VALUE;
        String minAlumniCountTerm = "";
        int totalAlumniCount = 0;

        Collection<AlumniDataResponse> terms = getAlumniSummaryPerTerm(from, to);
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

    public Collection<AlumniDataResponse> getAlumniCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_CSV);
        return getAlumniSummaryPerTerm(from, to);
    }

    private Collection<AlumniDataResponse> getAlumniSummaryPerTerm(String from, String to) {
        Collection<AlumniDataResponse> terms = new ArrayList<>();
        Map<String, Collection<CpfRegistration>> map = StatisticsHolder.getInstance().getAlumniByGraduationTerm();
        Map<CpfRegistration, StudentData> studentsMap = MapsHolder.getInstance().getMap("students");
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

    public Collection<DropoutSummaryResponse> getDropouts(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS);
        Collection<DropoutSummaryResponse> dropoutSummaryResponses = new ArrayList<>();
        Map<String, Collection<CpfRegistration>> dropouts = StatisticsHolder.getInstance().getDropoutByLeaveTerm();
        Map<CpfRegistration, StudentData> studentsMap = MapsHolder.getInstance().getMap("students");
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

    public Collection<DropoutDataResponse> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        Collection<DropoutDataResponse> dropoutDataResponses = new ArrayList<>();
        Collection<CpfRegistration> dropouts = StatisticsHolder.getInstance().getDropouts();
        Map<CpfRegistration, StudentData> studentsMap = MapsHolder.getInstance().getMap("students");
        dropouts.forEach(item -> {
            try {
                StudentData dropout = studentsMap.get(item);
                String leaveTerm = dropout.getStatusTerm();
                if (leaveTerm != null && leaveTerm.compareTo(from) >= 0 && leaveTerm.compareTo(to) <= 0) {
                    String affirmativeAction = dropout.getAffirmativePolicy();
                    String maritalStatus = dropout.getMaritalStatus();
                    String gender = dropout.getGender();
                    String dropoutCause = dropout.getStatusStr();
                    DropoutDataResponse summary = new DropoutDataResponse(affirmativeAction, dropout.getGpa(),
                            dropout.getComplementaryCredits(), dropout.getMandatoryCredits(),
                            dropout.getElectiveCredits(), dropout.getCurriculum(), maritalStatus,
                            gender, dropout.getIea(), item.getRegistration(), dropout.getInstitutionalTerms(),
                            dropout.getMc(), dropout.getAdmissionGrade(), dropout.getMobilityTerms(), dropoutCause,
                            dropout.getAdmissionTerm(), dropout.getTermsCount(), dropout.getSuspendedTerms());
                    dropoutDataResponses.add(summary);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        return dropoutDataResponses;
    }

    public Collection<AlumnusBasicData> getAlumniBasicData(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        Collection<AlumnusBasicData> alumniBasicData = new ArrayList<>();
        Collection<CpfRegistration> alumni = StatisticsHolder.getInstance().getAlumni();
        Map<CpfRegistration, StudentData> studentsMap = MapsHolder.getInstance().getMap("students");
        alumni.forEach(item -> {
            StudentData alumnus = studentsMap.get(item);
            AlumnusBasicData basicData = new AlumnusBasicData(item.getRegistration(), alumnus.getName(),
                    2, 1, alumnus.getAdmissionTerm(), alumnus.getStatusTerm());
            alumniBasicData.add(basicData);
        });
        return alumniBasicData;
    }

    public String getPublicKey() throws EurecaException {
        try {
            return CryptoUtil.toBase64(ServiceAsymmetricKeysHolder.getInstance().getPublicKey());
        } catch (GeneralSecurityException e) {
            throw new ConfigurationErrorException(e.getMessage());
        }
    }

    private RSAPublicKey getAsPublicKey() throws EurecaException {
        if (this.asPublicKey == null) {
            this.asPublicKey = EurecaAsPublicKeyHolder.getInstance().getAsPublicKey();
        }
        return this.asPublicKey;
    }

    private SystemUser authenticateAndAuthorize(String token, EurecaOperation operation) throws EurecaException {
        // ToDo: remove this test when the frontend is updated to send the token in the request
        if (token == null || token.equals("")) return null;
        RSAPublicKey keyRSA = getAsPublicKey();
        SystemUser requester = AuthenticationUtil.authenticate(keyRSA, token);
        this.authorizationPlugin.isAuthorized(requester, operation);
        return requester;
    }

    public String getVersionNumber() {
        String buildNumber = null;
        buildNumber = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.BUILD_NUMBER_KEY,
                    ConfigurationPropertyDefaults.BUILD_NUMBER);
        return SystemConstants.API_VERSION_NUMBER + "-" + buildNumber;
    }
}
