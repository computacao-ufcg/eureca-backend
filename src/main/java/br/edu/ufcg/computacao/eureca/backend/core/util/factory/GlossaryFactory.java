package br.edu.ufcg.computacao.eureca.backend.core.util.factory;

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
            case RETENTION:
                return (T) createRetentionGlossary(lang);
            case ALUMNI:
                return (T) createAlumniGlossary(lang);
            default:
                return (T) new GlossaryFields();
        }
    }

    private static GlossaryFields createStudentGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
            default:
                return new PortugueseStudentsGlossary().getGlossary();
        }
    }

    private static GlossaryFields createSubjectGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
            default:
                return new PortugueseSubjectsGlossary().getGlossary();
        }
    }

    private static GlossaryFields createEnrollmentGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
            default:
                return new PortugueseEnrollmentsGlossary().getGlossary();
        }
    }

    private static GlossaryFields createTeacherGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
            default:
                return new PortugueseTeachersGlossary().getGlossary();
        }
    }

    private static GlossaryFields createRetentionGlossary(String lang) {
        switch(lang) {
            case SystemConstants.PORTUGUESE:
            default:
                return new PortugueseRetentionGlossary().getGlossary();
        }
    }

    private static GlossaryFields createAlumniGlossary(String lang) {
        switch (lang) {
            case SystemConstants.PORTUGUESE:
            default:
                return new PortugueseAlumniGlossary().getGlossary();
        }
    }
}
