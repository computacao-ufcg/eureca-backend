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

    // instance of the RSAPublicKey object.
    @Autowired
    private RSAPublicKey asPublicKey;

    // instance of the EurecaAsPublicKeyHolder object.
    @Autowired
    private EurecaAsPublicKeyHolder instance;

    // set up: creation of the objects that will be used in the tests.
    @Before
    public void setUp() throws EurecaException {
        instance = EurecaAsPublicKeyHolder.getInstance();
        asPublicKey = instance.getAsPublicKey();
    }

}
