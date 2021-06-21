package br.edu.ufcg.computacao.eureca.backend.core.holders;

import br.edu.ufcg.computacao.eureca.backend.core.models.EnvironmentVariables;
import org.apache.log4j.Logger;

public class EnviromentVariablesHolder {
    private Logger LOGGER = Logger.getLogger(EnviromentVariablesHolder.class);

    private EnvironmentVariables environmentVariables;
    private static EnviromentVariablesHolder instance;

    private EnviromentVariablesHolder() {
    }

    public static synchronized EnviromentVariablesHolder getInstance() {
        if (instance == null) {
            instance = new EnviromentVariablesHolder();
        }
        return instance;
    }

    public EnvironmentVariables getEnvironmentVariables() {
        return environmentVariables;
    }

    public void setEnvironmentVariables(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }
}
