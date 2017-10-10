/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
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
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class PredicateAnalyzer {

    public <T extends Query> void analyze(T owner, Predicate cond) {

        if (cond instanceof PredicateBasic) {
            analyzeBasic(owner, (PredicateBasic) cond);

        } else if (cond instanceof PredicateBetween) {
            analyzeBetween(owner, (PredicateBetween) cond);

        } else if (cond instanceof PredicateExists) {
            analyzeExists(owner, (PredicateExists) cond);

        } else if (cond instanceof PredicateInValueList) {
            analyzeInValueList(owner, (PredicateInValueList) cond);

        } else if (cond instanceof PredicateInValueRowSelect) {
            analyzeInValueRowSelect(owner, (PredicateInValueRowSelect) cond);

        } else if (cond instanceof PredicateInValueSelect) {
            analyzeInValueSelect(owner, (PredicateInValueSelect) cond);

        } else if (cond instanceof PredicateIsNull) {
            analyzeIsNull(owner, (PredicateIsNull) cond);

        } else if (cond instanceof PredicateLike) {
            analyzeLike(owner, (PredicateLike) cond);

        } else if (cond instanceof PredicateQuantifiedRowSelect) {
            analyzeQuantifiedRowSelect(owner, (PredicateQuantifiedRowSelect) cond);

        } else if (cond instanceof PredicateQuantifiedValueSelect) {
            analyzeQuantifiedValueSelect(owner, (PredicateQuantifiedValueSelect) cond);

        } else if (cond instanceof PredicateIn) {
            analyzeIn(owner, (PredicateIn) cond);

        } else if (cond instanceof PredicateQuantified) {
            analyzeQuantified(owner, (PredicateQuantified) cond);

        } else if (cond instanceof SearchConditionCombined) {
            analyzeQuantified(owner, (PredicateQuantified) cond);

        } else if (cond instanceof SearchConditionDefault) {
            analyzeQuantified(owner, (PredicateQuantified) cond);

        } else if (cond instanceof SearchConditionNested) {
            analyzeQuantified(owner, (PredicateQuantified) cond);

        } else {
            throw new RuntimeException("サポートしてない検索条件の記載方法が使用されました。"); // TODO エラー
        }

    }

    private <T extends Query> void analyzeBasic(T owner, PredicateBasic cond) {
        System.out.println(PredicateBasic.class + ":" + cond);
        ValueExpressionAnalyzer valueAnalyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();
        if (cond.getLeftValueExpr() != null) {
            valueAnalyzer.analyze(owner, cond.getLeftValueExpr());
        } else {

        }

        if (cond.getRightValueExpr() != null) {
            valueAnalyzer.analyze(owner, cond.getRightValueExpr());
        } else {

        }
    }

    private <T extends Query> void analyzeBetween(T owner, PredicateBetween cond) {
        System.out.println(PredicateBetween.class + ":" + cond);
    }

    private <T extends Query> void analyzeExists(T owner, PredicateExists cond) {
        System.out.println(PredicateExists.class + ":" + cond);
        QueryAnalyzer queryAnalyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();
        Query subQuery = new Query(owner);
        queryAnalyzer.analyze(subQuery, cond.getQueryExpr());
        owner.getSubQueryList().add(subQuery);
    }

    private <T extends Query> void analyzeInValueList(T owner, PredicateInValueList cond) {
        System.out.println(PredicateInValueList.class + ":" + cond);
    }

    private <T extends Query> void analyzeInValueRowSelect(T owner, PredicateInValueRowSelect cond) {
        System.out.println(PredicateInValueRowSelect.class + ":" + cond);
        QueryAnalyzer queryAnalyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();
        Query subQuery = new Query(owner);
        queryAnalyzer.analyze(subQuery, cond.getQueryExpr());
        owner.getSubQueryList().add(subQuery);
    }

    private <T extends Query> void analyzeInValueSelect(T owner, PredicateInValueSelect cond) {
        System.out.println(PredicateInValueSelect.class + ":" + cond);
        QueryAnalyzer queryAnalyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();
        Query subQuery = new Query(owner);
        queryAnalyzer.analyze(subQuery, cond.getQueryExpr());
        owner.getSubQueryList().add(subQuery);
    }

    private <T extends Query> void analyzeIsNull(T owner, PredicateIsNull cond) {
        System.out.println(PredicateIsNull.class + ":" + cond);
    }

    private <T extends Query> void analyzeLike(T owner, PredicateLike cond) {
        System.out.println(PredicateLike.class + ":" + cond);
    }

    private <T extends Query> void analyzeQuantifiedRowSelect(T owner, PredicateQuantifiedRowSelect cond) {
        System.out.println(PredicateQuantifiedRowSelect.class + ":" + cond);
        QueryAnalyzer queryAnalyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();
        Query subQuery = new Query(owner);
        queryAnalyzer.analyze(subQuery, cond.getQueryExpr());
        owner.getSubQueryList().add(subQuery);
    }

    private <T extends Query> void analyzeQuantifiedValueSelect(T owner, PredicateQuantifiedValueSelect cond) {
        System.out.println(PredicateQuantifiedValueSelect.class + ":" + cond);
        QueryAnalyzer queryAnalyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();
        Query subQuery = new Query(owner);
        queryAnalyzer.analyze(subQuery, cond.getQueryExpr());
        owner.getSubQueryList().add(subQuery);
    }

    private <T extends Query> void analyzeIn(T owner, PredicateIn cond) {
        System.out.println(PredicateIn.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeQuantified(T owner, PredicateQuantified cond) {
        System.out.println(PredicateQuantified.class + ":" + cond);
        throw new RuntimeException("サポートしてません。");
    }

}
