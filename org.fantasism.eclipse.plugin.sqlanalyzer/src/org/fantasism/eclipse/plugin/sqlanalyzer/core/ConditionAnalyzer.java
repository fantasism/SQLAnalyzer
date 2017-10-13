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
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.emf.common.util.EList;
import org.fantasism.eclipse.plugin.sqlanalyzer.SqlAnalyzerContext;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class ConditionAnalyzer {

    private SqlAnalyzerContext context;

    public ConditionAnalyzer(SqlAnalyzerContext context) {
        this.context = context;
    }

    public <T extends Query> void analyze(T owner, QuerySearchCondition cond) {

        System.out.println("[BEGIN] " + cond.getClass().getSimpleName() + " : " + cond);

        if (cond instanceof SearchConditionCombined) {
            analyzeCombined(owner, (SearchConditionCombined) cond);

        } else if (cond instanceof SearchConditionDefault) {
            analyzeDefault(owner, (SearchConditionDefault) cond);

        } else if (cond instanceof SearchConditionNested) {
            analyzeNested(owner, (SearchConditionNested) cond);

        } else if (cond instanceof Predicate) {
            analyzePredicate(owner, (Predicate) cond);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        System.out.println("[END  ] " + cond.getClass().getSimpleName() + " : " + cond);

    }

    private <T extends Query> void analyzeCombined(T owner, SearchConditionCombined cond) {

        cond.getCombinedOperator(); // TODO

        analyze(owner, cond.getLeftCondition());

        analyze(owner, cond.getRightCondition());

    }

    private <T extends Query> void analyzeDefault(T owner, SearchConditionDefault cond) {
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeNested(T owner, SearchConditionNested cond) {

        analyze(owner, cond.getNestedCondition());

    }

    private <T extends Query> void analyzePredicate(T owner, Predicate cond) {

        if (cond instanceof PredicateBasic) {
            analyzeBasic(owner, (PredicateBasic) cond);

        } else if (cond instanceof PredicateBetween) {
            analyzeBetween(owner, (PredicateBetween) cond);

        } else if (cond instanceof PredicateExists) {
            analyzeExists(owner, (PredicateExists) cond);

        } else if (cond instanceof PredicateIsNull) {
            analyzeIsNull(owner, (PredicateIsNull) cond);

        } else if (cond instanceof PredicateLike) {
            analyzeLike(owner, (PredicateLike) cond);

        } else if (cond instanceof PredicateIn) {
            analyzeIn(owner, (PredicateIn) cond);

        } else if (cond instanceof PredicateQuantified) {
            analyzeQuantified(owner, (PredicateQuantified) cond);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeBasic(T owner, PredicateBasic cond) {

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        cond.getComparisonOperator(); // TODO

        valueAnalyzer.analyze(owner, cond.getLeftValueExpr());

        valueAnalyzer.analyze(owner, cond.getRightValueExpr());

    }

    private <T extends Query> void analyzeBetween(T owner, PredicateBetween cond) {

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        cond.isNegatedCondition(); // TODO

        cond.isNotBetween(); // TODO

        valueAnalyzer.analyze(owner, cond.getLeftValueExpr());

        valueAnalyzer.analyze(owner, cond.getRightValueExpr1());

        valueAnalyzer.analyze(owner, cond.getRightValueExpr2());

    }

    private <T extends Query> void analyzeExists(T owner, PredicateExists cond) {

        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();

        Query subquery = new Query(owner);

        cond.isNegatedCondition(); // TODO

        tableAnalyzer.analyze(subquery, cond.getQueryExpr());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeIn(T owner, PredicateIn cond) {

        if (cond instanceof PredicateInValueList) {
            analyzeInValueList(owner, (PredicateInValueList) cond);

        } else if (cond instanceof PredicateInValueRowSelect) {
            analyzeInValueRowSelect(owner, (PredicateInValueRowSelect) cond);

        } else if (cond instanceof PredicateInValueSelect) {
            analyzeInValueSelect(owner, (PredicateInValueSelect) cond);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeInValueList(T owner, PredicateInValueList cond) {

        ValueAnalyzer valueAnalyer = context.getValueAnalyzer();

        cond.isNegatedCondition(); // TODO

        cond.isNotIn(); // TODO

        valueAnalyer.analyze(owner, cond.getValueExpr());

        for (ValueExpression valueExpr : (EList<ValueExpression>) cond.getValueExprList()) {
            valueAnalyer.analyze(owner, valueExpr);
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeInValueRowSelect(T owner, PredicateInValueRowSelect cond) {

        ValueAnalyzer valueAnalyer = context.getValueAnalyzer();
        QueryAnalyzer queryAnalyzer = context.getQueryAnalyzer();

        cond.isNegatedCondition(); // TODO

        cond.isNotIn(); // TODO

        Query subquery = new Query(owner);

        for (ValueExpression valueExpr : (EList<ValueExpression>) cond.getValueExprList()) {
            valueAnalyer.analyze(owner, valueExpr);
        }

        queryAnalyzer.analyze(subquery, cond.getQueryExpr());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeInValueSelect(T owner, PredicateInValueSelect cond) {

        QueryAnalyzer queryAnalyzer = context.getQueryAnalyzer();
        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        Query subquery = new Query(owner);

        cond.isNegatedCondition(); // TODO

        cond.isNotIn(); // TODO

        valueAnalyzer.analyze(owner, cond.getValueExpr());

        queryAnalyzer.analyze(subquery, cond.getQueryExpr());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeIsNull(T owner, PredicateIsNull cond) {

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        cond.isNegatedCondition(); // TODO

        cond.isNotNull(); // TODO

        valueAnalyzer.analyze(owner, cond.getValueExpr());

    }

    private <T extends Query> void analyzeLike(T owner, PredicateLike cond) {

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();

        cond.isNegatedCondition(); // TODO

        cond.isNotLike(); // TODO

        valueAnalyzer.analyze(owner, cond.getMatchingValueExpr()); // TODO

        if (cond.getEscapeValueExpr() != null) {
            valueAnalyzer.analyze(owner, cond.getEscapeValueExpr());  // TODO
        } else {
        }

        valueAnalyzer.analyze(owner, cond.getPatternValueExpr());// TODO

    }

    private <T extends Query> void analyzeQuantified(T owner, PredicateQuantified cond) {

        if (cond instanceof PredicateQuantifiedRowSelect) {
            analyzeQuantifiedRowSelect(owner, (PredicateQuantifiedRowSelect) cond);

        } else if (cond instanceof PredicateQuantifiedValueSelect) {
            analyzeQuantifiedValueSelect(owner, (PredicateQuantifiedValueSelect) cond);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeQuantifiedRowSelect(T owner, PredicateQuantifiedRowSelect cond) {

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();
        QueryAnalyzer queryAnalyzer = context.getQueryAnalyzer();

        Query subquery = new Query(owner);

        cond.isNegatedCondition(); // TODO

        cond.getQuantifiedType(); // TODO

        for (ValueExpression valueExpr : (EList<ValueExpression>) cond.getValueExprList()) {
            valueAnalyzer.analyze(owner, valueExpr);
        }

        queryAnalyzer.analyze(subquery, cond.getQueryExpr());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeQuantifiedValueSelect(T owner, PredicateQuantifiedValueSelect cond) {

        ValueAnalyzer valueAnalyzer = context.getValueAnalyzer();
        QueryAnalyzer queryAnalyzer = context.getQueryAnalyzer();

        Query subsuery = new Query(owner);

        cond.isNegatedCondition(); // TODO

        cond.getQuantifiedType(); // TODO

        cond.getComparisonOperator(); // TODO

        valueAnalyzer.analyze(owner, cond.getValueExpr());

        queryAnalyzer.analyze(subsuery, cond.getQueryExpr());

        owner.getSubQueryList().add(subsuery);

    }

}
