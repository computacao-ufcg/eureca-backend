package br.edu.ufcg.computacao.eureca.backend.core.tests.holders;

import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class PropertiesHolderTest {

    // instance of the Properties object.
    private Properties properties;

    // instance of the PropertiesHolder object.
    private PropertiesHolder instance;

    // creation of a base PropertiesHolder object that will be used in the tests.
    @Before
    public void setUp() throws EurecaException {
        instance = PropertiesHolder.getInstance();
    }

    // test case: verify if the instance is not null.
    @Test
    public void verifyIfInstanceNotNullTest() {
        Assert.assertNotEquals(instance, null);
    }

}
