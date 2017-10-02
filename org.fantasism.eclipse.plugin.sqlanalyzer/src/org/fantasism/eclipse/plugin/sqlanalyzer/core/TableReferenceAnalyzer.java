/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableQueryLateral;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.AbstractModel;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.ConditionExpr;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Table;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Table.JoinType;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Table.TableType;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class TableReferenceAnalyzer {

    public <T extends AbstractModel<?>> Table<T> analyze(T owner, TableReference table) {

        if (table instanceof TableExpression) {
            if (table instanceof TableFunction) {
                return analyzeFunction(owner, (TableFunction) table);

            } else if (table instanceof TableQueryLateral) {
                return analyzeQueryLateral(owner, (TableQueryLateral) table);

            } else if (table instanceof TableInDatabase) {
                return analyzeInDatabase(owner, (TableInDatabase) table);

            } else if (table instanceof WithTableReference) {
                return analyzeWithTableReference(owner, (WithTableReference) table);

            } else {
                System.out.println(table);
                throw new RuntimeException("サポートしてません。");
            }

        } else if (table instanceof TableJoined) {
            return analyzeJoined(owner, (TableJoined) table);

        } else if (table instanceof TableNested) {
            return analyzeNested(owner, (TableNested) table);

        } else {
            System.out.println(table);
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends AbstractModel<?>> Table<T> analyzeFunction(T owner, TableFunction tableRef) {
        System.out.println(TableFunction.class + ":" + tableRef);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends AbstractModel<?>> Table<T> analyzeQueryLateral(T owner, TableQueryLateral tableRef) {
        System.out.println(TableQueryLateral.class + ":" + tableRef);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends AbstractModel<?>> Table<T> analyzeInDatabase(T owner, TableInDatabase tableRef) {
        System.out.println(TableInDatabase.class + ":" + tableRef);

        Table<T> table = new Table<T>(owner);
        table.setTableType(TableType.TABLE);                        // テーブル種別
        table.setTableName(tableRef.getName());                     // テーブル名
        table.setAlias(tableRef.getTableCorrelation().getName());   // 別名

        return table;
    }

    private <T extends AbstractModel<?>> Table<T> analyzeWithTableReference(T owner, WithTableReference tableRef) {
        System.out.println(WithTableReference.class + ":" + tableRef);

        Table<T> table = new Table<T>(owner);
        table.setTableType(TableType.TABLE);                        // テーブル種別
        table.setTableName(tableRef.getName());                     // テーブル名
        table.setAlias(tableRef.getTableCorrelation().getName());   // 別名

        return table;
    }

    private <T extends AbstractModel<?>> Table<T> analyzeJoined(T owner, TableJoined tableRef) {
        System.out.println(TableJoined.class + ":" + tableRef);

        Table<T> leftTable = analyze(owner, tableRef.getTableRefLeft());
        Table<T> rightTable = analyze(owner, tableRef.getTableRefRight());

        leftTable.setJoinType(JoinType.NONE);           // TODO 結合方法
        leftTable.setJoinCondition(null);               // TODO 結合条件
        leftTable.setJoinTable(rightTable);             // 結合元テーブル

        tableRef.getJoinOperator();

        ConditionExpr<Table<T>> rightCondition = new ConditionExpr<Table<T>>(rightTable);
        rightCondition.setConditionExprType(null); // TODO

        SqlAnalyzerManager.getInstance().getSearchConditionAnalyzer().analyze(rightCondition, tableRef.getJoinCondition());

        rightTable.setJoinType(JoinType.INNER_JOIN);    // TODO 結合方法
        rightTable.setJoinCondition(rightCondition);         // 結合条件
        rightTable.setJoinTable(null);                  // 結合元テーブル

        return leftTable;
    }

    private <T extends AbstractModel<?>> Table<T> analyzeNested(T owner, TableNested table) {
        System.out.println(TableNested.class + ":" + table);
        throw new RuntimeException("サポートしてません。");
    }

}
