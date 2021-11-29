package br.edu.ufcg.computacao.eureca.backend.api.http.response.preenrollment;

import br.edu.ufcg.computacao.eureca.backend.core.models.Schedule;

import java.util.Objects;

public class ScheduleResponse {

    private String subjectName;
    private Schedule schedule;

    public ScheduleResponse(String subjectName, Schedule schedule) {
        this.subjectName = subjectName;
        this.schedule = schedule;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleResponse that = (ScheduleResponse) o;
        return Objects.equals(subjectName, that.subjectName) && Objects.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName, schedule);
    }

    @Override
    public String toString() {
        return "ScheduleResponse{" +
                "subjectName='" + subjectName + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
