package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import br.edu.ufcg.computacao.eureca.backend.core.models.ClassSchedule;
import br.edu.ufcg.computacao.eureca.backend.core.models.Schedule;
import br.edu.ufcg.computacao.eureca.backend.core.models.WeekDay;

import java.util.ArrayList;
import java.util.Collection;

public class ScheduleData implements EurecaMapValue {

    private Integer availability;
    private String schedule;

    public ScheduleData() {
    }

    public ScheduleData(Integer availability, String schedule) {
        this.availability = availability;
        this.schedule = schedule;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Schedule createSchedule() {
        return new Schedule(availability, extractSchedule());
    }

    private Collection<ClassSchedule> extractSchedule() {
        Collection<ClassSchedule> schedules = new ArrayList<>();
        String[] schedulesString = this.schedule.split(",");

        for (String scheduleString : schedulesString) {
            String[] data = scheduleString.split("\\|");
            String weekdayString = data[0];
            WeekDay weekDay = WeekDay.toEnum(weekdayString);

            String hourString = data[1];
            String startHour = hourString.split("-")[0];
            String endHour = hourString.split("-")[1];

            schedules.add(new ClassSchedule(weekDay, startHour, endHour));
        }

        return schedules;
    }

}
