package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.util.MetricsCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataAccessFacadeHolder.class, DataAccessFacade.class})
public class MetricsCalculatorTest {

    private MetricsCalculator metricsCalculator;

    @Before
    public void setUp() {
        mockDataAccessFacadeHolder();
        this.metricsCalculator = MetricsCalculator.getInstance();
    }

    //Test case: Call the getInstance method and tests a successfully return.
    @Test
    public void getInstanceTest() {
        assertNotEquals(null,this.metricsCalculator);
    }

    private void mockDataAccessFacadeHolder() {
        DataAccessFacadeHolder dataAccessFacadeHolder = DataAccessFacadeHolder.getInstance();
        DataAccessFacade dataAccessFacade = mock(DataAccessFacade.class);
        dataAccessFacadeHolder.setDataAccessFacade(dataAccessFacade);
        PowerMockito.mockStatic(DataAccessFacadeHolder.class);
        BDDMockito.given(DataAccessFacadeHolder.getInstance()).willReturn(dataAccessFacadeHolder);
    }
}
