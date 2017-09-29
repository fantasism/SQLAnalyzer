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
public class SetClause<T extends Query<?>> {

    /** 所有者 */
    private T owner;

    /** カラム */
    private Column<SetClause<T>> column;

    /** 設定値 */
    private ValueExpr<SetClause<T>> value;

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
     * カラムを取得します。
     * @return カラム
     */
    public Column<SetClause<T>> getColumn() {
        return column;
    }

    /**
     * カラムを設定します。
     * @param column カラム
     */
    public void setColumn(Column<SetClause<T>> column) {
        this.column = column;
    }

    /**
     * 設定値を取得します。
     * @return 設定値
     */
    public ValueExpr<SetClause<T>> getValue() {
        return value;
    }

    /**
     * 設定値を設定します。
     * @param value 設定値
     */
    public void setValue(ValueExpr<SetClause<T>> value) {
        this.value = value;
    }

}