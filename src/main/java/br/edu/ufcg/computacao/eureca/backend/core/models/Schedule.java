package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.util.EurecaUtil;

import java.util.Collection;
import java.util.Objects;

public class Schedule {
    private int availability;
    private String classId;
    private Collection<ClassSchedule> schedule;

    public Schedule(int availability, String classId, Collection<ClassSchedule> schedule) {
        this.availability = availability;
        this.classId = classId;
        this.schedule = schedule;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void decrementAvailability() {
        this.availability--;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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
        Schedule other = (Schedule) o;
        return schedule.equals(other.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }

    public boolean haveConflict(Schedule schedule) {
        Collection<ClassSchedule> classSchedules = schedule.getSchedule();
        return !EurecaUtil.intersection(classSchedules, this.schedule).isEmpty();
    }
}
