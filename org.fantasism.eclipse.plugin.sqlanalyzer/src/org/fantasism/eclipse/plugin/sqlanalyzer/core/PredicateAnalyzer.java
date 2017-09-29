/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.PredicateIn;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantified;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ConditionExpr;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ConditionExpr.ConditionExprType;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ValueExpr;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ValueExpr.ValueType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class PredicateAnalyzer {

    public <T> ConditionExpr<T> analyze(T owner, Predicate cond) {

        if (cond instanceof PredicateBasic) {
            return analyzeBasic(owner, (PredicateBasic) cond);

        } else if (cond instanceof PredicateBetween) {
            return analyzeBetween(owner, (PredicateBetween) cond);

        } else if (cond instanceof PredicateExists) {
            return analyzeExists(owner, (PredicateExists) cond);

        } else if (cond instanceof PredicateInValueList) {
            return analyzeInValueList(owner, (PredicateInValueList) cond);

        } else if (cond instanceof PredicateInValueRowSelect) {
            return analyzeInValueRowSelect(owner, (PredicateInValueRowSelect) cond);

        } else if (cond instanceof PredicateInValueSelect) {
            return analyzeInValueSelect(owner, (PredicateInValueSelect) cond);

        } else if (cond instanceof PredicateIsNull) {
            return analyzeIsNull(owner, (PredicateIsNull) cond);

        } else if (cond instanceof PredicateLike) {
            return analyzeLike(owner, (PredicateLike) cond);

        } else if (cond instanceof PredicateQuantifiedRowSelect) {
            return analyzeQuantifiedRowSelect(owner, (PredicateQuantifiedRowSelect) cond);

        } else if (cond instanceof PredicateQuantifiedValueSelect) {
            return analyzeQuantifiedValueSelect(owner, (PredicateQuantifiedValueSelect) cond);

        } else if (cond instanceof PredicateIn) {
            return analyzeIn(owner, (PredicateIn) cond);

        } else if (cond instanceof PredicateQuantified) {
            return analyzeQuantified(owner, (PredicateQuantified) cond);

        } else if (cond instanceof SearchConditionCombined) {
            return analyzeQuantified(owner, (PredicateQuantified) cond);

        } else if (cond instanceof SearchConditionDefault) {
            return analyzeQuantified(owner, (PredicateQuantified) cond);

        } else if (cond instanceof SearchConditionNested) {
            return analyzeQuantified(owner, (PredicateQuantified) cond);

        } else {
            throw new RuntimeException("サポートしてない検索条件の記載方法が使用されました。"); // TODO エラー
        }

    }

    private <T> ConditionExpr<T> analyzeBasic(T owner, PredicateBasic cond) {
        System.out.println(PredicateBasic.class + ":" + cond);

        ValueExpressionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();

        ConditionExpr<T> condition = new ConditionExpr<T>();

        ValueExpr<ConditionExpr<T>> left = analyzer.analyze(condition, cond.getLeftValueExpr());
        ValueExpr<ConditionExpr<T>> right = analyzer.analyze(condition, cond.getRightValueExpr());

        condition.setOwner(owner);
        String operator = cond.getComparisonOperator().getName();
        if ("EQUAL".equals(operator)) {
            condition.setConditionExprType(ConditionExprType.EQUALS); // TODO 条件種別
        } else {
            condition.setConditionExprType(ConditionExprType.EQUALS); // TODO 条件種別
        }
        condition.setSrcValue(left);
        condition.setDestValue(right);

        return condition;
    }

    private <T> ConditionExpr<T> analyzeBetween(T owner, PredicateBetween cond) {
        System.out.println(PredicateBetween.class + ":" + cond);

        ValueExpressionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();

        ConditionExpr<T> condition = new ConditionExpr<T>();

        ValueExpr<ConditionExpr<T>> left = analyzer.analyze(condition, cond.getLeftValueExpr());
        ValueExpr<ConditionExpr<T>> rightFrom = analyzer.analyze(condition, cond.getRightValueExpr1());
        ValueExpr<ConditionExpr<T>> rightTo = analyzer.analyze(condition, cond.getRightValueExpr2());

        condition.setOwner(owner);
        condition.setConditionExprType(ConditionExprType.BETWEEN);
        condition.setSrcValue(left);
        condition.setBetweenFrom(rightFrom);
        condition.setBetweenTo(rightTo);

        return condition;
    }

    private <T> ConditionExpr<T> analyzeExists(T owner, PredicateExists cond) {
        System.out.println(PredicateExists.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ConditionExpr<T> analyzeInValueList(T owner, PredicateInValueList cond) {
        System.out.println(PredicateInValueList.class + ":" + cond);

        ValueExpressionAnalyzer valueExprAnalyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();

        ConditionExpr<T> condition = new ConditionExpr<>();

        ValueExpr<ConditionExpr<T>> srcValue = valueExprAnalyzer.analyze(condition, cond.getValueExpr());

        ValueExpr<ConditionExpr<T>> firstDestValue = null;
        ValueExpr<ConditionExpr<T>> lastDestValue = null;
        for (Object value : cond.getValueExprList()) {
            ValueExpr<ConditionExpr<T>> tempDestValue = valueExprAnalyzer.analyze(condition, (ValueExpression) value);
            if (lastDestValue == null) {
                firstDestValue = tempDestValue;
            } else {
                lastDestValue.setNestedValue(tempDestValue);
            }
            lastDestValue = tempDestValue;
        }

        condition.setOwner(owner);
        if (cond.isNotIn()) {
            condition.setConditionExprType(ConditionExprType.NOT_IN);
        } else {
            condition.setConditionExprType(ConditionExprType.IN);
        }
        condition.setSrcValue(srcValue);
        condition.setDestValue(firstDestValue);

        return condition;
    }

    private <T> ConditionExpr<T> analyzeInValueRowSelect(T owner, PredicateInValueRowSelect cond) {
        System.out.println(PredicateInValueRowSelect.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ConditionExpr<T> analyzeInValueSelect(T owner, PredicateInValueSelect cond) {
        System.out.println(PredicateInValueSelect.class + ":" + cond);

        ValueExpressionAnalyzer valueExprAnalyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();
        QueryExpressionAnalyzer queryExprAnalyzer = SqlAnalyzerManager.getInstance().getQueryExpressionAnalyzer();

        ConditionExpr<T> condition = new ConditionExpr<>();

        ValueExpr<ConditionExpr<T>> srcValue = valueExprAnalyzer.analyze(condition, cond.getValueExpr());

        ValueExpr<ConditionExpr<T>> destValue = new ValueExpr<ConditionExpr<T>>();

        Query<ValueExpr<ConditionExpr<T>>> destQuery = queryExprAnalyzer.analyze(destValue, cond.getQueryExpr());

        destValue.setOwner(condition);              // 所有者
        destValue.setValueType(ValueType.SUBQUERY); // 値種別
        destValue.setQuery(destQuery);              // クエリ

        condition.setOwner(owner);
        if (cond.isNotIn()) {
            condition.setConditionExprType(ConditionExprType.NOT_IN);
        } else {
            condition.setConditionExprType(ConditionExprType.IN);
        }
        condition.setSrcValue(srcValue);
        condition.setDestValue(destValue);

        return condition;
    }

    private <T> ConditionExpr<T> analyzeIsNull(T owner, PredicateIsNull cond) {
        System.out.println(PredicateIsNull.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ConditionExpr<T> analyzeLike(T owner, PredicateLike cond) {
        System.out.println(PredicateLike.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");

    }

    private <T> ConditionExpr<T> analyzeQuantifiedRowSelect(T owner, PredicateQuantifiedRowSelect cond) {
        System.out.println(PredicateQuantifiedRowSelect.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ConditionExpr<T> analyzeQuantifiedValueSelect(T owner, PredicateQuantifiedValueSelect cond) {
        System.out.println(PredicateQuantifiedValueSelect.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ConditionExpr<T> analyzeIn(T owner, PredicateIn cond) {
        System.out.println(PredicateIn.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T> ConditionExpr<T> analyzeQuantified(T owner, PredicateQuantified cond) {
        System.out.println(PredicateQuantified.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

}
