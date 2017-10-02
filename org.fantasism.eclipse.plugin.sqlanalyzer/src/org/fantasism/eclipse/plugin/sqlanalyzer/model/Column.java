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
public class Column<T extends AbstractModel<?>> extends AbstractModel<T> {

    /** 参照元クエリ */
    private Table<?> table;

    /** カラム名 */
    private String columnName;

    public Column(T owner) {
        super(owner);
    }

    /**
     * 参照元クエリを取得します。
     * @return 参照元クエリ
     */
    public Table<?> getTable() {
        return table;
    }

    /**
     * 参照元クエリを設定します。
     * @param table 参照元クエリ
     */
    public void setTable(Table<?> table) {
        this.table = table;
    }

    /**
     * カラム名を取得します。
     * @return カラム名
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * カラム名を設定します。
     * @param columnName カラム名
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

}
