package br.edu.ufcg.computacao.eureca.backend.core.tests.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;
import br.edu.ufcg.computacao.eureca.backend.core.models.AttemptsSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AttemptsSummaryTest {

    private AttemptsSummary attemptsSummary;

    @Before
    public void setUp() {
        Registration registration = new Registration("117219999");
        int attemptedCredits = 24;

        attemptsSummary = new AttemptsSummary(registration, attemptedCredits);
    }

    @Test
    public void getRegistrationTest() {
        Assert.assertEquals(attemptsSummary.getRegistration().getRegistration(), "117219999");
    }

    @Test
    public void setRegistrationTest() {
        Registration newRegistration = new Registration("11728999");
        attemptsSummary.setRegistration(newRegistration);
        Assert.assertEquals(attemptsSummary.getRegistration().getRegistration(), "11728999");
    }

    @Test
    public void getAttemptedCredits() {
        Assert.assertEquals(attemptsSummary.getAttemptedCredits(), 24);
    }

    @Test
    public void setAttemptedCreditsTest() {
        int newAttemptedCredits = 20;
        attemptsSummary.setAttemptedCredits(newAttemptedCredits);
        Assert.assertEquals(attemptsSummary.getAttemptedCredits(), 20);
    }

}
