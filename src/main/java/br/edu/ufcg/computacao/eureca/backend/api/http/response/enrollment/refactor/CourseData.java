package br.edu.ufcg.computacao.eureca.backend.api.http.response.enrollment.refactor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseData {

    private String courseCode;
    private String curriculumCode;
    private String subjectCode;
    private String subjectName;
    private String term;
    private String classId;

    public CourseData(String courseCode, String curriculumCode, String subjectCode, String subjectName, String term, String classId) {
        this.courseCode = courseCode;
        this.curriculumCode = curriculumCode;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.term = term;
        this.classId = classId;
    }
}
