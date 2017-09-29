/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpressionDefault;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Column;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Table;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ValueExpr;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ValueExpr.ValueType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class ValueExpressionAnalyzer {

    public <T> ValueExpr<T> analyze(T owner, ValueExpression expr) {

        if (expr instanceof ValueExpressionCaseElse) {
            return analyzeCaseElse(owner, (ValueExpressionCaseElse) expr);

        } else if (expr instanceof ValueExpressionCaseSearch) {
            return analyzeCaseSearch(owner, (ValueExpressionCaseSearch) expr);

        } else if (expr instanceof ValueExpressionCaseSearchContent) {
            return analyzeCaseSearchContent(owner, (ValueExpressionCaseSearchContent) expr);

        } else if (expr instanceof ValueExpressionCaseSimple) {
            return analyzeCaseSimple(owner, (ValueExpressionCaseSimple) expr);

        } else if (expr instanceof ValueExpressionCaseSimpleContent) {
            return analyzeCaseSimpleContent(owner, (ValueExpressionCaseSimpleContent) expr);

        } else if (expr instanceof ValueExpressionCast) {
            return analyzeCast(owner, (ValueExpressionCast) expr);

        } else if (expr instanceof ValueExpressionColumn) {
            return analyzeColumn(owner, (ValueExpressionColumn) expr);

        } else if (expr instanceof ValueExpressionCombined) {
            return analyzeCombined(owner, (ValueExpressionCombined) expr);

        } else if (expr instanceof ValueExpressionDefault) {
            return analyzeDefault(owner, (ValueExpressionDefault) expr);

        } else if (expr instanceof ValueExpressionDefaultValue) {
            return analyzeDefaultValue(owner, (ValueExpressionDefaultValue) expr);

        } else if (expr instanceof ValueExpressionFunction) {
            return analyzeFunction(owner, (ValueExpressionFunction) expr);

        } else if (expr instanceof ValueExpressionLabeledDuration) {
            return analyzeLabeledDuration(owner, (ValueExpressionLabeledDuration) expr);

        } else if (expr instanceof ValueExpressionNested) {
            return analyzeNested(owner, (ValueExpressionNested) expr);

        } else if (expr instanceof ValueExpressionNullValue) {
            return analyzeNullValue(owner, (ValueExpressionNullValue) expr);

        } else if (expr instanceof ValueExpressionRow) {
            return analyzeRow(owner, (ValueExpressionRow) expr);

        } else if (expr instanceof ValueExpressionScalarSelect) {
            return analyzeScalarSelect(owner, (ValueExpressionScalarSelect) expr);

        } else if (expr instanceof ValueExpressionSimple) {
            return analyzeSimple(owner, (ValueExpressionSimple) expr);

        } else if (expr instanceof ValueExpressionVariable) {
            return analyzeVariable(owner, (ValueExpressionVariable) expr);

        } else if (expr instanceof ValueExpressionCase) {
            return analyzeCase(owner, (ValueExpressionCase) expr);

        } else if (expr instanceof ValueExpressionAtomic) {
            return analyzeAtomic(owner, (ValueExpressionAtomic) expr);

        } else {
            System.out.println(expr);
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T> ValueExpr<T> analyzeAtomic(T owner, ValueExpressionAtomic expr) {
        System.out.println(ValueExpressionAtomic.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeCase(T owner, ValueExpressionCase expr) {
        System.out.println(ValueExpressionCase.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeCaseElse(T owner, ValueExpressionCaseElse expr) {
        System.out.println(ValueExpressionCaseElse.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeCaseSearch(T owner, ValueExpressionCaseSearch expr) {
        System.out.println(ValueExpressionCaseSearch.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeCaseSearchContent(T owner, ValueExpressionCaseSearchContent expr) {
        System.out.println(ValueExpressionCaseSearchContent.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeCaseSimple(T owner, ValueExpressionCaseSimple expr) {
        System.out.println(ValueExpressionCaseSimple.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeCaseSimpleContent(T owner, ValueExpressionCaseSimpleContent expr) {
        System.out.println(ValueExpressionCaseSimpleContent.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeCast(T owner, ValueExpressionCast expr) {
        System.out.println(ValueExpressionCast.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeColumn(T owner, ValueExpressionColumn expr) {
        System.out.println(ValueExpressionColumn.class + ":" + expr);

        TableReferenceAnalyzer tableAnalyzer = SqlAnalyzerManager.getInstance().getTableExpressionAnalyzer();

        ValueExpr<T> valueExpr = new ValueExpr<T>();

        Table<ValueExpr<T>> table = tableAnalyzer.analyze(valueExpr, expr.getTableExpr());

        Column<ValueExpr<T>> column = new Column<ValueExpr<T>>();
        column.setOwner(valueExpr);
        column.setTable(table);
        column.setColumnName(expr.getName());

        valueExpr.setOwner(owner);
        valueExpr.setValueType(ValueType.COLUMN);
        valueExpr.setColumn(column);

        return valueExpr;
    }

    private <T> ValueExpr<T> analyzeCombined(T owner, ValueExpressionCombined expr) {
        System.out.println(ValueExpressionCombined.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeDefault(T owner, ValueExpressionDefault expr) {
        System.out.println(ValueExpressionDefault.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeDefaultValue(T owner, ValueExpressionDefaultValue expr) {
        System.out.println(ValueExpressionDefaultValue.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeFunction(T owner, ValueExpressionFunction expr) {
        System.out.println(ValueExpressionFunction.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeLabeledDuration(T owner, ValueExpressionLabeledDuration expr) {
        System.out.println(ValueExpressionLabeledDuration.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeNested(T owner, ValueExpressionNested expr) {
        System.out.println(ValueExpressionNested.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeNullValue(T owner, ValueExpressionNullValue expr) {
        System.out.println(ValueExpressionNullValue.class + ":" + expr);

        ValueExpr<T> valueExpr = new ValueExpr<T>();
        valueExpr.setOwner(owner);
        valueExpr.setValueType(ValueType.NULL);

        return valueExpr;
    }

    private <T> ValueExpr<T> analyzeRow(T owner, ValueExpressionRow expr) {
        System.out.println(ValueExpressionRow.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeScalarSelect(T owner, ValueExpressionScalarSelect expr) {
        System.out.println(ValueExpressionScalarSelect.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ValueExpr<T> analyzeSimple(T owner, ValueExpressionSimple expr) {
        System.out.println(ValueExpressionSimple.class + ":" + expr);

        ValueExpr<T> valueExpr = new ValueExpr<T>();
        valueExpr.setOwner(owner);
        valueExpr.setValueType(ValueType.LITERAL_VALUE);
        valueExpr.setLiteralValue(expr.getValue());

        return valueExpr;
    }

    private <T> ValueExpr<T> analyzeVariable(T owner, ValueExpressionVariable expr) {
        System.out.println(ValueExpressionVariable.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

}
