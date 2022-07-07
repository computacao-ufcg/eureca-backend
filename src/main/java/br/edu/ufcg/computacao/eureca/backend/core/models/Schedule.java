package br.edu.ufcg.computacao.eureca.backend.core.models;

import br.edu.ufcg.computacao.eureca.backend.core.util.EurecaUtil;

import java.util.Collection;
import java.util.Objects;

public class Schedule {

    private int availability;
    private String classCode;
    private Collection<ClassSchedule> schedule;

    public Schedule(int availability, String classCode, Collection<ClassSchedule> schedule) {
        this.availability = availability;
        this.classCode = classCode;
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

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Collection<ClassSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Collection<ClassSchedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule1 = (Schedule) o;
        return schedule.equals(schedule1.schedule);
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
