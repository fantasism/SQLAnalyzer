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
public class FunctionExpr<T extends AbstractModel<?>> extends AbstractModel<T> {

    /** ファンクション名 */
    private String functionName;

    /** ファンクション引数 */
    private List<ValueExpr<FunctionExpr<T>>> parameterList;

    public FunctionExpr(T owner) {
        super(owner);
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
