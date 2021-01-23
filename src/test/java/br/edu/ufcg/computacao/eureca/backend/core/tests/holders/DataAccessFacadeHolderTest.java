package br.edu.ufcg.computacao.eureca.backend.core.tests.holders;

import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.common.exceptions.EurecaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DataAccessFacadeHolderTest {

    private DataAccessFacadeHolder dataAccessFacadeHolder;

    // set up: creation of a base object of the DataAccessFacadeHolder type that will be used in the tests.
    @Before
    public void setUp() {
        this.dataAccessFacadeHolder = DataAccessFacadeHolder.getInstance();
    }

    // Test case: check if the DataAccessFacadeHolder instance was actually created.
    @Test
    public void getInstanceTest() {
        Assert.assertNotEquals(this.dataAccessFacadeHolder, null);
    }

    // Test case: Call the getDataAccessFacade method and tests a successfully return.
    @Test
    public void getDataAccessFacadeTest() {
        // set up
        DataAccessFacade dataAccessFacadeMock = mock(DataAccessFacade.class);
        this.dataAccessFacadeHolder.setDataAccessFacade(dataAccessFacadeMock);

        // exercise
        DataAccessFacade dataAccessFacade = this.dataAccessFacadeHolder.getDataAccessFacade();

        // exercise
        assertEquals(dataAccessFacadeMock, dataAccessFacade);
    }

    // Test case: Call the setDataAccessFacade method and tests a successfully return.
    @Test
    public void setDataAccessFacadeTest() {
        // set up
        DataAccessFacade dataAccessFacadeMock = mock(DataAccessFacade.class);

        // exercise
        this.dataAccessFacadeHolder.setDataAccessFacade(dataAccessFacadeMock);

        // exercise
        DataAccessFacade dataAccessFacade = this.dataAccessFacadeHolder.getDataAccessFacade();
        assertEquals(dataAccessFacadeMock, dataAccessFacade);
    }

}
