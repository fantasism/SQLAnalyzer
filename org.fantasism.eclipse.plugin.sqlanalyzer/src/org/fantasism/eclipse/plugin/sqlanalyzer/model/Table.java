/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.model;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class Table<T> {

    public enum TableType {
        TABLE,
        QUERY,
        FUNCTION,
    }

    public enum JoinType {
        NONE,
        INNER_JOIN,
        LEFT_OUTER_JOIN,
        RIGHT_OUTER_JOIN,
        LATERAL,
    }

    /** 所有者 */
    private T owner;

    /** テーブル種別 **/
    private TableType tableType;

    /** テーブル名 */
    private String tableName;

    /** サブクエリ */
    private Query<Table<T>> query;

    /** テーブル関数 */
    private FunctionExpr<Table<T>> tableFunction;

    /** 別名 */
    private String alias;

    /** 結合方法 */
    private JoinType joinType;

    /** 結合条件 */
    private ConditionExpr<Table<T>> joinCondition;

    /** ＪＯＩＮ句 */
    private Table<T> joinTable;

    /**
     * 所有者を取得します。
     * @return 所有者
     */
    public T getOwner() {
        return owner;
    }

    /**
     * 所有者を設定します。
     * @param owner 所有者
     */
    public void setOwner(T owner) {
        this.owner = owner;
    }

    /**
     * テーブル種別 *を取得します。
     * @return テーブル種別 *
     */
    public TableType getTableType() {
        return tableType;
    }

    /**
     * テーブル種別 *を設定します。
     * @param tableType テーブル種別 *
     */
    public void setTableType(TableType tableType) {
        this.tableType = tableType;
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
     * サブクエリを取得します。
     * @return サブクエリ
     */
    public Query<Table<T>> getQuery() {
        return query;
    }

    /**
     * サブクエリを設定します。
     * @param query サブクエリ
     */
    public void setQuery(Query<Table<T>> query) {
        this.query = query;
    }

    /**
     * テーブル関数を取得します。
     * @return テーブル関数
     */
    public FunctionExpr<Table<T>> getTableFunction() {
        return tableFunction;
    }

    /**
     * テーブル関数を設定します。
     * @param tableFunction テーブル関数
     */
    public void setTableFunction(FunctionExpr<Table<T>> tableFunction) {
        this.tableFunction = tableFunction;
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
     * 結合方法を取得します。
     * @return 結合方法
     */
    public JoinType getJoinType() {
        return joinType;
    }

    /**
     * 結合方法を設定します。
     * @param joinType 結合方法
     */
    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    /**
     * 結合条件を取得します。
     * @return 結合条件
     */
    public ConditionExpr<Table<T>> getJoinCondition() {
        return joinCondition;
    }

    /**
     * 結合条件を設定します。
     * @param joinCondition 結合条件
     */
    public void setJoinCondition(ConditionExpr<Table<T>> joinCondition) {
        this.joinCondition = joinCondition;
    }

    /**
     * ＪＯＩＮ句を取得します。
     * @return ＪＯＩＮ句
     */
    public Table<T> getJoinTable() {
        return joinTable;
    }

    /**
     * ＪＯＩＮ句を設定します。
     * @param joinTable ＪＯＩＮ句
     */
    public void setJoinTable(Table<T> joinTable) {
        this.joinTable = joinTable;
    }

}
