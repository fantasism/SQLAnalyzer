/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SqlAnalyzerManager {

    public ResultSpecificationAnalyzer getResultSpecificationAnalyzer() {
        return resultSpecificationAnalyzer;
    }

    public void setResultSpecificationAnalyzer(ResultSpecificationAnalyzer resultSpecificationAnalyzer) {
        this.resultSpecificationAnalyzer = resultSpecificationAnalyzer;
    }

    private static SqlAnalyzerManager instance;

    private QueryStatementAnalyzer queryStatementAnalyzer;

    private QueryAnalyzer queryAnalyzer;

    private QueryExpressionAnalyzer queryExpressionAnalyzer;

    private TableReferenceAnalyzer tableExpressionAnalyzer;

    private ValueExpressionAnalyzer valueExpressionAnalyzer;

    private PredicateAnalyzer predicateAnalyzer;

    private ResultSpecificationAnalyzer resultSpecificationAnalyzer;

    public QueryStatementAnalyzer getQueryStatementAnalyzer() {
        return queryStatementAnalyzer;
    }

    public void setQueryStatementAnalyzer(QueryStatementAnalyzer queryStatementAnalyzer) {
        this.queryStatementAnalyzer = queryStatementAnalyzer;
    }

    public QueryAnalyzer getQueryAnalyzer() {
        return queryAnalyzer;
    }

    public void setQueryAnalyzer(QueryAnalyzer queryAnalyzer) {
        this.queryAnalyzer = queryAnalyzer;
    }

    public QueryExpressionAnalyzer getQueryExpressionAnalyzer() {
        return queryExpressionAnalyzer;
    }

    public void setQueryExpressionAnalyzer(QueryExpressionAnalyzer queryExpressionAnalyzer) {
        this.queryExpressionAnalyzer = queryExpressionAnalyzer;
    }

    public TableReferenceAnalyzer getTableExpressionAnalyzer() {
        return tableExpressionAnalyzer;
    }

    public void setTableExpressionAnalyzer(TableReferenceAnalyzer tableExpressionAnalyzer) {
        this.tableExpressionAnalyzer = tableExpressionAnalyzer;
    }

    public PredicateAnalyzer getPredicateAnalyzer() {
        return predicateAnalyzer;
    }

    public void setPredicateAnalyzer(PredicateAnalyzer predicateAnalyzer) {
        this.predicateAnalyzer = predicateAnalyzer;
    }

    public ValueExpressionAnalyzer getValueExpressionAnalyzer() {
        return valueExpressionAnalyzer;
    }

    public void setValueExpressionAnalyzer(ValueExpressionAnalyzer valueExpressionAnalyzer) {
        this.valueExpressionAnalyzer = valueExpressionAnalyzer;
    }

    public SearchConditionAnalyzer getSearchConditionAnalyzer() {
        return searchConditionAnalyzer;
    }

    public void setSearchConditionAnalyzer(SearchConditionAnalyzer searchConditionAnalyzer) {
        this.searchConditionAnalyzer = searchConditionAnalyzer;
    }

    private SearchConditionAnalyzer searchConditionAnalyzer;

    private SqlAnalyzerManager() {
        this.queryStatementAnalyzer = new QueryStatementAnalyzer();
        this.queryAnalyzer = new QueryAnalyzer();
        this.queryExpressionAnalyzer = new QueryExpressionAnalyzer();
        this.tableExpressionAnalyzer = new TableReferenceAnalyzer();
        this.valueExpressionAnalyzer = new ValueExpressionAnalyzer();
        this.searchConditionAnalyzer = new SearchConditionAnalyzer();
        this.predicateAnalyzer = new PredicateAnalyzer();
        this.resultSpecificationAnalyzer = new ResultSpecificationAnalyzer();
    }

    public static SqlAnalyzerManager getInstance() {
        if (instance == null) {
            instance = new SqlAnalyzerManager();
        }
        return instance;
    }

}
