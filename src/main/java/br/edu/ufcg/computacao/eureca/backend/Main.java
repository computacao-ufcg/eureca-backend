package br.edu.ufcg.computacao.eureca.backend;

import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyKeys;
import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.MapsHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.holders.StatisticsHolder;
import br.edu.ufcg.computacao.eureca.common.util.HomeDir;
import br.edu.ufcg.computacao.eureca.common.util.ServiceAsymmetricKeysHolder;
import br.edu.ufcg.computacao.eureca.backend.core.plugins.AuthorizationPlugin;
import br.edu.ufcg.computacao.eureca.backend.core.util.PluginInstantiator;
import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements ApplicationRunner {
    private final Logger LOGGER = Logger.getLogger(Main.class);

    @Override
    public void run(ApplicationArguments args) {
        try {
            // Setting up asymmetric cryptography
            String publicKeyFilePath = PropertiesHolder.getInstance().
                    getProperty(ConfigurationPropertyKeys.EURECA_PUBLICKEY_FILE_KEY);
            String privateKeyFilePath = PropertiesHolder.getInstance().
                    getProperty(ConfigurationPropertyKeys.EURECA_PRIVATEKEY_FILE_KEY);
            ServiceAsymmetricKeysHolder.getInstance().setPublicKeyFilePath(HomeDir.getPath() + publicKeyFilePath);
            ServiceAsymmetricKeysHolder.getInstance().setPrivateKeyFilePath(HomeDir.getPath() + privateKeyFilePath);

            // Setting up plugin
            AuthorizationPlugin authorizationPlugin = PluginInstantiator.getAuthorizationPlugin();

            // Setting up facade
            ApplicationFacade applicationFacade = ApplicationFacade.getInstance();
            applicationFacade.setAuthorizationPlugin(authorizationPlugin);

            // Reading input data
            MapsHolder.getInstance();
            StatisticsHolder.getInstance();
            LOGGER.info(Messages.ALL_SET);
        } catch (Exception e) {
            LOGGER.error(Messages.ERROR_READING_CONFIGURATION_FILE, e);
            System.exit(-1);
        }
    }
}
