/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class ConditionExpr<T extends AbstractModel<?>> extends AbstractModel<T> {

    public enum ConditionExprType {
        ROOT,
        NESTED,
        IS_NULL,
        IS_NOT_NULL,
        MORE,
        EQUALS_OR_MORE,
        EQUALS,
        EQUALS_OR_LESS,
        LESS,
        IN,
        NOT_IN,
        BETWEEN,
        EXISTS,
        NOT_EXISTS,
    }

    /** 条件式タイプ */
    private ConditionExprType conditionExprType;

    /** 右辺値 */
    private ValueExpr<ConditionExpr<T>> srcValue;

    /** 左辺値 */
    private ValueExpr<ConditionExpr<T>> destValue;

    /** 左辺最小値 */
    private ValueExpr<ConditionExpr<T>> betweenFrom;

    /** 左辺最大値 */
    private ValueExpr<ConditionExpr<T>> betweenTo;

    /** ＯＲ条件式リスト */
    private List<ConditionExpr<?>> andConditionList;

    /** ＯＲ条件式リスト */
    private List<ConditionExpr<?>> orConditionList;

    public ConditionExpr(T owner) {
        super(owner);
        this.orConditionList = new ArrayList<ConditionExpr<?>>();
        this.andConditionList = new ArrayList<ConditionExpr<?>>();
    }

    /**
     * 条件式タイプを取得します。
     * @return 条件式タイプ
     */
    public ConditionExprType getConditionExprType() {
        return conditionExprType;
    }

    /**
     * 条件式タイプを設定します。
     * @param conditionExprType 条件式タイプ
     */
    public void setConditionExprType(ConditionExprType conditionExprType) {
        this.conditionExprType = conditionExprType;
    }

    /**
     * 右辺値を取得します。
     * @return 右辺値
     */
    public ValueExpr<ConditionExpr<T>> getSrcValue() {
        return srcValue;
    }

    /**
     * 右辺値を設定します。
     * @param srcValue 右辺値
     */
    public void setSrcValue(ValueExpr<ConditionExpr<T>> srcValue) {
        this.srcValue = srcValue;
    }

    /**
     * 左辺値を取得します。
     * @return 左辺値
     */
    public ValueExpr<ConditionExpr<T>> getDestValue() {
        return destValue;
    }

    /**
     * 左辺値を設定します。
     * @param destValue 左辺値
     */
    public void setDestValue(ValueExpr<ConditionExpr<T>> destValue) {
        this.destValue = destValue;
    }

    /**
     * 左辺最小値を取得します。
     * @return 左辺最小値
     */
    public ValueExpr<ConditionExpr<T>> getBetweenFrom() {
        return betweenFrom;
    }

    /**
     * 左辺最小値を設定します。
     * @param betweenFrom 左辺最小値
     */
    public void setBetweenFrom(ValueExpr<ConditionExpr<T>> betweenFrom) {
        this.betweenFrom = betweenFrom;
    }

    /**
     * 左辺最大値を取得します。
     * @return 左辺最大値
     */
    public ValueExpr<ConditionExpr<T>> getBetweenTo() {
        return betweenTo;
    }

    /**
     * 左辺最大値を設定します。
     * @param betweenTo 左辺最大値
     */
    public void setBetweenTo(ValueExpr<ConditionExpr<T>> betweenTo) {
        this.betweenTo = betweenTo;
    }

    /**
     * ＯＲ条件式リストを取得します。
     * @return ＯＲ条件式リスト
     */
    public List<ConditionExpr<?>> getAndConditionList() {
        return andConditionList;
    }

    /**
     * ＯＲ条件式リストを設定します。
     * @param andConditionList ＯＲ条件式リスト
     */
    public void setAndConditionList(List<ConditionExpr<?>> andConditionList) {
        this.andConditionList = andConditionList;
    }

    /**
     * ＯＲ条件式リストを取得します。
     * @return ＯＲ条件式リスト
     */
    public List<ConditionExpr<?>> getOrConditionList() {
        return orConditionList;
    }

    /**
     * ＯＲ条件式リストを設定します。
     * @param orConditionList ＯＲ条件式リスト
     */
    public void setOrConditionList(List<ConditionExpr<?>> orConditionList) {
        this.orConditionList = orConditionList;
    }

}
