package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.SummaryPerTerm;
import br.edu.ufcg.computacao.eureca.backend.core.models.Student;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollectionUtil {

    public static String getFirstTermFromSummaries(Collection<? extends SummaryPerTerm> summaries) {
        if (summaries.size() == 0) return "9999.9";
        return getTermsFromSummaries(summaries).first();
    }

    public static String getLastTermFromSummaries(Collection<? extends SummaryPerTerm> summaries) {
        if (summaries.size() == 0) return "0000.0";
        return getTermsFromSummaries(summaries).last();
    }

    private static TreeSet<String> getTermsFromSummaries(Collection<? extends SummaryPerTerm> summaryPerTerms) {
        return summaryPerTerms
                .stream()
                .map(SummaryPerTerm::getTerm)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public static String getFirstTermFromStudents(Collection<Student> students) {
        if (students.size() == 0) return "9999.9";
        return getTermsFromStudents(students).first();
    }

    public static String getLastTermFromStudents(Collection<Student> students) {
        if (students.size() == 0) return "0000.0";
        return getTermsFromStudents(students).last();
    }

    private static TreeSet<String> getTermsFromStudents(Collection<Student> students) {
        return students
                .stream()
                .map(Student::getAdmissionTerm)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
