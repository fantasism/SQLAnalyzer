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
public class OrderByClause<T extends Query<?>> {

    /** 所有者 */
    private T owner;

    /** ソート項目 */
    private ValueExpr<OrderByClause<T>> sortItem;

    /** 並び替え方法 */
    private String sortType;

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
     * ソート項目を取得します。
     * @return ソート項目
     */
    public ValueExpr<OrderByClause<T>> getSortItem() {
        return sortItem;
    }

    /**
     * ソート項目を設定します。
     * @param sortItem ソート項目
     */
    public void setSortItem(ValueExpr<OrderByClause<T>> sortItem) {
        this.sortItem = sortItem;
    }

    /**
     * 並び替え方法を取得します。
     * @return 並び替え方法
     */
    public String getSortType() {
        return sortType;
    }

    /**
     * 並び替え方法を設定します。
     * @param sortType 並び替え方法
     */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}
