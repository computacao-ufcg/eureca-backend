package br.edu.ufcg.computacao.eureca.backend.core.tests.holders;

import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(DataAccessFacadeHolder.class)
public class DataAccessFacadeHolderTest {

    private DataAccessFacadeHolder instance;

    @Before
    public void setUp() throws EurecaException {
        instance = DataAccessFacadeHolder.getInstance();
    }

    @Test
    public void getInstanceTest() {
        Assert.assertNotEquals(instance, null);
    }

    @Test
    public void getDataAccessFacadeBeforeExecutionTest() {
        Assert.assertEquals(instance.getDataAccessFacade(), null);
    }

}
