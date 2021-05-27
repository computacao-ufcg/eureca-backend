package br.edu.ufcg.computacao.eureca.backend.core;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectSummaryResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsSummaryItemResponse;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.SubjectsSummaryResponseOld;
import br.edu.ufcg.computacao.eureca.backend.constants.PortugueseGlossary;
import br.edu.ufcg.computacao.eureca.backend.core.dao.DataAccessFacade;
import br.edu.ufcg.computacao.eureca.backend.core.holders.DataAccessFacadeHolder;
import br.edu.ufcg.computacao.eureca.backend.core.models.MetricSummary;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectStatisticsItem;
import org.apache.log4j.Logger;

import java.util.*;
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

    public Map<String, Collection<SubjectsSummaryResponseOld>> getSubjectsStatistics(String from, String to) {
        Map<String, Collection<SubjectsSummaryResponseOld>> completeMap = null;//this.dataAccessFacade.getSubjectSummary();
        Map<String, Collection<SubjectsSummaryResponseOld>> resultMap = new HashMap<>();
        completeMap.forEach((term, summary) -> {
            if (term.compareTo(from) >= 0 && term.compareTo(to) <= 0) {
                resultMap.put(term, summary);
            }
        });
        return resultMap;
    }

    public SubjectSummaryResponse getSubjectStatisticsMock() {
        MetricSummary metrics = new MetricSummary(20, 30, 25);
        SubjectStatisticsItem mandatory = new SubjectStatisticsItem(1980, 196, 30,metrics,metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem optional = new SubjectStatisticsItem(400, 50, 10, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem elective = new SubjectStatisticsItem(840, 56,15,metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectStatisticsItem complementary = new SubjectStatisticsItem(450, 30, 10, metrics, metrics, metrics, metrics, metrics, metrics);
        SubjectSummaryResponse summary = new SubjectSummaryResponse("2017", "1980.1", "2020.1", mandatory, optional, elective, complementary);
        return summary;
    }

    public Collection<SubjectsSummaryItemResponse> getSubjectsStatisticsCSV() {
        List<SubjectsSummaryItemResponse> response = new ArrayList<>();
        response.add(new SubjectsSummaryItemResponse("eda", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseGlossary()));
        response.add(new SubjectsSummaryItemResponse("leda", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseGlossary()));
        response.add(new SubjectsSummaryItemResponse("sistemas operacionais", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseGlossary()));
        response.add(new SubjectsSummaryItemResponse("redes", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseGlossary()));
        response.add(new SubjectsSummaryItemResponse("logica", 0.827, 0.12, 0.09, 0.04, 30, 0.2, "2017.2", "2019.2", new PortugueseGlossary()));
        return response;
    }
}
