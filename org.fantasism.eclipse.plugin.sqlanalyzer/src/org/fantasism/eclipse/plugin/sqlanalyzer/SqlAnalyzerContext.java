/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer;

import org.fantasism.eclipse.plugin.sqlanalyzer.core.ConditionAnalyzer;
import org.fantasism.eclipse.plugin.sqlanalyzer.core.OthersAnalyzer;
import org.fantasism.eclipse.plugin.sqlanalyzer.core.QueryAnalyzer;
import org.fantasism.eclipse.plugin.sqlanalyzer.core.ResultAnalyzer;
import org.fantasism.eclipse.plugin.sqlanalyzer.core.StatementAnalyzer;
import org.fantasism.eclipse.plugin.sqlanalyzer.core.TableAnalyzer;
import org.fantasism.eclipse.plugin.sqlanalyzer.core.ValueAnalyzer;
import org.fantasism.eclipse.plugin.sqlanalyzer.listener.DefaultSqlAnalyzeListener;
import org.fantasism.eclipse.plugin.sqlanalyzer.listener.SqlAnalyzeListener;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SqlAnalyzerContext {

    private String queryName;

    private Query rootQuery;

    private SqlAnalyzeListener listener;

    private ValueAnalyzer valueAnalyzer;

    private ConditionAnalyzer conditionAnalyzer;

    private QueryAnalyzer queryAnalyzer;

    private StatementAnalyzer statementAnalyzer;

    private TableAnalyzer tableAnalyzer;

    private ResultAnalyzer resultAnalyzer;

    private OthersAnalyzer othersAnalyzer;

    public SqlAnalyzerContext(SqlAnalyzeListener listener) {

        this.queryName = "UNKNOWN QUERY NAME";
        this.rootQuery = new Query(null);
        this.listener = listener;

        this.valueAnalyzer = new ValueAnalyzer(this);
        this.conditionAnalyzer = new ConditionAnalyzer(this);
        this.queryAnalyzer = new QueryAnalyzer(this);
        this.statementAnalyzer = new StatementAnalyzer(this);
        this.tableAnalyzer = new TableAnalyzer(this);
        this.resultAnalyzer = new ResultAnalyzer(this);
        this.othersAnalyzer =new OthersAnalyzer(this);

    }

    public SqlAnalyzerContext() {

        this(new DefaultSqlAnalyzeListener());

    }

    /**
     * queryNameを取得します。
     * @return queryName
     */
    public String getQueryName() {
        return queryName;
    }

    /**
     * queryNameを設定します。
     * @param queryName queryName
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    /**
     * rootQueryを取得します。
     * @return rootQuery
     */
    public Query getRootQuery() {
        return rootQuery;
    }

    /**
     * rootQueryを設定します。
     * @param rootQuery rootQuery
     */
    public void setRootQuery(Query rootQuery) {
        this.rootQuery = rootQuery;
    }

    /**
     * listenerを取得します。
     * @return listener
     */
    public SqlAnalyzeListener getListener() {
        return listener;
    }

    /**
     * listenerを設定します。
     * @param listener listener
     */
    public void setListener(SqlAnalyzeListener listener) {
        this.listener = listener;
    }

    /**
     * valueAnalyzerを取得します。
     * @return valueAnalyzer
     */
    public ValueAnalyzer getValueAnalyzer() {
        return valueAnalyzer;
    }

    /**
     * valueAnalyzerを設定します。
     * @param valueAnalyzer valueAnalyzer
     */
    public void setValueAnalyzer(ValueAnalyzer valueAnalyzer) {
        this.valueAnalyzer = valueAnalyzer;
    }

    /**
     * conditionAnalyzerを取得します。
     * @return conditionAnalyzer
     */
    public ConditionAnalyzer getConditionAnalyzer() {
        return conditionAnalyzer;
    }

    /**
     * conditionAnalyzerを設定します。
     * @param conditionAnalyzer conditionAnalyzer
     */
    public void setConditionAnalyzer(ConditionAnalyzer conditionAnalyzer) {
        this.conditionAnalyzer = conditionAnalyzer;
    }

    /**
     * queryAnalyzerを取得します。
     * @return queryAnalyzer
     */
    public QueryAnalyzer getQueryAnalyzer() {
        return queryAnalyzer;
    }

    /**
     * queryAnalyzerを設定します。
     * @param queryAnalyzer queryAnalyzer
     */
    public void setQueryAnalyzer(QueryAnalyzer queryAnalyzer) {
        this.queryAnalyzer = queryAnalyzer;
    }

    /**
     * statementAnalyzerを取得します。
     * @return statementAnalyzer
     */
    public StatementAnalyzer getStatementAnalyzer() {
        return statementAnalyzer;
    }

    /**
     * statementAnalyzerを設定します。
     * @param statementAnalyzer statementAnalyzer
     */
    public void setStatementAnalyzer(StatementAnalyzer statementAnalyzer) {
        this.statementAnalyzer = statementAnalyzer;
    }

    /**
     * tableAnalyzerを取得します。
     * @return tableAnalyzer
     */
    public TableAnalyzer getTableAnalyzer() {
        return tableAnalyzer;
    }

    /**
     * tableAnalyzerを設定します。
     * @param tableAnalyzer tableAnalyzer
     */
    public void setTableAnalyzer(TableAnalyzer tableAnalyzer) {
        this.tableAnalyzer = tableAnalyzer;
    }

    /**
     * resultAnalyzerを取得します。
     * @return resultAnalyzer
     */
    public ResultAnalyzer getResultAnalyzer() {
        return resultAnalyzer;
    }

    /**
     * resultAnalyzerを設定します。
     * @param resultAnalyzer resultAnalyzer
     */
    public void setResultAnalyzer(ResultAnalyzer resultAnalyzer) {
        this.resultAnalyzer = resultAnalyzer;
    }

    /**
     * othersAnalyzerを取得します。
     * @return othersAnalyzer
     */
    public OthersAnalyzer getOthersAnalyzer() {
        return othersAnalyzer;
    }

    /**
     * othersAnalyzerを設定します。
     * @param othersAnalyzer othersAnalyzer
     */
    public void setOthersAnalyzer(OthersAnalyzer othersAnalyzer) {
        this.othersAnalyzer = othersAnalyzer;
    }

}
