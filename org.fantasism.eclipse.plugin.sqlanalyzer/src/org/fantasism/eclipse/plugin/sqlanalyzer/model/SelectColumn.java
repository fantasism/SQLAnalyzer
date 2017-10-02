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
public class SelectColumn<T extends AbstractModel<?>> extends AbstractModel<T> {

    public enum ColumnType {
        COLUMN,
        ALL_COLUMNS
    }

    /** カラム種別 */
    private ColumnType columnType;

    /** 値 */
    private ValueExpr<SelectColumn<T>> value;

    /** 別名 */
    private String alias;

    public SelectColumn(T owner) {
        super(owner);
    }

    /**
     * カラム種別を取得します。
     * @return カラム種別
     */
    public ColumnType getColumnType() {
        return columnType;
    }

    /**
     * カラム種別を設定します。
     * @param columnType カラム種別
     */
    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    /**
     * 値を取得します。
     * @return 値
     */
    public ValueExpr<SelectColumn<T>> getValue() {
        return value;
    }

    /**
     * 値を設定します。
     * @param value 値
     */
    public void setValue(ValueExpr<SelectColumn<T>> value) {
        this.value = value;
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

}
