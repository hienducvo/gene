package org.oncokb.repository;

import org.oncokb.entity.Gene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by hienvo on 4/13/2017.
 */
public interface GeneRepository extends JpaRepository<Gene, Integer> {

    Optional<Gene> findOneByEntrezGeneId(Integer id);
}
