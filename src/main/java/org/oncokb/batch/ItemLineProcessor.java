package org.oncokb.batch;

import org.oncokb.dto.ConSequenceDTO;
import org.oncokb.dto.GeneDTO;
import org.oncokb.dto.ItemLineDTO;
import org.oncokb.dto.VariantDTO;

/**
 * Created by hienvo on 4/12/2017.
 */
public class ItemLineProcessor implements org.springframework.batch.item.ItemProcessor<VariantDTO, ItemLineDTO> {
    @Override
    public ItemLineDTO process(VariantDTO variantDTO) throws Exception {
        if (variantDTO == null)
            return null;

        ItemLineDTO itemLineDTO = null;
        GeneDTO geneDTO = variantDTO.getGene();
        //Filtering: Only process genes that are listed as an “OncoGene”
        if (geneDTO != null && geneDTO.isOncogene()) {
            ConSequenceDTO conSequenceDTO = variantDTO.getConsequence();
            itemLineDTO = new ItemLineDTO();
            itemLineDTO.setAliases(geneDTO.getGeneAliases().toString());
            itemLineDTO.setAlteration(variantDTO.getAlteration());
            itemLineDTO.setEntrezGeneId(geneDTO.getEntrezGeneId());
            itemLineDTO.setHugoSymbol(geneDTO.getHugoSymbol());
            itemLineDTO.setOncogene(geneDTO.isOncogene());
            itemLineDTO.setTsg(geneDTO.isTsg());
            itemLineDTO.setTerm(conSequenceDTO.getTerm());
            itemLineDTO.setGenerallyTruncating(conSequenceDTO.isGenerallyTruncating());
        }

        return itemLineDTO;
    }
}
