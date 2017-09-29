/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class QueryStatementAnalyzer {

    public <T> Query<T> analyze(T owner, QueryStatement statement) {

        if (statement instanceof QuerySelectStatement) {
            return analyzeSelectStatement(owner, (QuerySelectStatement)statement);

        } else if (statement instanceof QueryInsertStatement) {
            return analyzeInsertStatement(owner, (QueryInsertStatement)statement);

        } else if (statement instanceof QueryUpdateStatement) {
            return analyzeUpdateStatement(owner, (QueryUpdateStatement)statement);

        } else if (statement instanceof QueryDeleteStatement) {
            return analyzeDeleteStatement(owner, (QueryDeleteStatement)statement);

        } else {
            System.out.println(statement);
            throw new RuntimeException("サポートしてません。");
        }

    }


    private <T> Query<T> analyzeSelectStatement(T owner, QuerySelectStatement statement) {
        System.out.println(QuerySelectStatement.class + ":" + statement);

        QueryExpressionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getQueryExpressionAnalyzer();

        Query<T> query = analyzer.analyze(owner, statement.getQueryExpr());

        return query;
    }

    private <T> Query<T> analyzeInsertStatement(T owner, QueryInsertStatement statement) {
        System.out.println(QueryInsertStatement.class + ":" + statement);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> Query<T> analyzeUpdateStatement(T owner, QueryUpdateStatement statement) {
        System.out.println(QueryUpdateStatement.class + ":" + statement);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> Query<T> analyzeDeleteStatement(T owner, QueryDeleteStatement statement) {
        System.out.println(QueryDeleteStatement.class + ":" + statement);
        throw new RuntimeException("サポートしてません。");
    }

}
