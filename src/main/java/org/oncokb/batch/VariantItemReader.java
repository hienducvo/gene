package org.oncokb.batch;

import org.oncokb.dto.VariantDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hienvo on 4/12/2017.
 */
public class VariantItemReader implements ItemReader<VariantDTO> {


    private final String url;

    private final RestTemplate restTemplate;

    private int index;

    private List<VariantDTO> variantDTOList;


    public VariantItemReader(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
        index = 0;
    }


    @Override
    public VariantDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (isDataNotLoaded()) {
            VariantDTO[] variantDTOS = restTemplate.getForObject(url, VariantDTO[].class);
            variantDTOList = Arrays.asList(variantDTOS);

        }

        VariantDTO variantDTO = null;

        if (index < variantDTOList.size()) {
            variantDTO = variantDTOList.get(index);
            index++;
        }

        return variantDTO;


    }

    private boolean isDataNotLoaded() {
        return (variantDTOList == null);
    }
}
