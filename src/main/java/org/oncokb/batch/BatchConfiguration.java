package org.oncokb.batch;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.oncokb.constants.AppConstant;
import org.oncokb.dto.ItemLineDTO;
import org.oncokb.dto.VariantDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.PathResource;
import org.springframework.web.client.RestTemplate;


/**
 * Created by hienvo on 4/12/2017.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public Environment env;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private DataSource dataSource;

    
    @Bean
    public ItemReader<VariantDTO> apiReader() {

        return new VariantItemReader(env.getProperty(AppConstant.REST_API), restTemplate);
    }


    @Bean
    ItemWriter<ItemLineDTO> fileWriter() {
        FlatFileItemWriter<ItemLineDTO> csvFileWriter = new FlatFileItemWriter<>();

        csvFileWriter.setResource(new PathResource(env.getProperty(AppConstant.EXPORT_FILE_PATH)));
        LineAggregator<ItemLineDTO> lineAggregator = createLineAggregator();
        csvFileWriter.setLineAggregator(lineAggregator);

        return csvFileWriter;
    }


    @Bean
    public FlatFileItemReader<ItemLineDTO> fileReader() {
        FlatFileItemReader<ItemLineDTO> reader = new FlatFileItemReader<ItemLineDTO>();
        reader.setResource(new PathResource(env.getProperty(AppConstant.EXPORT_FILE_PATH)));

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB);
        delimitedLineTokenizer.setNames(new String[]{
                AppConstant.GENE_ID,
                AppConstant.HUGO_SYMBOL,
                AppConstant.ALIASES,
                AppConstant.ONCOGENE,
                AppConstant.TSG,
                AppConstant.ALTERATION,
                AppConstant.TERM,
                AppConstant.GENERALLY_TRUNCATING});

        reader.setLineMapper(new DefaultLineMapper<ItemLineDTO>() {{
            setLineTokenizer(delimitedLineTokenizer);
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ItemLineDTO>() {{
                setTargetType(ItemLineDTO.class);
            }});
        }});
        return reader;
    }

    public CompositeItemWriter<ItemLineDTO> dbWriter() {

        CompositeItemWriter<ItemLineDTO> cWriter = new CompositeItemWriter<ItemLineDTO>();


        JdbcBatchItemWriter<ItemLineDTO> geneWriter = new JdbcBatchItemWriter<ItemLineDTO>();
        geneWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ItemLineDTO>());
        geneWriter.setSql(AppConstant.SQL_GENE_INSERT);
        geneWriter.setDataSource(dataSource);
        geneWriter.afterPropertiesSet();

        JdbcBatchItemWriter<ItemLineDTO> variantWriter = new JdbcBatchItemWriter<ItemLineDTO>();
        variantWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ItemLineDTO>());
        variantWriter.setSql(AppConstant.SQL_VARIANT_INSERT);
        variantWriter.setDataSource(dataSource);
        variantWriter.afterPropertiesSet();

        List<ItemWriter<? super ItemLineDTO>> mWriter = new ArrayList<ItemWriter<? super ItemLineDTO>>();
        mWriter.add(geneWriter);
        mWriter.add(variantWriter);
        cWriter.setDelegates(mWriter);

        return cWriter;
    }


    private LineAggregator<ItemLineDTO> createLineAggregator() {
        DelimitedLineAggregator<ItemLineDTO> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
        FieldExtractor<ItemLineDTO> fieldExtractor = createFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);

        return lineAggregator;
    }

    private FieldExtractor<ItemLineDTO> createFieldExtractor() {
        BeanWrapperFieldExtractor<ItemLineDTO> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[]{
                AppConstant.GENE_ID,
                AppConstant.HUGO_SYMBOL,
                AppConstant.ALIASES,
                AppConstant.ONCOGENE,
                AppConstant.TSG,
                AppConstant.ALTERATION,
                AppConstant.TERM,
                AppConstant.GENERALLY_TRUNCATING});
        return extractor;
    }

    @Bean
    public ItemLineProcessor geneProcessor() {
        return new ItemLineProcessor();
    }


    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get(AppConstant.BATCH_JOB)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get(AppConstant.STEP_1)
                .<VariantDTO, ItemLineDTO>chunk(10)
                .reader(apiReader())
                .processor(geneProcessor())
                .writer(fileWriter())
                .build();
    }

    public Step step2() {
        return stepBuilderFactory.get(AppConstant.STEP_2)
                .<ItemLineDTO, ItemLineDTO>chunk(10)
                .reader(fileReader())
                .writer(dbWriter())
                .build();
    }


}
