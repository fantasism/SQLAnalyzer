/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
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
public class ResultAnalyzer {

    private SqlAnalyzerContext context;

    public ResultAnalyzer(SqlAnalyzerContext context) {
        this.context = context;
    }

    public <T extends Query> void analyze(T owner, QueryResultSpecification result) {

        System.out.println("[BEGIN] " + result.getClass().getSimpleName() + " : " + result);

        if (result instanceof ResultColumn) {
            analyzeColumn(owner, (ResultColumn) result);

        } else if (result instanceof ResultTableAllColumns) {
            analyzeTableAllColumn(owner, (ResultTableAllColumns) result);

        } else {
            throw new RuntimeException("サポートしてません。");
        }

        System.out.println("[END  ] " + result.getClass().getSimpleName() + " : " + result);

    }

    @SuppressWarnings("unchecked")
    private <T extends Query> void analyzeColumn(T owner, ResultColumn result) {

        ValueAnalyzer analyzer = context.getValueAnalyzer();

        analyzer.analyze(owner, result.getValueExpr());

        for (OrderByResultColumn column : (EList<OrderByResultColumn>) result.getOrderByResultCol()) {
            // TODO
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeTableAllColumn(T owner, ResultTableAllColumns result) {

        TableAnalyzer tableAnalyzer = context.getTableAnalyzer();

        tableAnalyzer.analyze(owner, result.getTableExpr());

    }

}
