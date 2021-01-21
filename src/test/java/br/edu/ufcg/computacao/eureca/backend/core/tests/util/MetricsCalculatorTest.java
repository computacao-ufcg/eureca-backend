package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.models.AttemptsSummary;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.*;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataAccessFacade.class)
public class MetricsCalculatorTest {

    @InjectMocks
    private static MetricsCalculator instance;

    @Mock
    private Map<Registration, Integer> attemptsMap;

    @Before
    public void setUp() {
        attemptsMap = new HashMap<>();
        Registration reg1 = new Registration("123456789");
        Registration reg2 = new Registration("323131331");
        Registration reg3 = new Registration("355125512");
        attemptsMap.put(reg1, 24);
        attemptsMap.put(reg2, 20);
        attemptsMap.put(reg3, 18);
    }

    @Test
    public void getInstanceTest() {
        mockInstance();
        instance = MetricsCalculator.getInstance();
    }

    public void mockInstance() {
        DataAccessFacade dataAccessFacade = mock(DataAccessFacade.class);
        Mockito.when(dataAccessFacade.getAttemptsSummary()).thenReturn((Collection<AttemptsSummary>) attemptsMap);
        PowerMockito.mockStatic(DataAccessFacade.class);
//        BDDMockito.given(DataAccessFacade.getInstance()).willReturn(dataAccessFacadeHolder);
    }

}
