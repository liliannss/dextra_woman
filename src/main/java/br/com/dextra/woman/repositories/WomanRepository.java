package br.com.dextra.woman.repositories;

import br.com.dextra.woman.models.entites.Woman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WomanRepository extends JpaRepository<Woman, Long> {

    List<Woman> findWomanByNameContainingIgnoreCase(String name);

}