/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Column;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ConditionExpr;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.FromClause;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.GroupByClause;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.HavingClause;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.SelectClause;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.SelectColumn;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Table;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ValueExpr;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ValueExpr.ValueType;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.WhereClause;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query.QueryType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class QueryAnalyzer {

    public <T> Query<T> analyze(T owner, QueryExpressionBody query) {

        if (query instanceof QuerySelect) {
            return analyzeSelect(owner, (QuerySelect) query);

        } else if (query instanceof QueryCombined) {
            return analyzeCombined(owner, (QueryCombined) query);

        } else if (query instanceof QueryValues) {
            return analyzeValues(owner, (QueryValues) query);

        } else if (query instanceof QueryNested) {
            return analyzeNested(owner, (QueryNested) query);

        } else {
            System.out.println(query);
            throw new RuntimeException("サポートしてません。");
        }
    }

    private <T> Query<T> analyzeSelect(T owner, QuerySelect select) {

        Query<T> query = new Query<T>();

        query.setOwner(owner);
        query.setQueryType(QueryType.QUERY_SELECT);
        query.setSelectClausesList(analyzeSelectClause(query, select));
        query.setFromClauseList(analyzeFromClause(query, select));
        query.setWhereClause(analyzeWhereClause(query, select));
        query.setGroupByClauseList(analyzeGroupByClause(query, select));
        query.setHavingClause(analyzeHavingClause(query, select));

        return query;
    }

    private <T> Query<T> analyzeCombined(T owner, QueryCombined query) {
        System.out.println(QueryCombined.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> Query<T> analyzeValues(T owner, QueryValues query) {
        System.out.println(QueryValues.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> Query<T> analyzeNested(T owner, QueryNested query) {
        System.out.println(QueryNested.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query<?>> List<SelectClause<T>> analyzeSelectClause(T owner, QuerySelect query) {
        System.out.println(QuerySelect.class + ":" + query);

        ResultSpecificationAnalyzer analyzer = SqlAnalyzerManager.getInstance().getResultSpecificationAnalyzer();

        List<SelectClause<T>> selectClauseList = new ArrayList<>();

        for (Object item : query.getSelectClause()) {
            SelectClause<T> selectClause = new SelectClause<T>();

            SelectColumn<SelectClause<T>> column = analyzer.analyze(selectClause, (QueryResultSpecification) item);

            selectClause.setOwner(owner);
            selectClause.setColumn(column);

            selectClauseList.add(selectClause);
        }

        return selectClauseList;
    }

    private <T extends Query<?>> List<FromClause<T>> analyzeFromClause(T owner, QuerySelect query) {

        TableReferenceAnalyzer tableAnalyzer = SqlAnalyzerManager.getInstance().getTableExpressionAnalyzer();

        List<FromClause<T>> fromClauseList = new ArrayList<FromClause<T>>();

        for (Object item : query.getFromClause()) {
            FromClause<T> fromClause = new FromClause<T>();

            Table<FromClause<T>> table = tableAnalyzer.analyze(fromClause, (TableReference) item);

            fromClause.setOwner(owner);
            fromClause.setTable(table);

            fromClauseList.add(fromClause);
        }

        return fromClauseList;
    }

    private <T extends Query<?>> WhereClause<T> analyzeWhereClause(T owner, QuerySelect query) {

        SearchConditionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer();

        WhereClause<T> whereClause = new WhereClause<T>();

        ConditionExpr<WhereClause<T>> condition = new ConditionExpr<WhereClause<T>>();
        condition.setOwner(whereClause);
        condition.setConditionExprType(null); // TODO

        analyzer.analyze(condition, query.getWhereClause());


        whereClause.setOwner(owner);
        whereClause.setWhereCondition(condition);

        return whereClause;
    }

    private <T extends Query<?>> List<GroupByClause<T>> analyzeGroupByClause(T owner, QuerySelect query) {

        List<GroupByClause<T>> groupByClauseList = new ArrayList<GroupByClause<T>>();

        for (Object item : query.getGroupByClause()) {

            System.out.println(item); // TODO 未実装

        }

        return groupByClauseList;
    }

    private <T extends Query<?>> HavingClause<T> analyzeHavingClause(T owner, QuerySelect query) {

        HavingClause<T> havingClause = null;

        if (query.getHavingClause() != null) {
            SearchConditionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer();

            havingClause = new HavingClause<T>();

            ConditionExpr<HavingClause<T>> condition = new ConditionExpr<HavingClause<T>>();
            condition.setOwner(havingClause);
            condition.setConditionExprType(null);

             analyzer.analyze(condition, query.getHavingClause());

            havingClause.setOwner(owner);
            havingClause.setHavingCondition(condition);

        } else {

        }

        return havingClause;
    }

}
