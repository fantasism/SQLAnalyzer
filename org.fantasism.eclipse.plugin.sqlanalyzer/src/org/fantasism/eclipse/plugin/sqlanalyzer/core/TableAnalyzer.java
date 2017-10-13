/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableQueryLateral;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.emf.common.util.EList;
import org.fantasism.eclipse.plugin.sqlanalyzer.SqlAnalyzerContext;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query.QueryType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class TableAnalyzer {

    private SqlAnalyzerContext context;

    public TableAnalyzer(SqlAnalyzerContext context) {
        this.context = context;
    }

    public <T extends Query> void analyze(T owner, TableReference table) {

        System.out.println("[BEGIN] " + table.getClass().getSimpleName() + " : " + table);

        if (table instanceof TableExpression) {
            analyzeTableExpression(owner, (TableExpression) table);

        } else if (table instanceof TableJoined) {
            analyzeJoined(owner, (TableJoined) table);

        } else if (table instanceof TableNested) {
            analyzeNested(owner, (TableNested) table);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        System.out.println("[END  ] " + table.getClass().getSimpleName() + " : " + table);

    }

    private <T extends Query> void analyzeTableExpression(T owner, TableExpression table){
        if (table instanceof TableFunction) {
            analyzeFunction(owner, (TableFunction) table);

        } else if (table instanceof TableQueryLateral) {
            analyzeQueryLateral(owner, (TableQueryLateral) table);

        } else if (table instanceof TableInDatabase) {
            analyzeInDatabase(owner, (TableInDatabase) table);

        } else if (table instanceof WithTableReference) {
            analyzeWithTableReference(owner, (WithTableReference) table);

        } else if (table instanceof QueryExpressionBody) {
            analyzeBody(owner, (QueryExpressionBody) table);

        } else {
            throw new RuntimeException("サポートしてません。");
        }
    }



    private <T extends Query> void analyzeFunction(T owner, TableFunction tableRef) {

        tableRef.getFunction(); // TODO
        tableRef.getParameterList(); // TODO

        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeQueryLateral(T owner, TableQueryLateral tableRef) {

        Query subquery = new Query(owner);

        analyze(subquery, tableRef.getQuery());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeInDatabase(T owner, TableInDatabase tableRef) {
        owner.getJoinTableNames().add(tableRef.getName());
    }

    private <T extends Query> void analyzeWithTableReference(T owner, WithTableReference tableRef) {
        Query subquery = new Query(owner);

        analyze(subquery, tableRef.getWithTableSpecification().getWithTableQueryExpr());

        owner.getSubQueryList().add(subquery);
    }

    private <T extends Query> void analyzeJoined(T owner, TableJoined tableRef) {
        ConditionAnalyzer conditionAnalyzer = context.getConditionAnalyzer();

        tableRef.getJoinOperator(); // TODO

        analyze(owner, tableRef.getTableRefLeft());

        analyze(owner, tableRef.getTableRefRight());

        conditionAnalyzer.analyze(owner, tableRef.getJoinCondition());
    }

    private <T extends Query> void analyzeNested(T owner, TableNested table) {

        Query subquery = new Query(owner);

        analyze(subquery, table.getNestedTableRef());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeBody(T owner, QueryExpressionBody query) {

        if (query instanceof QuerySelect) {
            analyzeSelect(owner, (QuerySelect) query);

        } else if (query instanceof QueryCombined) {
            analyzeCombined(owner, (QueryCombined) query);

        } else if (query instanceof QueryValues) {
            analyzeValues(owner, (QueryValues) query);

        } else if (query instanceof QueryNested) {
            analyzeNested(owner, (QueryNested) query);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeSelect(T owner, QuerySelect select) {

        ResultAnalyzer resultAnalyzer = context.getResultAnalyzer();
        ConditionAnalyzer conditionAnalyzer = context.getConditionAnalyzer();
        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        owner.setQueryType(QueryType.QUERY_SELECT);

        select.isDistinct(); // TODO

        for (QueryResultSpecification result : (EList<QueryResultSpecification>) select.getSelectClause()) {
            resultAnalyzer.analyze(owner, result);
        }

        for (TableReference table : (EList<TableReference>) select.getFromClause()) {
            analyze(owner, table);
        }

        if (select.getWhereClause() != null) {
            conditionAnalyzer.analyze(owner, select.getWhereClause());
        } else {
        }

        for (ValueExpression groupByValue : (EList<ValueExpression>) select.getGroupByClause()) {
            valueAnalyzer.analyze(owner, groupByValue);
        }

        if (select.getHavingClause() != null) {
            conditionAnalyzer.analyze(owner, select.getHavingClause());
        } else {
        }
    }

    private <T extends Query> void analyzeCombined(T owner, QueryCombined query) {
        Query subquery = new Query(owner.getOwnerQuery());

        query.getCombinedOperator(); // TODO

        analyze(owner, query.getLeftQuery());

        analyze(subquery, query.getRightQuery());

        getNestedLastQuery(owner).setUnionQuery(subquery);
    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeValues(T owner, QueryValues query) {
        for (ValuesRow row : (EList<ValuesRow>) query.getValuesRowList()) {
            row.getInsertStatement(); // TODO
            row.getExprList(); // TODO
            row.getQueryValues().getValuesRowList(); // TODO
        }

        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeNested(T owner, QueryNested query) {
        Query subquery = new Query(owner);

        analyze(subquery, query.getNestedQuery());

        getNestedLastQuery(owner).setUnionQuery(subquery);
    }

    private Query getNestedLastQuery(Query query) {

        Query lastQuery = query;

        while (lastQuery.getUnionQuery() != null) {
            lastQuery = lastQuery.getUnionQuery();
        }

        return lastQuery;

    }

}
