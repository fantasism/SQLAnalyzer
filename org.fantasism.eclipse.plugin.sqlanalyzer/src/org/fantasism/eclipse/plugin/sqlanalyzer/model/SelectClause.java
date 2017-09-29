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
public class SelectClause<T extends Query<?>> {

    /** 所有者 */
    private T owner;

    /** カラム */
    private SelectColumn<SelectClause<T>> column;

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
    public SelectColumn<SelectClause<T>> getColumn() {
        return column;
    }

    /**
     * カラムを設定します。
     * @param column カラム
     */
    public void setColumn(SelectColumn<SelectClause<T>> column) {
        this.column = column;
    }

}
