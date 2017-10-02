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
public class WhereClause<T extends Query<?>> extends AbstractModel<T> {

    /** 抽出条件 */
    private ConditionExpr<WhereClause<T>> whereCondition;

    public WhereClause(T owner) {
        super(owner);
    }

    /**
     * 抽出条件を取得します。
     * @return 抽出条件
     */
    public ConditionExpr<WhereClause<T>> getWhereCondition() {
        return whereCondition;
    }

    /**
     * 抽出条件を設定します。
     * @param whereCondition 抽出条件
     */
    public void setWhereCondition(ConditionExpr<WhereClause<T>> whereCondition) {
        this.whereCondition = whereCondition;
    }

}
