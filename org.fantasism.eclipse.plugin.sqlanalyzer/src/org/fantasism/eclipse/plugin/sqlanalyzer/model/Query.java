/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class Query<T extends AbstractModel<?>> extends AbstractModel<T> {

    public enum QueryType {
        SIMPLE_TABLE,
        QUERY_SELECT,
        QUERY_INSERT,
        QUERY_UPDATE,
        QUERY_DELETE,
        QUERY_SELECT_INSERT,
    }

    /** サブクエリリスト */
    private List<Query<?>> subQueryList;

    /** クエリ種別 */
    private QueryType queryType;

    /** テーブル名 */
    private String tableName;

    /** 別名 */
    private String alias;

    /** ＳＥＬＥＣＴ句リスト */
    private List<SelectClause<Query<T>>> selectClausesList;

    /** ＦＲＯＭ句リスト */
    private List<FromClause<Query<T>>> fromClauseList;

    private WhereClause<Query<T>> whereClause;

    /** ＧＲＯＵＰ　ＢＹ句リスト */
    private List<GroupByClause<Query<T>>> groupByClauseList;

    /** ＨＡＶＩＮＧ句リスト */
    private HavingClause<Query<T>> havingClause;

    /** ＯＲＤＥＲ　ＢＹ句リスト */
    private List<OrderByClause<Query<T>>> orderByClauseList;

    /** ＳＥＴ句リスト */
    private List<SetClause<Query<T>>> setClauseList;

    /** ＩＮＴＯ句クエリ */
    private Query<Query<T>> intoQuery;

    /** 関連クエリ */
    private Map<String, Query<Query<?>>> relationQueryMap;

    public Query(T owner) {
        super(owner);
        this.subQueryList = new ArrayList<Query<?>>();
        this.selectClausesList = new ArrayList<SelectClause<Query<T>>>();
        this.fromClauseList = new ArrayList<FromClause<Query<T>>>();
        this.groupByClauseList = new ArrayList<GroupByClause<Query<T>>>();
        this.orderByClauseList = new ArrayList<OrderByClause<Query<T>>>();
        this.setClauseList = new ArrayList<SetClause<Query<T>>>();
        this.relationQueryMap = new HashMap<String, Query<Query<?>>>();
    }

    /**
     * サブクエリリストを取得します。
     * @return サブクエリリスト
     */
    public List<Query<?>> getSubQueryList() {
        return subQueryList;
    }

    /**
     * サブクエリリストを設定します。
     * @param subQueryList サブクエリリスト
     */
    public void setSubQueryList(List<Query<?>> subQueryList) {
        this.subQueryList = subQueryList;
    }

    /**
     * クエリ種別を取得します。
     * @return クエリ種別
     */
    public QueryType getQueryType() {
        return queryType;
    }

    /**
     * クエリ種別を設定します。
     * @param queryType クエリ種別
     */
    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    /**
     * テーブル名を取得します。
     * @return テーブル名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * テーブル名を設定します。
     * @param tableName テーブル名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 別名を取得します。
     * @return 別名
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 別名を設定します。
     * @param alias 別名
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * ＳＥＬＥＣＴ句リストを取得します。
     * @return ＳＥＬＥＣＴ句リスト
     */
    public List<SelectClause<Query<T>>> getSelectClausesList() {
        return selectClausesList;
    }

    /**
     * ＳＥＬＥＣＴ句リストを設定します。
     * @param selectClausesList ＳＥＬＥＣＴ句リスト
     */
    public void setSelectClausesList(List<SelectClause<Query<T>>> selectClausesList) {
        this.selectClausesList = selectClausesList;
    }

    /**
     * ＦＲＯＭ句リストを取得します。
     * @return ＦＲＯＭ句リスト
     */
    public List<FromClause<Query<T>>> getFromClauseList() {
        return fromClauseList;
    }

    /**
     * ＦＲＯＭ句リストを設定します。
     * @param fromClauseList ＦＲＯＭ句リスト
     */
    public void setFromClauseList(List<FromClause<Query<T>>> fromClauseList) {
        this.fromClauseList = fromClauseList;
    }

    /**
     * whereClauseを取得します。
     * @return whereClause
     */
    public WhereClause<Query<T>> getWhereClause() {
        return whereClause;
    }

    /**
     * whereClauseを設定します。
     * @param whereClause whereClause
     */
    public void setWhereClause(WhereClause<Query<T>> whereClause) {
        this.whereClause = whereClause;
    }

    /**
     * ＧＲＯＵＰ　ＢＹ句リストを取得します。
     * @return ＧＲＯＵＰ　ＢＹ句リスト
     */
    public List<GroupByClause<Query<T>>> getGroupByClauseList() {
        return groupByClauseList;
    }

    /**
     * ＧＲＯＵＰ　ＢＹ句リストを設定します。
     * @param groupByClauseList ＧＲＯＵＰ　ＢＹ句リスト
     */
    public void setGroupByClauseList(List<GroupByClause<Query<T>>> groupByClauseList) {
        this.groupByClauseList = groupByClauseList;
    }

    /**
     * ＨＡＶＩＮＧ句リストを取得します。
     * @return ＨＡＶＩＮＧ句リスト
     */
    public HavingClause<Query<T>> getHavingClause() {
        return havingClause;
    }

    /**
     * ＨＡＶＩＮＧ句リストを設定します。
     * @param havingClause ＨＡＶＩＮＧ句リスト
     */
    public void setHavingClause(HavingClause<Query<T>> havingClause) {
        this.havingClause = havingClause;
    }

    /**
     * ＯＲＤＥＲ　ＢＹ句リストを取得します。
     * @return ＯＲＤＥＲ　ＢＹ句リスト
     */
    public List<OrderByClause<Query<T>>> getOrderByClauseList() {
        return orderByClauseList;
    }

    /**
     * ＯＲＤＥＲ　ＢＹ句リストを設定します。
     * @param orderByClauseList ＯＲＤＥＲ　ＢＹ句リスト
     */
    public void setOrderByClauseList(List<OrderByClause<Query<T>>> orderByClauseList) {
        this.orderByClauseList = orderByClauseList;
    }

    /**
     * ＳＥＴ句リストを取得します。
     * @return ＳＥＴ句リスト
     */
    public List<SetClause<Query<T>>> getSetClauseList() {
        return setClauseList;
    }

    /**
     * ＳＥＴ句リストを設定します。
     * @param setClauseList ＳＥＴ句リスト
     */
    public void setSetClauseList(List<SetClause<Query<T>>> setClauseList) {
        this.setClauseList = setClauseList;
    }

    /**
     * ＩＮＴＯ句クエリを取得します。
     * @return ＩＮＴＯ句クエリ
     */
    public Query<Query<T>> getIntoQuery() {
        return intoQuery;
    }

    /**
     * ＩＮＴＯ句クエリを設定します。
     * @param intoQuery ＩＮＴＯ句クエリ
     */
    public void setIntoQuery(Query<Query<T>> intoQuery) {
        this.intoQuery = intoQuery;
    }

    /**
     * 関連クエリを取得します。
     * @return 関連クエリ
     */
    public Map<String,Query<Query<?>>> getRelationQueryMap() {
        return relationQueryMap;
    }

    /**
     * 関連クエリを設定します。
     * @param relationQueryMap 関連クエリ
     */
    public void setRelationQueryMap(Map<String,Query<Query<?>>> relationQueryMap) {
        this.relationQueryMap = relationQueryMap;
    }

}
