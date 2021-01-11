package br.edu.ufcg.computacao.eureca.backend.core.tests.holders;

import br.edu.ufcg.computacao.eureca.backend.core.holders.PropertiesHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class PropertiesHolderTest {

    private Properties properties;
    private PropertiesHolder instance;

    @Before
    public void setUp() throws EurecaException {
        instance = PropertiesHolder.getInstance();
    }

}
