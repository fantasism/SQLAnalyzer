/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class ResultSpecificationAnalyzer {

    public <T extends Query> void analyze(T owner, QueryResultSpecification result) {

        if (result instanceof ResultColumn) {
            analyzeColumn(owner, (ResultColumn) result);

        } else if (result instanceof ResultTableAllColumns) {
            analyzeTableAllColumn(owner, (ResultTableAllColumns) result);

        } else {
            System.out.println(result);
            throw new RuntimeException("サポートしてません。");
        }
    }

    private <T extends Query> void analyzeColumn(T owner, ResultColumn result) {
        System.out.println(ResultColumn.class + ":" + result);
        ValueExpressionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();
        analyzer.analyze(owner, result.getValueExpr());
    }

    private <T extends Query> void analyzeTableAllColumn(T owner, ResultTableAllColumns result) {
        System.out.println(ResultTableAllColumns.class + ":" + result);
    }

}
