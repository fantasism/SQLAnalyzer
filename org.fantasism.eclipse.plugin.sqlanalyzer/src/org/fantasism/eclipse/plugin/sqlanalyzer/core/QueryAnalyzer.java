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
import org.fantasism.eclipse.plugin.sqlanalyzer.model.AbstractModel;
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

    public <T extends AbstractModel<?>> Query<T> analyze(T owner, QueryExpressionBody query) {

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

    private <T extends AbstractModel<?>> Query<T> analyzeSelect(T owner, QuerySelect select) {

        Query<T> query = new Query<T>(owner);

        if (owner != null) {
            if (owner.getOwnerQuery() != null) {
                owner.getOwnerQuery().getSubQueryList().add(query);
            } else {
                // 処理なし
            }
        } else {
            query.setOwner(query);
            query.setOwnerQuery(query);
        }

        query.setQueryType(QueryType.QUERY_SELECT);
        query.setSelectClausesList(analyzeSelectClause(query, select));
        query.setFromClauseList(analyzeFromClause(query, select));
        query.setWhereClause(analyzeWhereClause(query, select));
        query.setGroupByClauseList(analyzeGroupByClause(query, select));
        query.setHavingClause(analyzeHavingClause(query, select));

        return query;
    }

    private <T extends AbstractModel<?>> Query<T> analyzeCombined(T owner, QueryCombined query) {
        System.out.println(QueryCombined.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends AbstractModel<?>> Query<T> analyzeValues(T owner, QueryValues query) {
        System.out.println(QueryValues.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends AbstractModel<?>> Query<T> analyzeNested(T owner, QueryNested query) {
        System.out.println(QueryNested.class + ":" + query);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query<?>> List<SelectClause<T>> analyzeSelectClause(T owner, QuerySelect query) {
        System.out.println(QuerySelect.class + ":" + query);

        ResultSpecificationAnalyzer analyzer = SqlAnalyzerManager.getInstance().getResultSpecificationAnalyzer();

        List<SelectClause<T>> selectClauseList = new ArrayList<>();

        for (Object item : query.getSelectClause()) {
            SelectClause<T> selectClause = new SelectClause<T>(owner);

            SelectColumn<SelectClause<T>> column = analyzer.analyze(selectClause, (QueryResultSpecification) item);

            selectClause.setColumn(column);

            selectClauseList.add(selectClause);
        }

        return selectClauseList;
    }

    private <T extends Query<?>> List<FromClause<T>> analyzeFromClause(T owner, QuerySelect query) {

        TableReferenceAnalyzer tableAnalyzer = SqlAnalyzerManager.getInstance().getTableExpressionAnalyzer();

        List<FromClause<T>> fromClauseList = new ArrayList<FromClause<T>>();

        for (Object item : query.getFromClause()) {
            FromClause<T> fromClause = new FromClause<T>(owner);

            Table<FromClause<T>> table = tableAnalyzer.analyze(fromClause, (TableReference) item);

            fromClause.setTable(table);

            fromClauseList.add(fromClause);
        }

        return fromClauseList;
    }

    private <T extends Query<?>> WhereClause<T> analyzeWhereClause(T owner, QuerySelect query) {

        SearchConditionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer();

        WhereClause<T> whereClause = new WhereClause<T>(owner);

        ConditionExpr<WhereClause<T>> condition = new ConditionExpr<WhereClause<T>>(whereClause);
        condition.setConditionExprType(null); // TODO

        analyzer.analyze(condition, query.getWhereClause());

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

            havingClause = new HavingClause<T>(owner);

            ConditionExpr<HavingClause<T>> condition = new ConditionExpr<HavingClause<T>>(havingClause);
            condition.setConditionExprType(null);

            analyzer.analyze(condition, query.getHavingClause());

            havingClause.setHavingCondition(condition);

        } else {

        }

        return havingClause;
    }

}
