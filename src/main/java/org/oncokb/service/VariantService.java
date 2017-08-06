package org.oncokb.service;

import java.util.Optional;

import org.oncokb.dto.VariantItemDTO;
import org.oncokb.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hienvo on 4/13/2017.
 */
@Service
public class VariantService {

    @Autowired
    VariantRepository variantRepository;

    @Transactional(readOnly = true)
    public Page<VariantItemDTO> getAllVariants(Pageable pageable) {
        return variantRepository.findAll(pageable).map(VariantItemDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<VariantItemDTO> getVariantById(Integer id){
        return variantRepository.findOneById(id).map(VariantItemDTO::new);
    }
}
