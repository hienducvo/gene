package org.oncokb.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.oncokb.entity.Gene;
import org.oncokb.entity.Variant;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hienvo on 4/13/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeneItemDTO extends ResourceSupport implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer entrezGeneId;

    private String hugoSymbol;

    private List<String> geneAliases;

    private boolean oncogene;

    private boolean tsg;

    private List<VariantItemDTO> variants = new ArrayList<>();


    public GeneItemDTO() {
    }

    @JsonCreator
    public GeneItemDTO(Integer geneId, String symbol, String geneAliases, List<Variant> variants, boolean oncogene, @JsonProperty("tsg") boolean tsg) {
        this.entrezGeneId = geneId;
        this.hugoSymbol = symbol;
        if (geneAliases != null && geneAliases.length() > 0) {
            geneAliases = geneAliases.replace("[","");
            geneAliases = geneAliases.replace("]","");
            String[] aliasArr = geneAliases.split(",");
            this.geneAliases = new ArrayList<String>();
            for (int i = 0; i < aliasArr.length; i++) {
                this.geneAliases.add(aliasArr[i]);
            }
        }
        //to avoid looping data in a bidirectional relationship between Gene and Variant
        for(Variant variant : variants){
            VariantItemDTO variantItemDTO = new VariantItemDTO();
            BeanUtils.copyProperties(variant, variantItemDTO);
            this.variants.add(variantItemDTO);
        }

       // this.variants = variants;
        this.oncogene = oncogene;
        this.tsg = tsg;
    }

    public GeneItemDTO(Gene gene) {
        this(gene.getEntrezGeneId(), gene.getHugoSymbol(), gene.getGeneAliases(), gene.getVariants(), gene.isOncogene(), gene.isTsg());
    }

    public Integer getEntrezGeneId() {
        return entrezGeneId;
    }

    public void setEntrezGeneId(Integer entrezGeneId) {
        this.entrezGeneId = entrezGeneId;
    }

    public String getHugoSymbol() {
        return hugoSymbol;
    }

    public void setHugoSymbol(String hugoSymbol) {
        this.hugoSymbol = hugoSymbol;
    }

    public List<String> getGeneAliases() {
        return geneAliases;
    }

    public void setGeneAliases(List<String> geneAliases) {
        this.geneAliases = geneAliases;
    }

    public boolean isOncogene() {
        return oncogene;
    }

    public void setOncogene(boolean oncogene) {
        this.oncogene = oncogene;
    }

    public boolean isTsg() {
        return tsg;
    }

    public void setTsg(boolean tsg) {
        this.tsg = tsg;
    }

    public List<VariantItemDTO> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantItemDTO> variants) {
        this.variants = variants;
    }
}
