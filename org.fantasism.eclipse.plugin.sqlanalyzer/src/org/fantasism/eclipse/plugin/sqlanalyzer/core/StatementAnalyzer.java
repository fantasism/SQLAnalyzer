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
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
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
public class StatementAnalyzer {

    private SqlAnalyzerContext context;

    public StatementAnalyzer(SqlAnalyzerContext context) {
        this.context = context;
    }

    public <T extends Query> void analyze(T owner, QueryStatement statement) {

        System.out.println("[BEGIN] " + statement.getClass().getSimpleName() + " : " + statement);

        if (statement instanceof QuerySelectStatement) {
            analyzeSelectStatement(owner, (QuerySelectStatement) statement);

        } else if (statement instanceof QueryInsertStatement) {
            analyzeInsertStatement(owner, (QueryInsertStatement) statement);

        } else if (statement instanceof QueryUpdateStatement) {
            analyzeUpdateStatement(owner, (QueryUpdateStatement) statement);

        } else if (statement instanceof QueryDeleteStatement) {
            analyzeDeleteStatement(owner, (QueryDeleteStatement) statement);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        System.out.println("[END  ] " + statement.getClass().getSimpleName() + " : " + statement);

    }

    private <T extends Query> void analyzeSelectStatement(T owner, QuerySelectStatement statement) {

        QueryAnalyzer analyzer = context.getQueryAnalyzer();
        OthersAnalyzer othersAnalyzer = context.getOthersAnalyzer();

        analyzer.analyze(owner, statement.getQueryExpr());

        for (Object a : statement.getOrderByClause()) {
            // TODO
            throw new RuntimeException("サポートしてません。");
        }

        if (statement.getUpdatabilityExpr() != null) {
            othersAnalyzer.analyze(owner, statement.getUpdatabilityExpr());
        } else {
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeInsertStatement(T owner, QueryInsertStatement statement) {

        OthersAnalyzer othersAnalyzer = context.getOthersAnalyzer();
        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();
        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();
        QueryAnalyzer queryAnalyzer = context.getQueryAnalyzer();

        statement.isSetTargetTable(); // TODO

        for (ValueExpression valueExpr : (EList<ValueExpression>) statement.getTargetColumnList()) {
            valueAnalyzer.analyze(owner, valueExpr);
        }

        if (statement.getSourceValuesRowList().size() > 0) {
            owner.setQueryType(QueryType.QUERY_INSERT);

            for (ValuesRow row : (EList<ValuesRow>) statement.getSourceValuesRowList()) {
                othersAnalyzer.analyze(owner, row);
            }

        } else if (statement.getSourceQuery() != null) {
            Query subquery = new Query(owner);

            owner.setQueryType(QueryType.QUERY_SELECT_INSERT);
            owner.setNestedQuery(subquery);

            queryAnalyzer.analyze(subquery, statement.getSourceQuery());

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        if (statement.getTargetTable() != null) {
            // TODO
            tableAnalyzer.analyze(owner, statement.getTargetTable());
        } else {
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeUpdateStatement(T owner, QueryUpdateStatement statement) {

        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();
        OthersAnalyzer othersAnalyzer = context.getOthersAnalyzer();
        ConditionAnalyzer scAnalyzer = context.getConditionAnalyzer();

        owner.setQueryType(QueryType.QUERY_UPDATE);

        if (statement.getTargetTable() != null) {
            // TODO
            tableAnalyzer.analyze(owner, (TableReference) statement.getTargetTable());
        } else {
        }

        if (statement.getWhereClause() != null) {
            scAnalyzer.analyze(owner, statement.getWhereClause());
        } else {
        }

        for (UpdateAssignmentExpression assignExpr : (EList<UpdateAssignmentExpression>) statement
                .getAssignmentClause()) {
            othersAnalyzer.analyze(owner, assignExpr);
        }

    }

    private <T extends Query> void analyzeDeleteStatement(T owner, QueryDeleteStatement statement) {

        ConditionAnalyzer conditionAnalyzer = context.getConditionAnalyzer();
        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();

        owner.setQueryType(QueryType.QUERY_DELETE);

        statement.getWhereCurrentOfClause(); // TODO ？

        statement.isSetTargetTable(); // TODO ？

        statement.isSetWhereClause(); // TODO ？

        if (statement.getTargetTable() != null) {
            // TODO
            tableAnalyzer.analyze(owner, statement.getTargetTable());
        } else {
        }

        if (statement.getWhereClause() != null) {
            conditionAnalyzer.analyze(owner, statement.getWhereClause());
        } else {
        }

    }

}
