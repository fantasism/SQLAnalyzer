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
public class FromClause<T extends Query<?>> extends AbstractModel<T> {

    public enum JoinType {
        NONE,
        INNER_JOIN,
        LEFT_OUTER_JOIN,
        RIGHT_OUTER_JOIN,
        LATERAL,
    }

    /** テーブル */
    private Table<FromClause<T>> table;

    public FromClause(T owner) {
        super(owner);
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
