package org.oncokb.dto;

/**
 * Created by hienvo on 4/12/2017.
 */
public class ConSequenceDTO {

    private String term;
    private boolean isGenerallyTruncating;
    private String description;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public boolean isGenerallyTruncating() {
        return isGenerallyTruncating;
    }

    public void setGenerallyTruncating(boolean generallyTruncating) {
        isGenerallyTruncating = generallyTruncating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
