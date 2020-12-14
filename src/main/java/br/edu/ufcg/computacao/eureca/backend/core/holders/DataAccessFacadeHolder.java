package br.edu.ufcg.computacao.eureca.backend.core.holders;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import org.apache.log4j.Logger;

public class DataAccessFacadeHolder {
    private Logger LOGGER = Logger.getLogger(DataAccessFacadeHolder.class);

    private DataAccessFacade dataAccessFacade;
    private static DataAccessFacadeHolder instance;

    private DataAccessFacadeHolder() {
    }

    public static synchronized DataAccessFacadeHolder getInstance() {
        if (instance == null) {
            instance = new DataAccessFacadeHolder();
        }
        return instance;
    }

    public DataAccessFacade getDataAccessFacade() {
        return dataAccessFacade;
    }

    public void setDataAccessFacade(DataAccessFacade dataAccessFacade) {
        this.dataAccessFacade = dataAccessFacade;
    }
}
