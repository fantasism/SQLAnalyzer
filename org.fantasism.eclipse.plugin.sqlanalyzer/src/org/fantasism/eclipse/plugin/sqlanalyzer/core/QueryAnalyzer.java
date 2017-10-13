/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.fantasism.eclipse.plugin.sqlanalyzer.SqlAnalyzerContext;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class QueryAnalyzer {

    private SqlAnalyzerContext context;

    public QueryAnalyzer(SqlAnalyzerContext context) {
        this.context = context;
    }

    public <T extends Query> void analyze(T owner, QueryExpression query) {

        System.out.println("[BEGIN] " + query.getClass().getSimpleName() + " : " + query);

        if (query instanceof QueryExpressionDefault) {
            analyzeDefault(owner, (QueryExpressionDefault) query);

        } else if (query instanceof QueryExpressionRoot) {
            analyzeRoot(owner, (QueryExpressionRoot) query);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        System.out.println("[END  ] " + query.getClass().getSimpleName() + " : " + query);

    }

    private <T extends Query> void analyzeDefault(T owner, QueryExpressionDefault query) {
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeRoot(T owner, QueryExpressionRoot query) {

        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();

        tableAnalyzer.analyze(owner, query.getQuery());

    }


}
