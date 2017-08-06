package org.oncokb.controller;

import io.swagger.annotations.ApiParam;
import org.oncokb.controller.util.PaginationUtil;
import org.oncokb.dto.VariantItemDTO;
import org.oncokb.service.VariantService;
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
 * Created by hienvo on 4/13/2017.
 */
@RestController
@RequestMapping("/api")
public class VariantController {

    @Autowired
    VariantService variantService;

    @RequestMapping(value = "/variants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VariantItemDTO>> getAllVariants(@ApiParam Pageable pageable)
            throws URISyntaxException {
        final Page<VariantItemDTO> page = variantService.getAllVariants(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/variants");

        List<VariantItemDTO> items = page.getContent();
        for (VariantItemDTO variantItemDTO : items) {
            variantItemDTO.add(linkTo(methodOn(VariantController.class).getAllVariants(pageable)).withSelfRel());
        }

        return new ResponseEntity<>(items, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/variants/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VariantItemDTO> getVariantById(@PathVariable Integer id) {

        Optional<VariantItemDTO> dtoOptional = variantService.getVariantById(id);
        VariantItemDTO variantItemDTO = dtoOptional.get();
        if (variantItemDTO != null) {
            variantItemDTO.add(linkTo(methodOn(VariantController.class).getVariantById(id)).withSelfRel());

        }

        return dtoOptional.map(v -> new ResponseEntity<VariantItemDTO>(v, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
