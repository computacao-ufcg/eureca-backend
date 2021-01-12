package br.edu.ufcg.computacao.eureca.backend.core.tests.holders;

import br.edu.ufcg.computacao.eureca.backend.core.holders.EurecaAsPublicKeyHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.interfaces.RSAPublicKey;

@SpringBootTest
@AutoConfigureMockMvc
public class EurecaAsPublicKeyHolderTest {

    @Autowired
    private RSAPublicKey asPublicKey;

    @Autowired
    private EurecaAsPublicKeyHolder instance;

    @Before
    public void setUp() throws EurecaException {
        instance = EurecaAsPublicKeyHolder.getInstance();
        asPublicKey = instance.getAsPublicKey();
    }

    @Test
    public void getInstanceTest() {
        Assert.assertNotEquals(instance, null);
    }

    @Test
    public void getAsPublicKeyTest() throws EurecaException { }

}
