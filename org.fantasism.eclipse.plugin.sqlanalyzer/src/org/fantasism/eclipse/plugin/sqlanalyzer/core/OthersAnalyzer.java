/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.emf.common.util.EList;
import org.fantasism.eclipse.plugin.sqlanalyzer.SqlAnalyzerContext;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class OthersAnalyzer {

    private SqlAnalyzerContext context;

    public OthersAnalyzer(SqlAnalyzerContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public <T extends Query> void analyze(T owner, UpdateAssignmentExpression assignExpr) {

        System.out.println("[BEGIN] " + assignExpr.getClass().getSimpleName() + " : " + assignExpr);

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        for (ValueExpression expr : (EList<ValueExpression>) assignExpr.getTargetColumnList()) {
            valueAnalyzer.analyze(owner, expr);
        }

        analyzeUpdateSource(owner, assignExpr.getUpdateSource());

        System.out.println("[END  ] " + assignExpr.getClass().getSimpleName() + " : " + assignExpr);

    }

    public <T extends Query> void analyze(T owner, ValuesRow row) {

        System.out.println("[BEGIN] " + row.getClass().getSimpleName() + " : " + row);

        System.out.println("[WARN ] " + row.getClass().getSimpleName() + " : サポートしていません。"); // TODO

        System.out.println("[END  ] " + row.getClass().getSimpleName() + " : " + row);

    }

    public <T extends Query> void analyze(T owner, UpdatabilityExpression expr) {

        System.out.println("[BEGIN] " + expr.getClass().getSimpleName() + " : " + expr);

        System.out.println("[WARN ] " + expr.getClass().getSimpleName() + " : サポートしていません。"); // TODO

        System.out.println("[END  ] " + expr.getClass().getSimpleName() + " : " + expr);

    }

    public <T extends Query> void analyze(T owner, MergeInsertSpecification spec) {

        System.out.println("[BEGIN] " + spec.getClass().getSimpleName() + " : " + spec);

        System.out.println("[WARN ] " + spec.getClass().getSimpleName() + " : サポートしていません。"); // TODO

        System.out.println("[END  ] " + spec.getClass().getSimpleName() + " : " + spec);

    }

    private <T extends Query> void analyzeUpdateSource(T owner, UpdateSource source) {

        if (source instanceof UpdateSourceExprList) {
            analyzeUpdateSourceExprList(owner, (UpdateSourceExprList) source);

        } else if (source instanceof UpdateSourceQuery) {
            analyzeUpdateSourceQuery(owner, (UpdateSourceQuery) source);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeUpdateSourceExprList(T owner, UpdateSourceExprList source) {

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        source.getUpdateAssignmentExpr(); // TODO

        for (ValueExpression expr : (EList<ValueExpression>) source.getValueExprList()) {
            valueAnalyzer.analyze(owner, expr);
        }

    }

    private <T extends Query> void analyzeUpdateSourceQuery(T owner, UpdateSourceQuery source) {

        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();

        Query subquery = new Query(owner);

        source.getUpdateAssignmentExpr(); // TODO

        tableAnalyzer.analyze(subquery, source.getQueryExpr());

        owner.getSubQueryList().add(subquery);

    }

}
