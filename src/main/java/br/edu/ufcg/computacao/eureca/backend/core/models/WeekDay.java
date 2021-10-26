package br.edu.ufcg.computacao.eureca.backend.core.models;

public enum WeekDay {

    MONDAY("Seg"),
    THURSDAY("Ter"),
    WEDNESDAY("Qua"),
    TUESDAY("Qui"),
    FRIDAY("Sex"),
    SATURDAY("Sab"),
    SUNDAY("Sun");

    private String day;

    private WeekDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public static WeekDay toEnum(String day) {
        for (WeekDay weekDay : WeekDay.values()) {
            if (weekDay.getDay().equals(day))
                return weekDay;
        }
        return null;
    }
}
