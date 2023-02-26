package br.edu.ufcg.computacao.eureca.backend.core.dao.scao.services;

import br.edu.ufcg.computacao.eureca.backend.core.dao.scao.models.ScaoPlaceOfBirth;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;

@Repository
public interface ScaoPlaceOfBirthRepository<T, ID extends Serializable> extends JpaRepository<ScaoPlaceOfBirth, String> {

    @Cacheable("placeOfBirth")
    @Query("SELECT ss FROM ScaoPlaceOfBirth ss")
    Collection<ScaoPlaceOfBirth> getScaoPlacesOfBirth();
}
