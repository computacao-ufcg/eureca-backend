package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SubjectsStatisticsController {
    private Logger LOGGER = Logger.getLogger(SubjectsStatisticsController.class);

    private DataAccessFacade dataAccessFacade;

    public SubjectsStatisticsController() {
        this.dataAccessFacade = DataAccessFacadeHolder.getInstance().getDataAccessFacade();
    }

    private <T> Collection<String> getSliderLabel(Collection<T> terms, Function<T, String> function) {
        return terms
                .stream()
                .map(function)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Map<String, Collection<SubjectsSummaryResponse>> getSubjectsStatistics(String from, String to) {
        Map<String, Collection<SubjectsSummaryResponse>> completeMap = null;//this.dataAccessFacade.getSubjectSummary();
        Map<String, Collection<SubjectsSummaryResponse>> resultMap = new HashMap<>();
        completeMap.forEach((term, summary) -> {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                resultMap.put(term, summary);
            }
        });
        return resultMap;
    }
}
