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
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class TableReferenceAnalyzer {

    public <T extends Query> void analyze(T owner, TableReference table) {

        if (table instanceof TableExpression) {
            if (table instanceof TableFunction) {
                analyzeFunction(owner, (TableFunction) table);

            } else if (table instanceof TableQueryLateral) {
                analyzeQueryLateral(owner, (TableQueryLateral) table);

            } else if (table instanceof TableInDatabase) {
                analyzeInDatabase(owner, (TableInDatabase) table);

            } else if (table instanceof WithTableReference) {
                analyzeWithTableReference(owner, (WithTableReference) table);

            } else {
                System.out.println(table);
                throw new RuntimeException("サポートしてません。");
            }

        } else if (table instanceof TableJoined) {
            analyzeJoined(owner, (TableJoined) table);

        } else if (table instanceof TableNested) {
            analyzeNested(owner, (TableNested) table);

        } else {
            System.out.println(table);
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeFunction(T owner, TableFunction tableRef) {
        System.out.println(TableFunction.class + ":" + tableRef);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeQueryLateral(T owner, TableQueryLateral tableRef) {
        System.out.println(TableQueryLateral.class + ":" + tableRef);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeInDatabase(T owner, TableInDatabase tableRef) {
        System.out.println(TableInDatabase.class + ":" + tableRef);
        owner.getJoinTableNames().add(tableRef.getName());
    }

    private <T extends Query> void analyzeWithTableReference(T owner, WithTableReference tableRef) {
        System.out.println(WithTableReference.class + ":" + tableRef);
        QueryAnalyzer analyzer = SqlAnalyzerManager.getInstance().getQueryAnalyzer();

        Query subquery = new Query(owner);
        analyzer.analyze(subquery, tableRef.getWithTableSpecification().getWithTableQueryExpr());
        owner.getSubQueryList().add(subquery);
    }

    private <T extends Query> void analyzeJoined(T owner, TableJoined tableRef) {
        System.out.println(TableJoined.class + ":" + tableRef);
        analyze(owner, tableRef.getTableRefLeft());
        analyze(owner, tableRef.getTableRefRight());
    }

    private <T extends Query> void analyzeNested(T owner, TableNested table) {
        System.out.println(TableNested.class + ":" + table);
        owner.getJoinTableNames().add(table.getName());
    }

}
