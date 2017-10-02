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
public class HavingClause<T extends Query<?>> extends AbstractModel<T> {

    /** 集約条件 */
    private ConditionExpr<HavingClause<T>> havingCondition;

    public HavingClause(T owner) {
        super(owner);
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
