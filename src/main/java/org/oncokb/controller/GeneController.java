package org.oncokb.controller;

import io.swagger.annotations.ApiParam;
import org.oncokb.controller.util.PaginationUtil;
import org.oncokb.dto.GeneItemDTO;
import org.oncokb.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by hienvo on 4/12/2017.
 */

@RestController
@RequestMapping("/api")
public class GeneController {

    @Autowired
    GeneService geneService;

    @RequestMapping(value = "/genes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GeneItemDTO>> getAllGenes(@ApiParam Pageable pageable)
            throws URISyntaxException {
        final Page<GeneItemDTO> page = geneService.getAllGenes(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/genes");

        List<GeneItemDTO> items = page.getContent();
        for (GeneItemDTO geneItemDTO : items) {
            geneItemDTO.add(linkTo(methodOn(GeneController.class).getAllGenes(pageable)).withSelfRel());
        }

        return new ResponseEntity<>(items, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/genes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneItemDTO> getGeneById(@PathVariable Integer id) {

        Optional<GeneItemDTO> dtoOptional = geneService.getGeneById(id);
        GeneItemDTO geneItemDTO = dtoOptional.get();
        if (geneItemDTO != null) {
            geneItemDTO.add(linkTo(methodOn(GeneController.class).getGeneById(id)).withSelfRel());

        }

        return dtoOptional
                .map(gene -> new ResponseEntity<GeneItemDTO>(gene, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
