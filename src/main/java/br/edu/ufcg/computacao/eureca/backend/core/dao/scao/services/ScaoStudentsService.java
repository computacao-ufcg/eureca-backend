package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.services;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models.ScaoStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Service
public class ScaoStudentsService {

    @Autowired
    private ScaoStudentsRepository<ScaoStudent, String> ScaoStudentsDAO;

    private static ScaoStudentsService instance;

    public static ScaoStudentsService getInstance() {
        return instance;
    }

    @PostConstruct
    private void registerInstance() {
        instance = this;
    }

    public Collection<ScaoStudent> getStudentsPerCourse(String courseCode) {
        return  ScaoStudentsDAO.findStudentsPerCourse(courseCode);
    }
}

