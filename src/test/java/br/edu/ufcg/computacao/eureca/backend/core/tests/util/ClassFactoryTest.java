package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.constants.ConfigurationPropertyKeys;
import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.backend.core.plugins.AuthorizationPlugin;
import br.edu.ufcg.computacao.eureca.backend.core.util.ClassFactory;
import static org.junit.jupiter.api.Assertions.assertThrows;
import br.edu.ufcg.computacao.eureca.common.exceptions.FatalErrorException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassFactoryTest {

     private ClassFactory classFactory;

    @Before
    public void setUp() {
        this.classFactory = new ClassFactory();
    }

    // Test case: Calls the createClass method with invalid className and tests if the right exception is thrown.
    @Test
    public void createClassInvalidClassName() {
        String invalidClassname = "k";

        assertThrows(FatalErrorException.class, () -> {
            this.classFactory.createClass(invalidClassname);
        });
    }

    // Test case: Calls the createClass method and tests a successfully return.
    @Test
    public void createClassTest() {
        // set up
        String className = PropertiesHolder.getInstance().getProperty(ConfigurationPropertyKeys.AUTHORIZATION_PLUGIN_CLASS_KEY);

        // exercise
        Object authorizationPlugin = this.classFactory.createClass(className);

        // verify
        assertTrue(authorizationPlugin instanceof AuthorizationPlugin);
    }

}
