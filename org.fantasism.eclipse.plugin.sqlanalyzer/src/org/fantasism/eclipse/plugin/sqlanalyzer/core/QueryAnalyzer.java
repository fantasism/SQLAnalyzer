/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query.QueryType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class QueryAnalyzer {

    public <T extends Query> void analyze(T owner, SQLQueryObject query) {

        if (query instanceof QueryExpressionBody) {
            analyzeBody(owner, (QueryExpressionBody) query);

        } else if (query instanceof QueryExpressionDefault) {
            analyzeDefault(owner, (QueryExpressionDefault) query);

        } else if (query instanceof QueryExpressionRoot) {
            analyzeRoot(owner, (QueryExpressionRoot) query);

        } else {
            System.out.println(query);
            throw new RuntimeException("サポートしてません。");
        }
    }

    private <T extends Query> void analyzeSelect(T owner, QuerySelect select) {

//        Query query = new Query(owner);
//
//        if (owner != null) {
//            if (owner.getOwnerQuery() != null) {
//                owner.getOwnerQuery().getSubQueryList().add(query);
//            } else {
//                // 処理なし
//            }
//        } else {
//            query.setOwnerQuery(query);
//        }

        owner.setQueryType(QueryType.QUERY_SELECT);
        analyzeSelectClause(owner, select);
        analyzeFromClause(owner, select);
        analyzeWhereClause(owner, select);
        analyzeGroupByClause(owner, select);
        analyzeHavingClause(owner, select);
    }

    private <T extends Query> void analyzeBody(T owner, QueryExpressionBody query) {
        System.out.println(QueryExpressionBody.class + ":" + query);
        if (query instanceof QuerySelect) {
            analyzeSelect(owner, (QuerySelect) query);

        } else if (query instanceof QueryCombined) {
            analyzeCombined(owner, (QueryCombined) query);

        } else if (query instanceof QueryValues) {
            analyzeValues(owner, (QueryValues) query);

        } else if (query instanceof QueryNested) {
            analyzeNested(owner, (QueryNested) query);

        } else {
            System.out.println(query);
            throw new RuntimeException("サポートしてません。");
        }
    }

    private <T extends Query> void analyzeCombined(T owner, QueryCombined query) {
        System.out.println(QueryCombined.class + ":" + query);

        analyze(owner, query.getLeftQuery());

        Query nestedQuery = new Query(owner.getOwnerQuery());

        Query lastQuery = owner;

        while (lastQuery.getUnionQuery() != null) {
            lastQuery = lastQuery.getUnionQuery();
        }
        lastQuery.setUnionQuery(nestedQuery);
        analyze(nestedQuery, query.getRightQuery());

    }

    private <T extends Query> void analyzeValues(T owner, QueryValues query) {
        System.out.println(QueryValues.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeNested(T owner, QueryNested query) {
        System.out.println(QueryNested.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeDefault(T owner, QueryExpressionDefault query) {
        System.out.println(QueryExpressionDefault.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeRoot(T owner, QueryExpressionRoot query) {
        System.out.println(QueryExpressionRoot.class + ":" + query);
        analyzeBody(owner, query.getQuery());
    }

    private <T extends Query> void analyzeSelectClause(T owner, QuerySelect query) {
        System.out.println(QuerySelect.class + ":" + query);
        ResultSpecificationAnalyzer analyzer = SqlAnalyzerManager.getInstance().getResultSpecificationAnalyzer();
        for (Object item : query.getSelectClause()) {
            analyzer.analyze(owner, (QueryResultSpecification) item);
        }
    }

    private <T extends Query> void analyzeFromClause(T owner, QuerySelect query) {
        TableReferenceAnalyzer tableAnalyzer = SqlAnalyzerManager.getInstance().getTableExpressionAnalyzer();
        for (Object item : query.getFromClause()) {
            tableAnalyzer.analyze(owner, (TableReference) item);
        }
    }

    private <T extends Query> void analyzeWhereClause(T owner, QuerySelect query) {
        if (query.getWhereClause() != null) {
            SearchConditionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer();
            analyzer.analyze(owner, query.getWhereClause());
        } else {

        }
    }

    private <T extends Query> void analyzeGroupByClause(T owner, QuerySelect query) {
        ResultSpecificationAnalyzer analyzer = SqlAnalyzerManager.getInstance().getResultSpecificationAnalyzer();
        for (Object item : query.getGroupByClause()) {
            analyzer.analyze(owner, (QueryResultSpecification) item);
        }
    }

    private <T extends Query> void analyzeHavingClause(T owner, QuerySelect query) {
        if (query.getHavingClause() != null) {
            SearchConditionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer();
            analyzer.analyze(owner, query.getHavingClause());
        } else {

        }
    }

}
