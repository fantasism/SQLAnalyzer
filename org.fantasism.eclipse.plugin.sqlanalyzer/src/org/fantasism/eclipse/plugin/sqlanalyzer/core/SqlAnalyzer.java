/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManagerProvider;
import org.eclipse.emf.common.util.EList;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SqlAnalyzer {

    public void analyze() {
        SQLQueryParserManager manager = SQLQueryParserManagerProvider.getInstance().getParserManager(null, null);
        try {
            StringBuilder sb = new StringBuilder();
            sb
            .append("WITH TEST as (    SELECT a.* FROM T_TEST)")
            .append("    SELECT a.* ")
            .append("         , b.NAME as NAME1 ")
            .append("         , 'aa' as NAME2 ")
            .append("      FROM TABLE1 a ")
            .append("INNER JOIN TABLE2 b ")
            .append("        ON a.ID = b.ID ")
            .append("INNER JOIN TEST c ")
            .append("        ON a.ID = c.ID ")
            .append("     WHERE a.GROUP    = 'bbb' ")
            .append("       AND a.CATEGORY = 'ccc' ")
            .append("       AND (   a.TYPE in ('01', '02') ")
            .append("            OR ")
            .append("               (    b.TYPE in ('02', '03') ")
            .append("                AND ")
            .append("                    a.name in (select aa.name from TABLE3 aa where aa.NAME = a.NAME)")
            .append("               )")
            .append("           )")
            .append(";");

            SQLQueryParseResult result = manager.parseQuery(sb.toString());

            Query<?> query = SqlAnalyzerManager.getInstance().getQueryStatementAnalyzer().analyze(null, result.getQueryStatement());

            query.getAlias();

        } catch (SQLParserException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (SQLParserInternalException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SqlAnalyzer analyzer = new SqlAnalyzer();
        analyzer.analyze();
    }

}
