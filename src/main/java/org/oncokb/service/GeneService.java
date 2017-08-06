package org.oncokb.service;

import java.util.Optional;

import org.oncokb.dto.GeneItemDTO;
import org.oncokb.repository.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hienvo on 4/13/2017.
 */
@Service
public class GeneService {

    @Autowired
    GeneRepository geneRepository;

    @Transactional(readOnly = true)
    public Page<GeneItemDTO> getAllGenes(Pageable pageable) {
        return geneRepository.findAll(pageable).map(GeneItemDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<GeneItemDTO> getGeneById(Integer id) {
        return geneRepository.findOneByEntrezGeneId(id).map(GeneItemDTO::new);
    }


}
