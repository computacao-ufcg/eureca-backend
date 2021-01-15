package br.edu.ufcg.computacao.eureca.backend.core.tests.holders;

import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(DataAccessFacadeHolder.class)
public class DataAccessFacadeHolderTest {

    // instance of the DataAccessFacadeHolder object.
    private DataAccessFacadeHolder instance;

    // set up: creation of a base object of the DataAccessFacadeHolder type that will be used in the tests.
    @Before
    public void setUp() throws EurecaException {
        instance = DataAccessFacadeHolder.getInstance();
    }

    // test case 1: check if the DataAccessFacadeHolder instance was actually created.
    @Test
    public void getInstanceTest() {
        Assert.assertNotEquals(instance, null);
    }

}
