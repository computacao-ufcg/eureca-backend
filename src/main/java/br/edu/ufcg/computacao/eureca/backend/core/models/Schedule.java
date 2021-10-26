package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Collection;
import java.util.Objects;

public class Schedule {

    private Integer availability;
    private Collection<ClassSchedule> schedule;

    public Schedule(Integer availability, Collection<ClassSchedule> schedule) {
        this.availability = availability;
        this.schedule = schedule;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Collection<ClassSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Collection<ClassSchedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule1 = (Schedule) o;
        return schedule.equals(schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }
}
