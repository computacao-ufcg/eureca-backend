package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class SubjectSchedule {

    private Subject subject;
    private Map<String, Schedule> schedules;

    public SubjectSchedule(Subject subject, Map<String, Schedule> schedules) {
        this.subject = subject;
        this.schedules = schedules;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Map<String, Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Map<String, Schedule> schedules) {
        this.schedules = schedules;
    }

    public Collection<String> getClassCodes() {
        return schedules.keySet();
    }

    public Schedule getSchedule(String classCode) {
        return schedules.get(classCode);
    }

    public Collection<Schedule> getAllSchedules() {
        return new ArrayList<>(schedules.values());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectSchedule that = (SubjectSchedule) o;
        return subject.equals(that.subject) && schedules.equals(that.schedules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, schedules);
    }

    @Override
    public String toString() {
        return "SubjectSchedule{" +
                "subject=" + subject +
                ", schedules=" + schedules +
                '}';
    }
}
