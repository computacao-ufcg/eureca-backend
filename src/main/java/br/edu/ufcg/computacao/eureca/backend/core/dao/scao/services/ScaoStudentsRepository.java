package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.services;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models.ScaoStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;

@Repository
public interface ScaoStudentsRepository  <T, ID extends Serializable> extends JpaRepository<ScaoStudent, String> {
    @Query("SELECT s FROM ScaoStudent s WHERE s.courseCode = ?1")
    Collection<ScaoStudent> findStudentsPerCourse(String courseCode);
}


