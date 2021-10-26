package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Objects;

public class ClassSchedule {

    private WeekDay weekDay;
    private String startHour;
    private String endHour;

    public ClassSchedule(WeekDay weekDay, String startHour, String endHour) {
        this.weekDay = weekDay;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassSchedule that = (ClassSchedule) o;
        return weekDay == that.weekDay && startHour.equals(that.startHour) && endHour.equals(that.endHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekDay, startHour, endHour);
    }

    @Override
    public String toString() {
        return "ClassSchedule{" +
                "weekDay=" + weekDay +
                ", startHour='" + startHour + '\'' +
                ", endHour='" + endHour + '\'' +
                '}';
    }
}
