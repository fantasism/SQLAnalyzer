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
public class CaseWhenThen<T extends AbstractModel<?>> extends AbstractModel<T> {

    /** 条件 */
    private ConditionExpr<CaseWhenThen<T>> whenCondition;

    /** 値 */
    private ValueExpr<CaseWhenThen<T>> thenValue;

    public CaseWhenThen(T owner) {
        super(owner);
    }

    /**
     * 条件を取得します。
     * @return 条件
     */
    public ConditionExpr<CaseWhenThen<T>> getWhenCondition() {
        return whenCondition;
    }

    /**
     * 条件を設定します。
     * @param whenCondition 条件
     */
    public void setWhenCondition(ConditionExpr<CaseWhenThen<T>> whenCondition) {
        this.whenCondition = whenCondition;
    }

    /**
     * 値を取得します。
     * @return 値
     */
    public ValueExpr<CaseWhenThen<T>> getThenValue() {
        return thenValue;
    }

    /**
     * 値を設定します。
     * @param thenValue 値
     */
    public void setThenValue(ValueExpr<CaseWhenThen<T>> thenValue) {
        this.thenValue = thenValue;
    }

}
