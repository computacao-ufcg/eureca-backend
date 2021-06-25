package br.edu.ufcg.computacao.eureca.backend.core.util;

import br.edu.ufcg.computacao.eureca.backend.constants.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.GlossaryType;

public class GlossaryFactory {

    public static <T extends GlossaryFields> T createGlossary(String lang, GlossaryType type) {
        switch (type) {
            case STUDENT:
                return (T) createStudentGlossary(lang);
            case SUBJECT:
                return (T) createSubjectGlossary(lang);
            case ENROLLMENT:
                return (T) createEnrollmentGlossary(lang);
            case TEACHER:
                return (T) createTeacherGlossary(lang);
            default:
                return (T) new GlossaryFields();
        }
    }

    private static GlossaryFields createStudentGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
                return new PortugueseStudentsGlossary().getGlossary();
            default:
                return new PortugueseStudentsGlossary().getGlossary();
        }
    }

    private static GlossaryFields createSubjectGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
                return new PortugueseSubjectsGlossary().getGlossary();
            default:
                return new PortugueseSubjectsGlossary().getGlossary();
        }
    }

    private static GlossaryFields createEnrollmentGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
                return new PortugueseEnrollmentsGlossary().getGlossary();
            default:
                return new PortugueseEnrollmentsGlossary().getGlossary();
        }
    }

    private static GlossaryFields createTeacherGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
                return new PortugueseTeachersGlossary().getGlossary();
            default:
                return new PortugueseTeachersGlossary().getGlossary();
        }
    }
}
