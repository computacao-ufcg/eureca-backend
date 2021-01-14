package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.models.AttemptsSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttemptsSummaryTest {

    private AttemptsSummary attemptsSummary;

    // setup: creation of a base object that will be used in the tests.
    @Before
    public void setUp() {
        Registration registration = new Registration("117219999");
        int attemptedCredits = 24;

        attemptsSummary = new AttemptsSummary(registration, attemptedCredits);
    }

    // test case: check if the registration is correct.
    @Test
    public void getRegistrationTest() {
        Assert.assertEquals(attemptsSummary.getRegistration().getRegistration(), "117219999");
    }

    // test case: changes the registration fee of an instance.
    @Test
    public void setRegistrationTest() {
        Registration newRegistration = new Registration("11728999");
        attemptsSummary.setRegistration(newRegistration);
        Assert.assertEquals(attemptsSummary.getRegistration().getRegistration(), "11728999");
    }

    // test case: check if the attempted credits are correct.
    @Test
    public void getAttemptedCredits() {
        Assert.assertEquals(attemptsSummary.getAttemptedCredits(), 24);
    }

    // test case: changes the attempted credits value of an instance.
    @Test
    public void setAttemptedCreditsTest() {
        int newAttemptedCredits = 20;
        attemptsSummary.setAttemptedCredits(newAttemptedCredits);
        Assert.assertEquals(attemptsSummary.getAttemptedCredits(), 20);
    }

}
