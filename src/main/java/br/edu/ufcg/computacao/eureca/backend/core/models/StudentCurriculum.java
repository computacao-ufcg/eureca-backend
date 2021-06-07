package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectData;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.SubjectKey;

import java.util.Collection;
import java.util.TreeSet;

public class StudentCurriculum {
    private int currentTerm;
    private Collection<String> completed;
    private Collection<String> enabled;
    private Collection<String> disabled;

    public StudentCurriculum(int currentTerm) {
        this.currentTerm = currentTerm;
        this.completed = new TreeSet<>();
        this.enabled = new TreeSet<>();
        this.disabled = new TreeSet<>();
    }

    public int getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(int currentTerm) {
        this.currentTerm = currentTerm;
    }

    public Collection<String> getCompleted() {
        return completed;
    }

    public void setCompleted(Collection<String> completed) {
        this.completed = completed;
    }

    public Collection<String> getEnabled() {
        return enabled;
    }

    public void setEnabled(Collection<String> enabled) {
        this.enabled = enabled;
    }

    public Collection<String> getDisabled() {
        return disabled;
    }

    public void setDisabled(Collection<String> disabled) {
        this.disabled = disabled;
    }

    public boolean hasCompleted(SubjectKey k, SubjectData v) {
        if (this.completed.contains((k.getCurriculumCode() + ":" + k.getSubjectCode()))) return true;
        for (String subjectCode : v.getEquivalentCodesList()) {
            if (this.completed.contains((k.getCurriculumCode() + ":" + subjectCode))) {
                this.completed.add((k.getCurriculumCode() + ":" + k.getSubjectCode()));
                return true;
            }
        }
        return false;
    }
}
