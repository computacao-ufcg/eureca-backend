package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.services;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models.ScaoEnrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Service
public class ScaoEnrollmentService {

    @Autowired
    private ScaoEnrollmentRepository<ScaoEnrollment, String> ScaoEnrollmentDAO;

    private static ScaoEnrollmentService instance;

    public static ScaoEnrollmentService getInstance() {
        return instance;
    }

    @PostConstruct
    private void registerInstance() {
        instance = this;
    }

    public Collection<ScaoEnrollment> getEnrollmentsByRegistration(String registration){
        return ScaoEnrollmentDAO.getEnrollmentsByRegistration(registration);
    }
}
