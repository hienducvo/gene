package org.oncokb.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by hienvo on 4/12/2017.
 */

@Entity
public class Variant implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Integer id;

    private String alteration;

    private String term;

    private boolean generallyTruncating;

    @ManyToOne
    @JoinColumn(name = "geneId")
    private Gene gene;

    public Variant() {
    }

    public Variant(String alteration, String term, boolean isGenerallyTruncating) {
        this.alteration = alteration;
        this.term = term;
        this.generallyTruncating = isGenerallyTruncating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlteration() {
        return alteration;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
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
