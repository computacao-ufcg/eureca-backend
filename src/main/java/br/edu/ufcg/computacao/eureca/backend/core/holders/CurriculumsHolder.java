package br.edu.ufcg.computacao.eureca.backend.core.holders;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurriculumsHolder {

    private static CurriculumsHolder instance;
    private Map<String, Collection<String>> availableCurriculumsPerCourseCode;

    private CurriculumsHolder() {
        this.loadAvailableCurriculums();
    }

    public static CurriculumsHolder getInstance() {
        synchronized (CurriculumsHolder.class) {
            if (instance == null) {
                instance = new CurriculumsHolder();
            }
            return instance;
        }
    }

    private void loadAvailableCurriculums() {
        this.availableCurriculumsPerCourseCode = new HashMap<>();
        this.availableCurriculumsPerCourseCode.put("14102100", Arrays.asList("1990", "2017", "1999", "1979"));
    }

    public Collection<String> getAvailableCurriculums(String courseCode) {
        return this.availableCurriculumsPerCourseCode.get(courseCode);
    }
}
