package org.oncokb.dto;

import java.util.List;

/**
 * Created by hienvo on 4/12/2017.
 */
public class GeneDTO {

    private Integer entrezGeneId;
    private String hugoSymbol;
    private String name;
    private boolean oncogene;
    private String curatedIsoform;
    private String curatedRefSeq;
    private List<String> geneAliases;
    private boolean tsg;

    public GeneDTO(){}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOncogene() {
        return oncogene;
    }

    public void setOncogene(boolean oncogene) {
        this.oncogene = oncogene;
    }

    public String getCuratedIsoform() {
        return curatedIsoform;
    }

    public void setCuratedIsoform(String curatedIsoform) {
        this.curatedIsoform = curatedIsoform;
    }

    public String getCuratedRefSeq() {
        return curatedRefSeq;
    }

    public void setCuratedRefSeq(String curatedRefSeq) {
        this.curatedRefSeq = curatedRefSeq;
    }

    public List<String> getGeneAliases() {
        return geneAliases;
    }

    public void setGeneAliases(List<String> geneAliases) {
        this.geneAliases = geneAliases;
    }

    public boolean isTsg() {
        return tsg;
    }

    public void setTsg(boolean tsg) {
        this.tsg = tsg;
    }
}
