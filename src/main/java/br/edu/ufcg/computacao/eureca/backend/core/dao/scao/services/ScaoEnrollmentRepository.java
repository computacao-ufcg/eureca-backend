package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.services;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models.ScaoEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;

@Repository
public interface ScaoEnrollmentRepository <T, ID extends Serializable> extends JpaRepository<ScaoEnrollment, String> {
    @Query("SELECT se FROM ScaoEnrollment se WHERE se.id.registration = ?1")
    Collection<ScaoEnrollment> getEnrollmentsByRegistration(String registration);
}
