package org.oncokb.repository;

import org.oncokb.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by hienvo on 4/13/2017.
 */
public interface VariantRepository extends JpaRepository<Variant, Integer> {

    Optional<Variant> findOneById(Integer id);
}
