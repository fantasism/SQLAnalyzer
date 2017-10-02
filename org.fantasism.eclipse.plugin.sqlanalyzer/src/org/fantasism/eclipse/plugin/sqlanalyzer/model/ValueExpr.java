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
public class ValueExpr<T extends AbstractModel<?>> extends AbstractModel<T> {

    public enum ValueType {
        COLUMN,
        ROWS,
        LITERAL_VALUE,
        CASE,
        SUBQUERY,
        FUNCTION,
        NESTED_VALUE,
        NULL,
    }

    /** 値タイプ */
    private ValueType valueType;

    /** カラム */
    private Column<ValueExpr<T>> column;

    /** 行 */
    private List<Column<ValueExpr<T>>> rows;

    /** リテラル値 */
    private String literalValue;

    /** サブクエリ */
    private Query<ValueExpr<T>> query;

    /** ケース */
    private CaseExpr<ValueExpr<T>> caseExpr;

    /** 関数 */
    private FunctionExpr<ValueExpr<T>> function;

    /** ネストした値 */
    private ValueExpr<T> nestedValue;

    public ValueExpr(T owner) {
        super(owner);
    }

    /**
     * 値タイプを取得します。
     * @return 値タイプ
     */
    public ValueType getValueType() {
        return valueType;
    }

    /**
     * 値タイプを設定します。
     * @param valueType 値タイプ
     */
    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    /**
     * カラムを取得します。
     * @return カラム
     */
    public Column<ValueExpr<T>> getColumn() {
        return column;
    }

    /**
     * カラムを設定します。
     * @param column カラム
     */
    public void setColumn(Column<ValueExpr<T>> column) {
        this.column = column;
    }

    /**
     * 行を取得します。
     * @return 行
     */
    public List<Column<ValueExpr<T>>> getRows() {
        return rows;
    }

    /**
     * 行を設定します。
     * @param rows 行
     */
    public void setRows(List<Column<ValueExpr<T>>> rows) {
        this.rows = rows;
    }

    /**
     * リテラル値を取得します。
     * @return リテラル値
     */
    public String getLiteralValue() {
        return literalValue;
    }

    /**
     * リテラル値を設定します。
     * @param literalValue リテラル値
     */
    public void setLiteralValue(String literalValue) {
        this.literalValue = literalValue;
    }

    /**
     * サブクエリを取得します。
     * @return サブクエリ
     */
    public Query<ValueExpr<T>> getQuery() {
        return query;
    }

    /**
     * サブクエリを設定します。
     * @param query サブクエリ
     */
    public void setQuery(Query<ValueExpr<T>> query) {
        this.query = query;
    }

    /**
     * ケースを取得します。
     * @return ケース
     */
    public CaseExpr<ValueExpr<T>> getCaseExpr() {
        return caseExpr;
    }

    /**
     * ケースを設定します。
     * @param caseExpr ケース
     */
    public void setCaseExpr(CaseExpr<ValueExpr<T>> caseExpr) {
        this.caseExpr = caseExpr;
    }

    /**
     * 関数を取得します。
     * @return 関数
     */
    public FunctionExpr<ValueExpr<T>> getFunction() {
        return function;
    }

    /**
     * 関数を設定します。
     * @param function 関数
     */
    public void setFunction(FunctionExpr<ValueExpr<T>> function) {
        this.function = function;
    }

    /**
     * ネストした値を取得します。
     * @return ネストした値
     */
    public ValueExpr<T> getNestedValue() {
        return nestedValue;
    }

    /**
     * ネストした値を設定します。
     * @param nestedValue ネストした値
     */
    public void setNestedValue(ValueExpr<T> nestedValue) {
        this.nestedValue = nestedValue;
    }

}
