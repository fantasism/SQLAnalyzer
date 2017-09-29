/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParseResult;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManager;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserManagerProvider;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SqlSelectAnalizer {

    public void analyze(String sql) {
        SQLQueryParserManager manager = SQLQueryParserManagerProvider.getInstance().getParserManager(null, null);
        try {
            SQLQueryParseResult result = manager.parseQuery("SELECT * FROM TABLE1 a INNER JOIN TABLE2 b ON a.ID = b.ID WHERE a.GROUP = 'bbb';");

            QuerySelectStatement statement = (QuerySelectStatement)result.getQueryStatement();

        } catch (SQLParserException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (SQLParserInternalException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

}
