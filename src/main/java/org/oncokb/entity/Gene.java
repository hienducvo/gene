package org.oncokb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hienvo on 4/12/2017.
 */

@Entity
public class Gene implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer entrezGeneId;

    private String hugoSymbol;

    private String geneAliases;

    private boolean oncogene;

    private boolean tsg;

    @OneToMany(mappedBy = "gene")
    private List<Variant> variants = new ArrayList<>();

    public Gene() {
    }

    public Gene(Integer geneId, String symbol, String geneAliases, boolean oncogene, boolean tsg) {
        this.entrezGeneId = geneId;
        this.hugoSymbol = symbol;
        this.geneAliases = geneAliases;
        this.oncogene = oncogene;
        this.tsg = tsg;
    }

    public Gene(Gene gene) {
        this(gene.getEntrezGeneId(), gene.getHugoSymbol(), gene.getGeneAliases(), gene.isOncogene(), gene.tsg);
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

    public String getGeneAliases() {
        return geneAliases;
    }

    public void setGeneAliases(String geneAliases) {
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

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.entrezGeneId != null ? this.entrezGeneId.hashCode() : 0);
        hash = 37 * hash + (this.hugoSymbol != null ? this.hugoSymbol.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gene gene = (Gene) obj;
        if ((this.entrezGeneId == null) ? (gene.entrezGeneId != null) : !this.entrezGeneId.equals(gene.entrezGeneId)) {
            return false;
        }
        if ((this.hugoSymbol == null) ? (gene.hugoSymbol != null) : !this.hugoSymbol.equals(gene.hugoSymbol)) {
            return false;
        }


        return true;

    }
}
