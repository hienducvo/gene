package org.oncokb.dto;

/**
 * Created by hienvo on 4/12/2017.
 */
public class VariantDTO {

    private GeneDTO gene;

    private ConSequenceDTO consequence;

    private String alteration;

    private String name;

    private String refResidues;

    private Integer proteinStart;

    private Integer proteinEnd;

    private String variantResidues;

    public GeneDTO getGene() {
        return gene;
    }

    public void setGene(GeneDTO gene) {
        this.gene = gene;
    }

    public ConSequenceDTO getConsequence() {
        return consequence;
    }

    public void setConsequence(ConSequenceDTO consequence) {
        this.consequence = consequence;
    }

    public String getAlteration() {
        return alteration;
    }

    public void setAlteration(String alteration) {
        this.alteration = alteration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefResidues() {
        return refResidues;
    }

    public void setRefResidues(String refResidues) {
        this.refResidues = refResidues;
    }

    public Integer getProteinStart() {
        return proteinStart;
    }

    public void setProteinStart(Integer proteinStart) {
        this.proteinStart = proteinStart;
    }

    public Integer getProteinEnd() {
        return proteinEnd;
    }

    public void setProteinEnd(Integer proteinEnd) {
        this.proteinEnd = proteinEnd;
    }

    public String getVariantResidues() {
        return variantResidues;
    }

    public void setVariantResidues(String variantResidues) {
        this.variantResidues = variantResidues;
    }
}
