/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.emf.common.util.EList;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query.QueryType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class QueryStatementAnalyzer {

    public <T extends Query> void analyze(T owner, QueryStatement statement) {

        if (statement instanceof QuerySelectStatement) {
            analyzeSelectStatement(owner, (QuerySelectStatement)statement);

        } else if (statement instanceof QueryInsertStatement) {
            analyzeInsertStatement(owner, (QueryInsertStatement)statement);

        } else if (statement instanceof QueryUpdateStatement) {
            analyzeUpdateStatement(owner, (QueryUpdateStatement)statement);

        } else if (statement instanceof QueryDeleteStatement) {
            analyzeDeleteStatement(owner, (QueryDeleteStatement)statement);

        } else {
            System.out.println(statement);
            throw new RuntimeException("サポートしてません。");
        }

    }


    private <T extends Query> void analyzeSelectStatement(T owner, QuerySelectStatement statement) {
        System.out.println(QuerySelectStatement.class + ":" + statement);
        QueryAnalyzer analyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();
        analyzer.analyze(owner, statement.getQueryExpr());
    }

    private <T extends Query> void analyzeInsertStatement(T owner, QueryInsertStatement statement) {
        System.out.println(QueryInsertStatement.class + ":" + statement);

        TableReferenceAnalyzer tableAnalyzer = SqlAnalyzerManager.getInstance().getTableExpressionAnalyzer();
        ValueExpressionAnalyzer valueAnalyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();
        QueryAnalyzer queryAnalyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();

        tableAnalyzer.analyze(owner, (TableReference) statement.getTargetTable());

        for (ValueExpression valueExpr : (EList<ValueExpression>) statement.getTargetColumnList()) {
            valueAnalyzer.analyze(owner, valueExpr);
        }

        if (statement.getSourceValuesRowList().size() > 0) {
            owner.setQueryType(QueryType.QUERY_INSERT);
            for (Object a : statement.getSourceValuesRowList()) {
                throw new RuntimeException("サポートしてません。" + a);
            }

        } else if (statement.getSourceQuery() != null) {
            Query subquery = new Query(owner);

            owner.setQueryType(QueryType.QUERY_SELECT_INSERT);
            owner.setNestedQuery(subquery);

            queryAnalyzer.analyze(subquery, statement.getSourceQuery());

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeUpdateStatement(T owner, QueryUpdateStatement statement) {
        System.out.println(QueryUpdateStatement.class + ":" + statement);

        owner.setQueryType(QueryType.QUERY_UPDATE);
        SearchConditionAnalyzer scAnalyzer = SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer();
        TableReferenceAnalyzer tableAnalyzer = SqlAnalyzerManager.getInstance().getTableExpressionAnalyzer();

        tableAnalyzer.analyze(owner, (TableReference) statement.getTargetTable());

        scAnalyzer.analyze(owner, statement.getWhereClause());

        ValueExpressionAnalyzer valueAnalyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();
        for (UpdateAssignmentExpression assignExpr : (EList<UpdateAssignmentExpression>) statement.getAssignmentClause()) {
            for (ValueExpression expr : (EList<ValueExpression>) assignExpr.getTargetColumnList()) {
                valueAnalyzer.analyze(owner, expr);
            }

            analyzeUpdateSource(owner, assignExpr.getUpdateSource());
        }


    }

    private <T extends Query> void analyzeUpdateSource(T owner, UpdateSource source) {
        if (source instanceof UpdateSourceExprList) {
            ValueExpressionAnalyzer valueAnalyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();
            UpdateSourceExprList exprList = (UpdateSourceExprList)source;
            for (ValueExpression expr : (EList<ValueExpression>) exprList.getValueExprList()) {
                valueAnalyzer.analyze(owner, expr);
            }

        } else if (source instanceof UpdateSourceQuery) {
            QueryAnalyzer queryAnalyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();

            Query subquery = new Query(owner);
            owner.getSubQueryList().add(subquery);

            UpdateSourceQuery updateSourceQuery = (UpdateSourceQuery) source;
            queryAnalyzer.analyze(subquery, updateSourceQuery.getQueryExpr());

        } else {

        }
    }

    private <T extends Query> void analyzeDeleteStatement(T owner, QueryDeleteStatement statement) {
        System.out.println(QueryDeleteStatement.class + ":" + statement);

        owner.setQueryType(QueryType.QUERY_DELETE);
        SearchConditionAnalyzer scAnalyzer = SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer();
        TableReferenceAnalyzer tableAnalyzer = SqlAnalyzerManager.getInstance().getTableExpressionAnalyzer();

        tableAnalyzer.analyze(owner, (TableReference) statement.getTargetTable());

        scAnalyzer.analyze(owner, statement.getWhereClause());
    }

}
