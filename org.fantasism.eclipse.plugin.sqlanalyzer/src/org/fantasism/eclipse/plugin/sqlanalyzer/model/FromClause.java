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
public class FromClause<T extends Query<?>> {

    public enum JoinType {
        NONE,
        INNER_JOIN,
        LEFT_OUTER_JOIN,
        RIGHT_OUTER_JOIN,
        LATERAL,
    }

    /** 所有者 */
    private T owner;

    /** テーブル */
    private Table<FromClause<T>> table;

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
     * テーブルを取得します。
     * @return テーブル
     */
    public Table<FromClause<T>> getTable() {
        return table;
    }

    /**
     * テーブルを設定します。
     * @param table テーブル
     */
    public void setTable(Table<FromClause<T>> table) {
        this.table = table;
    }

}
