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
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.EurecaOperation;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.IdCode;
import br.edu.ufcg.computacao.eureca.backend.core.models.mapentries.StudentCourse;
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
            String admission = item.getAcademicData().getSemestre_ingresso();
            if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                ActiveSummaryResponse studentSummary = new ActiveSummaryResponse(
                        item.getId().getMatricula(),
                        item.getAcademicData().getSemestre_ingresso(),
                        item.getAcademicData().getPer_int(),
                        computePercentage(item));
                activeStudentsSummary.add(studentSummary);
            }
        });
        return activeStudentsSummary;
    }

    public Collection<ActiveDataResponse> getActivesCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ACTIVES);
        Collection<ActiveDataResponse> activeStudentsData = new ArrayList<>();
        Collection<Student> actives = StatisticsHolder.getInstance().getAllActives();
        actives.forEach(item -> {
            String admission = item.getAcademicData().getSemestre_ingresso();
            if (admission != null && admission.compareTo(from) >= 0 && admission.compareTo(to) <= 0) {
                ActiveDataResponse studentData = new ActiveDataResponse(item.getId().getMatricula(),
                        item.getPersonalData(), item.getAcademicData());
                activeStudentsData.add(studentData);
            }
        });
        return activeStudentsData;
    }

    private double computePercentage(Student item) {
        StudentCourse data = item.getAcademicData();
        double totalCreditsFulfilled = data.getCred_obrig_int() + data.getCred_opt_int() + data.getCred_comp_int();
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
            double averageGPA = item.getCra_medio();
            int termAlumniCount = item.getQtd_egressos();
            String term = item.getPeriodo_conclusao();
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
        Map<String, Collection<String>> map = StatisticsHolder.getInstance().getAlumniByGraduationTerm();
        for (Map.Entry<String, Collection<String>> entry : map.entrySet()) {
            String term = entry.getKey();
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                Collection<String> cpfs = entry.getValue();
                int termAlumniCount = cpfs.size();
                double termAccumulatedGPA = 0;
                for (String cpf : cpfs) {
                    Student alumnus = StatisticsHolder.getInstance().getAlumni().get(cpf);
                    termAccumulatedGPA += alumnus.getAcademicData().getCra();
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
        Map<String, Collection<String>> dropouts = StatisticsHolder.getInstance().getDropoutByLeaveTerm();
        dropouts.forEach((k, v) -> {
            if (k.compareTo(from) >= 0 && k.compareTo(to) <= 0) {
                int dropoutsCount[] = new int[13];
                v.forEach(item -> {
                    Student dropout = StatisticsHolder.getInstance().getDropouts().get(item);
                    dropoutsCount[dropout.getAcademicData().getId_situacao_vinculo()-1]++;
                });
                DropoutClassification dropoutClassification = new DropoutClassification(dropoutsCount[0],
                        dropoutsCount[12], dropoutsCount[1], dropoutsCount[2], dropoutsCount[3], dropoutsCount[4],
                        dropoutsCount[5], dropoutsCount[6], dropoutsCount[7], dropoutsCount[8]);
                dropoutSummaryResponses.add(new DropoutSummaryResponse(k, dropoutClassification));
            }
        });
        return dropoutSummaryResponses;
    }

    public Collection<DropoutDataResponse> getDropoutsCSV(String token, String from, String to) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_DROPOUTS_CSV);
        Collection<DropoutDataResponse> dropoutDataResponses = new ArrayList<>();
        Map<String, Student> dropouts = StatisticsHolder.getInstance().getDropouts();
        dropouts.forEach((k, v) -> {
            String leaveTerm = v.getAcademicData().getSemestre_situacao();
            if (leaveTerm != null && leaveTerm.compareTo(from) >= 0 && leaveTerm.compareTo(to) <= 0) {
                IdCode idCota = new IdCode(v.getAcademicData().getId_cota());
                String cota = MapsHolder.getInstance().getValue("Cota", idCota).toString();
                IdCode idEstadoCivil = new IdCode(v.getPersonalData().getId_estado_civil());
                String estadoCivil = MapsHolder.getInstance().getValue("EstadoCivil", idEstadoCivil).toString();
                IdCode idGenero = new IdCode(v.getPersonalData().getId_genero());
                String genero = MapsHolder.getInstance().getValue("Genero", idGenero).toString();
                IdCode idDropoutCause = new IdCode(v.getAcademicData().getId_situacao_vinculo());
                String dropoutCause = MapsHolder.getInstance().getValue("SituacaoVinculo", idDropoutCause).toString();
                DropoutDataResponse summary = new DropoutDataResponse(cota, v.getAcademicData().getCra(),
                        v.getAcademicData().getCred_comp_int(), v.getAcademicData().getCred_obrig_int(),
                        v.getAcademicData().getCred_opt_int(), v.getAcademicData().getCurriculo(), estadoCivil,
                        genero, v.getAcademicData().getIea(), v.getId().getMatricula(), v.getAcademicData().getMat_inst(),
                        v.getAcademicData().getMc(), v.getAcademicData().getMedia_geral_ingresso(),
                        v.getAcademicData().getMob_estudantil(), dropoutCause, v.getAcademicData().getSemestre_ingresso(),
                        v.getAcademicData().getPer_int(), v.getAcademicData().getTranc());
                dropoutDataResponses.add(summary);
            }
        });
        return dropoutDataResponses;
    }

    public Collection<AlumnusBasicData> getAlumniBasicData(String token) throws EurecaException {
        authenticateAndAuthorize(token, EurecaOperation.GET_ALUMNI_BASIC_DATA);
        Collection<AlumnusBasicData> alumniBasicData = new ArrayList<>();
        Map<String, Student> alumni = StatisticsHolder.getInstance().getAlumni();
        alumni.forEach((k, v) -> {
            AlumnusBasicData basicData = new AlumnusBasicData(v.getId().getMatricula(), v.getPersonalData().getNome(),
                    2, 1, v.getAcademicData().getSemestre_ingresso(),
                    v.getAcademicData().getSemestre_situacao());
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
