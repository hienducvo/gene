package org.oncokb.dto;

/**
 * Created by hienvo on 4/13/2017.
 */
public class ItemLineDTO {

    private Integer entrezGeneId;

    private String hugoSymbol;

    private String aliases;

    private boolean oncogene;

    private boolean tsg;

    private String alteration;

    private String term;

    private boolean generallyTruncating;

    public ItemLineDTO() {
    }

    public ItemLineDTO(Integer entrezGeneId, String hugoSymbol, String aliases, boolean oncogene, boolean tsg, String alteration, String term, boolean isGenerallyTruncating) {
        this.entrezGeneId = entrezGeneId;
        this.hugoSymbol = hugoSymbol;
        this.aliases = aliases;
        this.oncogene = oncogene;
        this.tsg = tsg;
        this.alteration = alteration;
        this.term = term;
        this.generallyTruncating = isGenerallyTruncating;
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

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
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
}
