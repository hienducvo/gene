package org.oncokb.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.oncokb.entity.Gene;
import org.oncokb.entity.Variant;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by hienvo on 4/12/2017.
 */
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class VariantItemDTO extends ResourceSupport implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer variantId;

    private String alteration;

    private String term;

    private boolean generallyTruncating;

    private GeneItemDTO gene;

    public VariantItemDTO(){}

    @JsonCreator
    public VariantItemDTO(Integer id, String alteration, String term, boolean isGenerallyTruncating, @JsonProperty("gene") Gene gene){
        this.variantId = id;
        this.alteration = alteration;
        this.term = term;
        this.generallyTruncating = isGenerallyTruncating;
        if(gene != null){
            gene.setVariants(null);
            this.gene = new GeneItemDTO();
            this.gene.setVariants(null);
            BeanUtils.copyProperties(gene, this.gene);

        }
    }

    public VariantItemDTO(Variant variant) {
        this(variant.getId(), variant.getAlteration(), variant.getTerm(), variant.isGenerallyTruncating(), variant.getGene());
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public String getAlteration() {
        return alteration;
    }

    public void setAlteration(String alteration) {
        this.alteration = alteration;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public boolean isGenerallyTruncating() {
        return generallyTruncating;
    }

    public void setGenerallyTruncating(boolean generallyTruncating) {
        this.generallyTruncating = generallyTruncating;
    }

    public GeneItemDTO getGene() {
        return gene;
    }

    public void setGene(GeneItemDTO gene) {
        this.gene = gene;
    }
}
