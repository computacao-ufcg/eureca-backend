package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.Registration;

public class AttemptsSummary implements Comparable {
    private Registration registration;
    private int attemptedCredits;

    public AttemptsSummary(Registration registration, int attemptedCredits) {
        this.registration = registration;
        this.attemptedCredits = attemptedCredits;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public void setAttemptedCredits(int attemptedCredits) {
        this.attemptedCredits = attemptedCredits;
    }

    @Override
    public int compareTo(Object o) {
        AttemptsSummary other = (AttemptsSummary) o;
        return (this.getRegistration().compareTo(other.getRegistration()));
    }
}
