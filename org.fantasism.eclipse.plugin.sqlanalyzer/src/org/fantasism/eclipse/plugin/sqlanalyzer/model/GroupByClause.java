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
public class GroupByClause<T extends Query<?>> extends AbstractModel<T> {

    /** グルーピング項目 */
    private ValueExpr<GroupByClause<T>> groupingItem;

    public GroupByClause(T owner) {
        super(owner);
    }

    /**
     * グルーピング項目を取得します。
     * @return グルーピング項目
     */
    public ValueExpr<GroupByClause<T>> getGroupingItem() {
        return groupingItem;
    }

    /**
     * グルーピング項目を設定します。
     * @param groupingItem グルーピング項目
     */
    public void setGroupingItem(ValueExpr<GroupByClause<T>> groupingItem) {
        this.groupingItem = groupingItem;
    }

}
