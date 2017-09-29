/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.model;

import java.util.List;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class CaseExpr<T> {

    /** 所有者 */
    private T owner;

    /** ケース */
    private List<CaseWhenThen<CaseExpr<T>>> caseWhenThenList;

    /** その他の値 */
    private ValueExpr<CaseExpr<T>> elseValue;

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
     * ケースを取得します。
     * @return ケース
     */
    public List<CaseWhenThen<CaseExpr<T>>> getCaseWhenThenList() {
        return caseWhenThenList;
    }

    /**
     * ケースを設定します。
     * @param caseWhenThenList ケース
     */
    public void setCaseWhenThenList(List<CaseWhenThen<CaseExpr<T>>> caseWhenThenList) {
        this.caseWhenThenList = caseWhenThenList;
    }

    /**
     * その他の値を取得します。
     * @return その他の値
     */
    public ValueExpr<CaseExpr<T>> getElseValue() {
        return elseValue;
    }

    /**
     * その他の値を設定します。
     * @param elseValue その他の値
     */
    public void setElseValue(ValueExpr<CaseExpr<T>> elseValue) {
        this.elseValue = elseValue;
    }

    public static <T> ValueExpr<CaseExpr<T>> createValueExpr(CaseExpr<T> caseExpr) {
        ValueExpr<CaseExpr<T>> valueExpr = new ValueExpr<CaseExpr<T>>();
        valueExpr.setOwner(caseExpr);
        return valueExpr;
    }

}
