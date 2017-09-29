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
public class FunctionExpr<T> {

    /** 所有者 */
    private T owner;

    /** ファンクション名 */
    private String functionName;

    /** ファンクション引数 */
    private List<ValueExpr<FunctionExpr<T>>> parameterList;

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
     * ファンクション名を取得します。
     * @return ファンクション名
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * ファンクション名を設定します。
     * @param functionName ファンクション名
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * ファンクション引数を取得します。
     * @return ファンクション引数
     */
    public List<ValueExpr<FunctionExpr<T>>> getParameterList() {
        return parameterList;
    }

    /**
     * ファンクション引数を設定します。
     * @param parameterList ファンクション引数
     */
    public void setParameterList(List<ValueExpr<FunctionExpr<T>>> parameterList) {
        this.parameterList = parameterList;
    }

}
