/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.AbstractModel;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class QueryExpressionAnalyzer {

    public <T extends AbstractModel<?>> Query<T> analyze(T owner, QueryExpression expr) {

        if (expr instanceof QueryExpressionBody) {
            return analyzeBody(owner, (QueryExpressionBody) expr);

        } else if (expr instanceof QueryExpressionDefault) {
            return analyzeDefault(owner, (QueryExpressionDefault) expr);

        } else if (expr instanceof QueryExpressionRoot) {
            return analyzeRoot(owner, (QueryExpressionRoot) expr);

        } else {
            System.out.println(expr);
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends AbstractModel<?>> Query<T> analyzeBody(T owner, QueryExpressionBody expr) {
        System.out.println(QueryExpressionBody.class + ":" + expr);

        QueryAnalyzer analyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();

        return analyzer.analyze(owner, expr);
    }

    private <T extends AbstractModel<?>> Query<T> analyzeDefault(T owner, QueryExpressionDefault expr) {
        System.out.println(QueryExpressionDefault.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends AbstractModel<?>> Query<T> analyzeRoot(T owner, QueryExpressionRoot expr) {
        System.out.println(QueryExpressionRoot.class + ":" + expr);
        return analyzeBody(owner, expr.getQuery());
    }

}
