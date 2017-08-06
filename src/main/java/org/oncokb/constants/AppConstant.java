package org.oncokb.constants;

/**
 * Created by hienvo on 4/14/2017.
 */
public class AppConstant {

    public static final String REST_API = "rest.api";
    public static final String EXPORT_FILE_PATH = "export.file";
    public static final String GENE_ID = "entrezGeneId";
    public static final String HUGO_SYMBOL = "hugoSymbol";
    public static final String ALIASES = "aliases";
    public static final String ONCOGENE = "oncogene";
    public static final String TSG = "tsg";
    public static final String ALTERATION = "alteration";
    public static final String TERM = "term";
    public static final String GENERALLY_TRUNCATING = "generallyTruncating";
    public static final String SQL_GENE_INSERT = "REPLACE INTO gene (entrez_gene_id, gene_aliases, hugo_symbol, oncogene, tsg) VALUES (:entrezGeneId, :aliases, :hugoSymbol,:oncogene,:oncogene)";
    public static final String SQL_VARIANT_INSERT = "INSERT INTO variant (alteration,  generally_truncating, term, gene_id) VALUES (:alteration, :generallyTruncating, :term, :entrezGeneId)";
    public static final String BATCH_JOB = "batchJob";
    public static final String STEP_1 = "step1";
    public static final String STEP_2 = "step2";
    public static final String JOB_FINISHED = "!!! JOB FINISHED!";
}
