/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ConditionExpr;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ConditionExpr.ConditionExprType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SearchConditionAnalyzer {

    public <T extends ConditionExpr<?>> ConditionExpr<?> analyze(T owner, QuerySearchCondition cond) {

        if (cond instanceof SearchConditionCombined) {
            if ("AND".equals(((SearchConditionCombined)cond).getCombinedOperator().getName())) {
                return analyzeAndCombined(owner, (SearchConditionCombined) cond);
            } else {
                return analyzeOrCombined(owner, (SearchConditionCombined) cond);
            }

        } else if (cond instanceof SearchConditionDefault) {
            return analyzeDefault(owner, (SearchConditionDefault) cond);

        } else if (cond instanceof SearchConditionNested) {
            return analyzeNestedAnd(owner, (SearchConditionNested) cond);

        } else if (cond instanceof Predicate) {
            PredicateAnalyzer analyzer = SqlAnalyzerManager.getInstance().getPredicateAnalyzer();
            return analyzer.analyze(owner, (Predicate) cond);

        } else {
            System.out.println(cond);
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends ConditionExpr<?>> ConditionExpr<?> analyzeAndCombined(T owner, SearchConditionCombined searchCond) {

        System.out.println(SearchConditionCombined.class + ":[BEGIN]" + searchCond);

        ConditionExpr<?> left = null;
        ConditionExpr<?> right = null;

        searchCond.isNegatedCondition(); // TODO ???
        String operator = searchCond.getCombinedOperator().getName();

        if (searchCond.getLeftCondition() instanceof SearchConditionCombined) {
            analyze(owner, searchCond.getLeftCondition());
        } else if (searchCond.getLeftCondition() instanceof SearchConditionNested) {
            owner.getAndConditionList().add(analyzeNestedAnd(owner, (SearchConditionNested) searchCond.getLeftCondition()));
        } else if (searchCond.getLeftCondition() instanceof SearchConditionDefault) {
            owner.getAndConditionList().add(analyze(owner, searchCond.getLeftCondition()));
        } else if (searchCond.getLeftCondition() instanceof Predicate) {
            owner.getAndConditionList().add(analyze(owner, searchCond.getLeftCondition()));
        } else {
//            left = analyze(owner, searchCond.getLeftCondition());
        }

        if (searchCond.getRightCondition() instanceof SearchConditionCombined) {
            analyze(owner, searchCond.getRightCondition());
        } else if (searchCond.getRightCondition() instanceof SearchConditionNested) {
            owner.getAndConditionList().add(analyzeNestedAnd(owner, (SearchConditionNested) searchCond.getRightCondition()));
        } else if (searchCond.getRightCondition() instanceof SearchConditionDefault) {
            owner.getAndConditionList().add(analyze(owner, searchCond.getRightCondition()));
        } else if (searchCond.getRightCondition() instanceof Predicate) {
            owner.getAndConditionList().add(analyze(owner, searchCond.getRightCondition()));
        } else {
//            right = analyze(owner, searchCond.getRightCondition());
        }

        System.out.println(SearchConditionCombined.class + ":[END  ]" + searchCond);

        return right;
    }

    private <T extends ConditionExpr<?>> ConditionExpr<?> analyzeOrCombined(T owner, SearchConditionCombined searchCond) {

        System.out.println(SearchConditionCombined.class + ":[BEGIN]" + searchCond);

        ConditionExpr<?> left = null;
        ConditionExpr<?> right = null;

        searchCond.isNegatedCondition(); // TODO ???
        String operator = searchCond.getCombinedOperator().getName();

        if (searchCond.getLeftCondition() instanceof SearchConditionCombined) {
            analyze(owner, searchCond.getLeftCondition());
        } else if (searchCond.getLeftCondition() instanceof SearchConditionNested) {
            owner.getOrConditionList().add(analyzeNestedAnd(owner, (SearchConditionNested) searchCond.getLeftCondition()));
        } else if (searchCond.getLeftCondition() instanceof SearchConditionDefault) {
            owner.getOrConditionList().add(analyze(owner, searchCond.getLeftCondition()));
        } else if (searchCond.getLeftCondition() instanceof Predicate) {
            owner.getOrConditionList().add(analyze(owner, searchCond.getLeftCondition()));
        } else {
//            left = analyze(owner, searchCond.getLeftCondition());
        }

        if (searchCond.getRightCondition() instanceof SearchConditionCombined) {
            analyze(owner, searchCond.getRightCondition());
        } else if (searchCond.getRightCondition() instanceof SearchConditionNested) {
            owner.getOrConditionList().add(analyzeNestedAnd(owner, (SearchConditionNested) searchCond.getRightCondition()));
        } else if (searchCond.getRightCondition() instanceof SearchConditionDefault) {
            owner.getOrConditionList().add(analyze(owner, searchCond.getRightCondition()));
        } else if (searchCond.getRightCondition() instanceof Predicate) {
            owner.getOrConditionList().add(analyze(owner, searchCond.getRightCondition()));
        } else {
//            right = analyze(owner, searchCond.getRightCondition());
        }

        System.out.println(SearchConditionCombined.class + ":[END  ]" + searchCond);

        return owner;
    }

    private <T extends ConditionExpr<?>> ConditionExpr<?> analyzeDefault(T owner, SearchConditionDefault searchCond) {
        System.out.println(SearchConditionDefault.class + ":" + searchCond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends ConditionExpr<?>> ConditionExpr<?> analyzeNestedAnd(T owner, SearchConditionNested searchCond) {

        System.out.println(SearchConditionNested.class + ":[BEGIN]" + searchCond);

        ConditionExpr<T> nested = new ConditionExpr<T>(owner);
        nested.setConditionExprType(ConditionExprType.NESTED);

        analyze(nested, searchCond.getNestedCondition());

        System.out.println(SearchConditionNested.class + ":[END  ]" + searchCond);

        return nested;
    }

    private <T extends ConditionExpr<?>> ConditionExpr<?> analyzeNestedOr(T owner, SearchConditionNested searchCond) {

        System.out.println(SearchConditionNested.class + ":[BEGIN]" + searchCond);

        ConditionExpr<T> nested = new ConditionExpr<T>(owner);
        nested.setConditionExprType(ConditionExprType.NESTED);

        analyze(nested, searchCond.getNestedCondition());

        System.out.println(SearchConditionNested.class + ":[END  ]" + searchCond);

        return nested;
    }

}
