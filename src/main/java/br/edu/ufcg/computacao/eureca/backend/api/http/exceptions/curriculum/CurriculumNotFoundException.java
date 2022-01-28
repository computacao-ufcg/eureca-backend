package br.edu.ufcg.computacao.eureca.backend.api.http.exceptions.curriculum;

import br.edu.ufcg.computacao.eureca.backend.constants.Messages;
import br.edu.ufcg.computacao.eureca.common.exceptions.NotFoundException;

public class CurriculumNotFoundException extends NotFoundException {

    public CurriculumNotFoundException(String courseCode, String curriculumCode) {
        super(String.format(Messages.INVALID_COURSE_OR_CURRICULUM_S_S, courseCode, curriculumCode));
    }
}