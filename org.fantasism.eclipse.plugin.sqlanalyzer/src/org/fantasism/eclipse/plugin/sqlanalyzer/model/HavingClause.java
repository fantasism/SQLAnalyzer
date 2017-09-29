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
public class HavingClause<T extends Query<?>> {

    /** 所有者 */
    private T owner;

    /** 集約条件 */
    private ConditionExpr<HavingClause<T>> havingCondition;

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
     * 集約条件を取得します。
     * @return 集約条件
     */
    public ConditionExpr<HavingClause<T>> getHavingCondition() {
        return havingCondition;
    }

    /**
     * 集約条件を設定します。
     * @param havingCondition 集約条件
     */
    public void setHavingCondition(ConditionExpr<HavingClause<T>> havingCondition) {
        this.havingCondition = havingCondition;
    }

}
