/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.AbstractModel;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Column;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.SelectColumn;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.SelectColumn.ColumnType;
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
public class ResultSpecificationAnalyzer {

    public <T extends AbstractModel<?>> SelectColumn<T> analyze(T owner, QueryResultSpecification result) {

        if (result instanceof ResultColumn) {
            return analyzeColumn(owner, (ResultColumn) result);

        } else if (result instanceof ResultTableAllColumns) {
            return analyzeTableAllColumn(owner, (ResultTableAllColumns) result);

        } else {
            System.out.println(result);
            throw new RuntimeException("サポートしてません。");
        }
    }

    private <T extends AbstractModel<?>> SelectColumn<T> analyzeColumn(T owner, ResultColumn result) {
        System.out.println(ResultColumn.class + ":" + result);

        ValueExpressionAnalyzer analyzer = SqlAnalyzerManager.getInstance().getValueExpressionAnalyzer();

        SelectColumn<T> selectColumn = new SelectColumn<T>(owner);

        ValueExpr<SelectColumn<T>> value = analyzer.analyze(selectColumn, result.getValueExpr());

        selectColumn.setColumnType(ColumnType.COLUMN);
        selectColumn.setValue(value);
        selectColumn.setAlias(result.getName());

        return selectColumn;
    }

    private <T extends AbstractModel<?>> SelectColumn<T> analyzeTableAllColumn(T owner, ResultTableAllColumns result) {
        System.out.println(ResultTableAllColumns.class + ":" + result);

        SelectColumn<T> selectColumn = new SelectColumn<T>(owner);

        selectColumn.setColumnType(ColumnType.ALL_COLUMNS);
        selectColumn.setValue(null);
        selectColumn.setAlias(null);

        return selectColumn;
    }

}
