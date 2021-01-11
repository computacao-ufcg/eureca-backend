package br.edu.ufcg.computacao.eureca.backend.core.tests.holders;

import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.interfaces.RSAPublicKey;

public class EurecaAsPublicKeyHolderTest {

    private RSAPublicKey asPublicKey;

    private EurecaAsPublicKeyHolder instance;

    @Before
    public void setUp() throws EurecaException {
        instance = EurecaAsPublicKeyHolder.getInstance();
    }

    @Test
    public void getInstanceTest() {
        Assert.assertNotEquals(instance, null);
    }

    @Test
    public void getAsPublicKeyTest() {

    }

}
