package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import org.apache.log4j.Logger;

public class StudentsStatisticsController {
    private Logger LOGGER = Logger.getLogger(StudentsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public StudentsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public ActivesSummaryResponse getActivesSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getActivesSummaryResponse(from, to);
    }

    public AlumniSummaryResponse getAlumniSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getAlumniSummaryResponse(from, to);
    }

    public DropoutsSummaryResponse getDropoutsSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getDropoutsSummaryResponse(from, to);
    }

    public DelayedSummaryResponse getDelayedSummaryResponse(String from, String to) {
        return this.dataAccessFacade.getDelayedSummaryResponse(from, to);
    }

    public StudentsSummaryResponse getStudentsSummaryResponse(String from, String to) {
        ActivesSummary activesSummary = this.dataAccessFacade.getActivesSummary(from, to);
        AlumniSummary alumniSummary = this.dataAccessFacade.getAlumniSummary(from, to);
        DelayedSummary delayedSummary = this.dataAccessFacade.getDelayedSummary(from, to);
        DropoutsSummary dropoutSummary = this.dataAccessFacade.getDropoutsSummary(from, to);

        return new StudentsSummaryResponse(activesSummary, alumniSummary, delayedSummary, dropoutSummary);
    }
}