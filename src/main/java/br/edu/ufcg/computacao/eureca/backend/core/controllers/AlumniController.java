package br.edu.ufcg.computacao.eureca.backend.core.controllers;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.AlumniDigest;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import org.apache.log4j.Logger;

import java.util.Collection;

public class AlumniController {
    private Logger LOGGER = Logger.getLogger(AlumniController.class);

    private DataAccessFacade dataAccessFacade;

    public AlumniController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    public Collection<AlumniDigest> getAlumniDigest(String courseCode, String from, String to) {
        return this.dataAccessFacade.getAlumniPerStudentSummary(courseCode, from, to);
    }
}
