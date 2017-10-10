/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import java.io.IOException;
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
            StringBuilder selectInsert = new StringBuilder();
            selectInsert
            .append("INSERT INTO TABLE11 (GROUP, NAME1, NAME2) ")
            .append("WITH TEST as (    SELECT a.* FROM T_TEST)")
            .append("    SELECT a.GROUP ")
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
            .append("UNION ALL ")
            .append("    SELECT a.GROUP ")
            .append("         , b.NAME as NAME1 ")
            .append("         , 'aa' as NAME2 ")
            .append("      FROM TABLE4 a ")
            .append("INNER JOIN TABLE5 b ")
            .append("        ON a.ID = b.ID ")
            .append("INNER JOIN TEST c ")
            .append("        ON a.ID = c.ID ")
            .append("     WHERE a.GROUP    = 'bbb' ")
            .append("       AND a.CATEGORY = 'ccc' ")
            .append("       AND (   a.TYPE in ('01', '02') ")
            .append("            OR ")
            .append("               (    b.TYPE in ('02', '03') ")
            .append("                AND ")
            .append("                    a.name in (select aa.name from TABLE6 aa where aa.NAME = a.NAME)")
            .append("               )")
            .append("           )")
            .append("UNION ALL ")
            .append("    SELECT a.GROUP ")
            .append("         , b.NAME as NAME1 ")
            .append("         , 'aa' as NAME2 ")
            .append("      FROM TABLE7 a ")
            .append("INNER JOIN TABLE8 b ")
            .append("        ON a.ID = b.ID ")
            .append("INNER JOIN TEST c ")
            .append("        ON a.ID = c.ID ")
            .append("     WHERE a.GROUP    = 'bbb' ")
            .append("       AND a.CATEGORY = 'ccc' ")
            .append("       AND (   a.TYPE in ('01', '02') ")
            .append("            OR ")
            .append("               (    b.TYPE in ('02', '03') ")
            .append("                AND ")
            .append("                    a.name in (select aa.name from TABLE9 aa where aa.NAME = a.NAME)")
            .append("               )")
            .append("           )")
            .append("       AND EXISTS(")
            .append("           SELECT * ")
            .append("             FROM TABLE10 aa ")
            .append("            WHERE aa.GROUP = a.GROUP ")
            .append("           )")
            .append(";");

            StringBuilder delete = new StringBuilder()
            .append("DELETE FROM T_TEST1 ")
            .append("WHERE ID = (select ID from T_TEST2 where ID = 1) ");

            StringBuilder update = new StringBuilder()
            .append("UPDATE T_TEST1 ")
            .append("   SET NAME = 'aaaa' ")
            .append("     , REMARKS = (select remarks from T_TEST2 where T_TEST2.ID = T_TEST1.ID) ")
            .append("WHERE ID = (select ID from T_TEST3 where ID = 1) ");

            SQLQueryParseResult result = manager.parseQuery(update.toString());

            Query query = new Query(null);
            SqlAnalyzerManager.getInstance().getQueryStatementAnalyzer().analyze(query, result.getQueryStatement());


            SqlAnalyzerWriter writer = new SqlAnalyzerWriter("C:\\Users\\TAKAHIDE\\Documents");
            writer.write(query);
            query = query;


        } catch (SQLParserException e) {
            throw new RuntimeException(e);
        } catch (SQLParserInternalException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        SqlAnalyzer analyzer = new SqlAnalyzer();
        analyzer.analyze();
    }

}
