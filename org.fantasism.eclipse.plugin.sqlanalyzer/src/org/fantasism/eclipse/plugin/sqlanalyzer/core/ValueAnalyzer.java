/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpressionDefault;
import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
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
public class ValueAnalyzer {

    private SqlAnalyzerContext context;

    public ValueAnalyzer(SqlAnalyzerContext context) {
        this.context = context;
    }

    public <T extends Query> void analyze(T owner, ValueExpression expr) {

        System.out.println("[BEGIN] " + expr.getClass().getSimpleName() + " : " + expr);

        if (expr instanceof ValueExpressionCast) {
            analyzeCast(owner, (ValueExpressionCast) expr);

        } else if (expr instanceof ValueExpressionDefault) {
            analyzeDefault(owner, (ValueExpressionDefault) expr);

        } else if (expr instanceof ValueExpressionCase) {
            analyzeCase(owner, (ValueExpressionCase) expr);

        } else if (expr instanceof ValueExpressionAtomic) {
            analyzeAtomic(owner, (ValueExpressionAtomic) expr);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        System.out.println("[END  ] " + expr.getClass().getSimpleName() + " : " + expr);

    }

    private <T extends Query> void analyzeCase(T owner, ValueExpressionCase expr) {

        if (expr instanceof ValueExpressionCaseSearch) {
            analyzeCaseSearch(owner, (ValueExpressionCaseSearch) expr);

        } else if (expr instanceof ValueExpressionCaseSimple) {
            analyzeCaseSimple(owner, (ValueExpressionCaseSimple) expr);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        analyzeCaseElse(owner, expr.getCaseElse());

    }

    private <T extends Query> void analyzeCaseSearch(T owner, ValueExpressionCaseSearch expr) {

        for (ValueExpressionCaseSearchContent content : (EList<ValueExpressionCaseSearchContent>) expr.getSearchContentList()) {
            analyzeCaseSearchContent(owner, content);
        }

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeCaseSimple(T owner, ValueExpressionCaseSimple expr) {

        for (ValueExpressionCaseSimpleContent content : (EList<ValueExpressionCaseSimpleContent>) expr.getContentList()) {
            analyzeCaseSimpleContent(owner, content);
        }

        analyze(owner, expr.getValueExpr());

    }

    private <T extends Query> void analyzeCaseElse(T owner, ValueExpressionCaseElse expr) {

        analyze(owner, expr.getValueExpr());
    }

    private <T extends Query, U extends Query> void analyzeCaseSearchContent(T owner, ValueExpressionCaseSearchContent expr) {

        ConditionAnalyzer conditionAnalyzer = context.getConditionAnalyzer();

//        // NOTE 親オブジェクトのため解析すると循環参照となるため除外
//        expr.getValueExprCaseSearch(); // TODO 親？

        conditionAnalyzer.analyze(owner, expr.getSearchCondition());

        analyze(owner, expr.getValueExpr());

    }

    private <T extends Query> void analyzeCaseSimpleContent(T owner, ValueExpressionCaseSimpleContent expr) {

//        // NOTE 親オブジェクトのため解析すると循環参照となるため除外
//        analyze(owner, expr.getValueExprCaseSimple());

        analyze(owner, expr.getWhenValueExpr());

        analyze(owner, expr.getResultValueExpr());

    }

    private <T extends Query> void analyzeDefault(T owner, ValueExpressionDefault expr) {
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeQuery(T owner, QueryValueExpression expr) {

        if (expr instanceof ValueExpressionCombined) {
            analyzeCombined(owner, (ValueExpressionCombined) expr);

        } else if (expr instanceof ValueExpressionNested) {
            analyzeNested(owner, (ValueExpressionNested) expr);

        } else if (expr instanceof ValueExpressionRow) {
            analyzeRow(owner, (ValueExpressionRow) expr);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeCombined(T owner, ValueExpressionCombined expr) {

        Query subquery = new Query(owner);

        expr.getCombinedOperator(); // TODO

        analyze(subquery, expr.getLeftValueExpr());

        analyze(subquery, expr.getRightValueExpr());

        owner.getSubQueryList().add(subquery);

        throw new RuntimeException("サポートしてません。");

    }

    private <T extends Query> void analyzeNested(T owner, ValueExpressionNested expr) {

        analyze(owner, expr.getNestedValueExpr());

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeRow(T owner, ValueExpressionRow expr) {

        for (ValueExpression valueExpr : (EList<ValueExpression>) expr.getValueExprList()) {
            analyze(owner, valueExpr);
        }

        throw new RuntimeException("サポートしてません。");

    }

    private <T extends Query> void analyzeAtomic(T owner, ValueExpressionAtomic expr) {

        if (expr instanceof ValueExpressionCast) {
            analyzeCast(owner, (ValueExpressionCast) expr);

        } else if (expr instanceof ValueExpressionColumn) {
            analyzeColumn(owner, (ValueExpressionColumn) expr);

        } else if (expr instanceof ValueExpressionDefaultValue) {
            analyzeDefaultValue(owner, (ValueExpressionDefaultValue) expr);

        } else if (expr instanceof ValueExpressionFunction) {
            analyzeFunction(owner, (ValueExpressionFunction) expr);

        } else if (expr instanceof ValueExpressionLabeledDuration) {
            analyzeLabeledDuration(owner, (ValueExpressionLabeledDuration) expr);

        } else if (expr instanceof ValueExpressionNullValue) {
            analyzeNullValue(owner, (ValueExpressionNullValue) expr);

        } else if (expr instanceof ValueExpressionScalarSelect) {
            analyzeScalarSelect(owner, (ValueExpressionScalarSelect) expr);

        } else if (expr instanceof ValueExpressionSimple) {
            analyzeSimple(owner, (ValueExpressionSimple) expr);

        } else if (expr instanceof ValueExpressionVariable) {
            analyzeVariable(owner, (ValueExpressionVariable) expr);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeCast(T owner, ValueExpressionCast expr) {

        analyze(owner, expr.getValueExpr());

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeColumn(T owner, ValueExpressionColumn expr) {

        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();
        StatementAnalyzer statementAnalyzer = context.getStatementAnalyzer();
        OthersAnalyzer othersAnalyzer = context.getOthersAnalyzer();

//        // NOTE 親のUPDATE文のセット句を参照しているため、ここで解析すると循環するため除外
//        for (UpdateAssignmentExpression assignExpr : (EList<UpdateAssignmentExpression>) expr.getAssignmentExprTarget()) {
//            othersAnalyzer.analyze(owner, assignExpr);
//        }

//        // NOTE 親のINSERT文のバリューズ句を参照しているため、ここで解析すると循環するため除外
//        for (QueryStatement statement : (EList<QueryStatement>) expr.getInsertStatement()) {
//            Query subquery = new Query(owner);
//            statementAnalyzer.analyze(subquery, statement);
//            owner.getSubQueryList().add(subquery);
//        }

//        // カラムを所有しているテーブル
//        if (expr.getTableExpr() != null) {
//            tableAnalyzer.analyze(owner, expr.getTableExpr());
//        } else {
//        }

        // TODO 謎
        if (expr.getParentTableExpr() != null) {
            tableAnalyzer.analyze(owner, expr.getParentTableExpr());
            throw new RuntimeException("サポートしてません。");
        } else {
        }

        // TODO 謎
        if (expr.getTableInDatabase() != null) {
            tableAnalyzer.analyze(owner, expr.getTableInDatabase());
            throw new RuntimeException("サポートしてません。");
        } else {
        }

        for (MergeInsertSpecification spec : (EList<MergeInsertSpecification>) expr.getMergeInsertSpec()) {
            othersAnalyzer.analyze(owner, spec);
        }

    }

    private <T extends Query> void analyzeDefaultValue(T owner, ValueExpressionDefaultValue expr) {
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeFunction(T owner, ValueExpressionFunction expr) {

        expr.isSpecialRegister(); // TODO

        expr.isDistinct(); // TODO

        expr.isColumnFunction(); // TODO

        for (Object parameter : expr.getParameterList()) {
            // TODO
        }

        expr.getFunction(); // TODO

    }

    private <T extends Query> void analyzeLabeledDuration(T owner, ValueExpressionLabeledDuration expr) {

        Query subquery = new Query(owner);

        expr.getLabeledDurationType(); // TODO

        analyze(subquery, expr.getValueExpr());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeNullValue(T owner, ValueExpressionNullValue expr) {

        // TODO

    }

    private <T extends Query> void analyzeScalarSelect(T owner, ValueExpressionScalarSelect expr) {

        QueryAnalyzer analyzer = context.getQueryAnalyzer();

        Query subquery = new Query(owner);

        analyzer.analyze(subquery, expr.getQueryExpr());

        owner.getSubQueryList().add(subquery);

    }

    private <T extends Query> void analyzeSimple(T owner, ValueExpressionSimple expr) {

        expr.getValue(); // TODO

    }

    private <T extends Query> void analyzeVariable(T owner, ValueExpressionVariable expr) {

        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();

        Query subquery = new Query(owner);

        tableAnalyzer.analyze(subquery, expr.getQuerySelect());

        owner.getSubQueryList().add(subquery);

        throw new RuntimeException("サポートしてません。");

    }

}
