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
public class SelectClause<T extends Query<?>> extends AbstractModel<T> {

    /** カラム */
    private SelectColumn<SelectClause<T>> column;

    public SelectClause(T owner) {
        super(owner);
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
