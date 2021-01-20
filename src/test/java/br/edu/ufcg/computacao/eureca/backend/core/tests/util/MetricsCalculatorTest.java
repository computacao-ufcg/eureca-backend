package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MetricsCalculatorTest {

    @MockBean
    private static MetricsCalculator instance;

    @Autowired
    private Map<Registration, Integer> attemptsMap;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        instance = MetricsCalculator.getInstance();
    }

    @Test
    public void getInstanceTest() {
        Assertions.assertThat(instance).isNotEqualTo(null);
    }

}
