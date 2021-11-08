package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EurecaUtil {
    public static <T> Collection<T> intersection(Collection<T> list1, Collection<T> list2) {
        return list1.stream().distinct().filter(list2::contains).collect(Collectors.toList());
    }

    public static <T> Collection<T> difference(Collection<T> list1, Collection<T> list2) {
        List<T> result = new ArrayList<>(list1);
        result.removeAll(list2);
        return result;
    }

    public static SubjectType getSubjectType(Subject subject) {
        switch (subject.getType()) {
            case "M":
                return SubjectType.MANDATORY;
            case "O":
                return SubjectType.OPTIONAL;
            case "C":
                return SubjectType.COMPLEMENTARY;
            case "E":
                return SubjectType.ELECTIVE;
            default:
                return null;
        }
    }
}
