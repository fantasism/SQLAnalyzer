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
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class ValueExpressionAnalyzer {

    public <T extends Query> void analyze(T owner, ValueExpression expr) {

        if (expr instanceof ValueExpressionCaseElse) {
            analyzeCaseElse(owner, (ValueExpressionCaseElse) expr);

        } else if (expr instanceof ValueExpressionCaseSearch) {
            analyzeCaseSearch(owner, (ValueExpressionCaseSearch) expr);

        } else if (expr instanceof ValueExpressionCaseSearchContent) {
            analyzeCaseSearchContent(owner, (ValueExpressionCaseSearchContent) expr);

        } else if (expr instanceof ValueExpressionCaseSimple) {
            analyzeCaseSimple(owner, (ValueExpressionCaseSimple) expr);

        } else if (expr instanceof ValueExpressionCaseSimpleContent) {
            analyzeCaseSimpleContent(owner, (ValueExpressionCaseSimpleContent) expr);

        } else if (expr instanceof ValueExpressionCast) {
            analyzeCast(owner, (ValueExpressionCast) expr);

        } else if (expr instanceof ValueExpressionColumn) {
            analyzeColumn(owner, (ValueExpressionColumn) expr);

        } else if (expr instanceof ValueExpressionCombined) {
            analyzeCombined(owner, (ValueExpressionCombined) expr);

        } else if (expr instanceof ValueExpressionDefault) {
            analyzeDefault(owner, (ValueExpressionDefault) expr);

        } else if (expr instanceof ValueExpressionDefaultValue) {
            analyzeDefaultValue(owner, (ValueExpressionDefaultValue) expr);

        } else if (expr instanceof ValueExpressionFunction) {
            analyzeFunction(owner, (ValueExpressionFunction) expr);

        } else if (expr instanceof ValueExpressionLabeledDuration) {
            analyzeLabeledDuration(owner, (ValueExpressionLabeledDuration) expr);

        } else if (expr instanceof ValueExpressionNested) {
            analyzeNested(owner, (ValueExpressionNested) expr);

        } else if (expr instanceof ValueExpressionNullValue) {
            analyzeNullValue(owner, (ValueExpressionNullValue) expr);

        } else if (expr instanceof ValueExpressionRow) {
            analyzeRow(owner, (ValueExpressionRow) expr);

        } else if (expr instanceof ValueExpressionScalarSelect) {
            analyzeScalarSelect(owner, (ValueExpressionScalarSelect) expr);

        } else if (expr instanceof ValueExpressionSimple) {
            analyzeSimple(owner, (ValueExpressionSimple) expr);

        } else if (expr instanceof ValueExpressionVariable) {
            analyzeVariable(owner, (ValueExpressionVariable) expr);

        } else if (expr instanceof ValueExpressionCase) {
            analyzeCase(owner, (ValueExpressionCase) expr);

        } else if (expr instanceof ValueExpressionAtomic) {
            analyzeAtomic(owner, (ValueExpressionAtomic) expr);

        } else {
            System.out.println(expr);
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeAtomic(T owner, ValueExpressionAtomic expr) {
        System.out.println(ValueExpressionAtomic.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeCase(T owner, ValueExpressionCase expr) {
        System.out.println(ValueExpressionCase.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeCaseElse(T owner, ValueExpressionCaseElse expr) {
        System.out.println(ValueExpressionCaseElse.class + ":" + expr);
        analyze(owner, expr.getValueExpr());
    }

    private <T extends Query> void analyzeCaseSearch(T owner, ValueExpressionCaseSearch expr) {
        System.out.println(ValueExpressionCaseSearch.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeCaseSearchContent(T owner, ValueExpressionCaseSearchContent expr) {
        System.out.println(ValueExpressionCaseSearchContent.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeCaseSimple(T owner, ValueExpressionCaseSimple expr) {
        System.out.println(ValueExpressionCaseSimple.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeCaseSimpleContent(T owner, ValueExpressionCaseSimpleContent expr) {
        System.out.println(ValueExpressionCaseSimpleContent.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeCast(T owner, ValueExpressionCast expr) {
        System.out.println(ValueExpressionCast.class + ":" + expr);
        analyze(owner, expr.getValueExprCast());
    }

    private <T extends Query> void analyzeColumn(T owner, ValueExpressionColumn expr) {
        System.out.println(ValueExpressionColumn.class + ":" + expr);
    }

    private <T extends Query> void analyzeCombined(T owner, ValueExpressionCombined expr) {
        System.out.println(ValueExpressionCombined.class + ":" + expr);
        analyze(owner, expr.getLeftValueExpr());
        analyze(owner, expr.getRightValueExpr());
    }

    private <T extends Query> void analyzeDefault(T owner, ValueExpressionDefault expr) {
        System.out.println(ValueExpressionDefault.class + ":" + expr);
    }

    private <T extends Query> void analyzeDefaultValue(T owner, ValueExpressionDefaultValue expr) {
        System.out.println(ValueExpressionDefaultValue.class + ":" + expr);
    }

    private <T extends Query> void analyzeFunction(T owner, ValueExpressionFunction expr) {
        System.out.println(ValueExpressionFunction.class + ":" + expr);
    }

    private <T extends Query> void analyzeLabeledDuration(T owner, ValueExpressionLabeledDuration expr) {
        System.out.println(ValueExpressionLabeledDuration.class + ":" + expr);
    }

    private <T extends Query> void analyzeNested(T owner, ValueExpressionNested expr) {
        System.out.println(ValueExpressionNested.class + ":" + expr);
        analyze(owner, expr.getNest());
    }

    private <T extends Query> void analyzeNullValue(T owner, ValueExpressionNullValue expr) {
        System.out.println(ValueExpressionNullValue.class + ":" + expr);
    }

    private <T extends Query> void analyzeRow(T owner, ValueExpressionRow expr) {
        System.out.println(ValueExpressionRow.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeScalarSelect(T owner, ValueExpressionScalarSelect expr) {
        System.out.println(ValueExpressionScalarSelect.class + ":" + expr);
        Query subQuery = new Query(owner);
        QueryAnalyzer analyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();
        analyzer.analyze(subQuery, expr.getQueryExpr());
        owner.getSubQueryList().add(subQuery);
    }

    private <T extends Query> void analyzeSimple(T owner, ValueExpressionSimple expr) {
        System.out.println(ValueExpressionSimple.class + ":" + expr);
    }

    private <T extends Query> void analyzeVariable(T owner, ValueExpressionVariable expr) {
        System.out.println(ValueExpressionVariable.class + ":" + expr);
        throw new RuntimeException("サポートしてません。");
    }

}
