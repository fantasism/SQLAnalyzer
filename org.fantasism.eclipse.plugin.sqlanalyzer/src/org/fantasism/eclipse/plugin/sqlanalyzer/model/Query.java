/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class Query {

    public enum QueryType {
        SIMPLE_TABLE,
        QUERY_SELECT,
        QUERY_INSERT,
        QUERY_UPDATE,
        QUERY_DELETE,
        QUERY_SELECT_INSERT,
    }

    /** サブクエリリスト */
    private List<Query> subQueryList;

    /** クエリ種別 */
    private QueryType queryType;

    private List<String> joinTableNames;

    private Query unionQuery;

    private Query nestedQuery;

    /** 親クエリ */
    private Query ownerQuery;

    public Query(Query owner) {
        if (owner != null) {
            this.ownerQuery = owner.getOwnerQuery();
        } else {
            this.ownerQuery = this;
        }
        this.subQueryList = new ArrayList<Query>();
        this.joinTableNames = new ArrayList<>();
    }

    /**
     * サブクエリリストを取得します。
     * @return サブクエリリスト
     */
    public List<Query> getSubQueryList() {
        return subQueryList;
    }

    /**
     * サブクエリリストを設定します。
     * @param subQueryList サブクエリリスト
     */
    public void setSubQueryList(List<Query> subQueryList) {
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
     * joinTableNamesを取得します。
     * @return joinTableNames
     */
    public List<String> getJoinTableNames() {
        return joinTableNames;
    }

    /**
     * joinTableNamesを設定します。
     * @param joinTableNames joinTableNames
     */
    public void setJoinTableNames(List<String> joinTableNames) {
        this.joinTableNames = joinTableNames;
    }

    /**
     * unionQueryを取得します。
     * @return unionQuery
     */
    public Query getUnionQuery() {
        return unionQuery;
    }

    /**
     * unionQueryを設定します。
     * @param unionQuery unionQuery
     */
    public void setUnionQuery(Query unionQuery) {
        this.unionQuery = unionQuery;
    }

    /**
     * nestedQueryを取得します。
     * @return nestedQuery
     */
    public Query getNestedQuery() {
        return nestedQuery;
    }

    /**
     * nestedQueryを設定します。
     * @param nestedQuery nestedQuery
     */
    public void setNestedQuery(Query nestedQuery) {
        this.nestedQuery = nestedQuery;
    }

    /**
     * 親クエリを取得します。
     * @return 親クエリ
     */
    public Query getOwnerQuery() {
        return ownerQuery;
    }

    /**
     * 親クエリを設定します。
     * @param ownerQuery 親クエリ
     */
    public void setOwnerQuery(Query ownerQuery) {
        this.ownerQuery = ownerQuery;
    }

}
