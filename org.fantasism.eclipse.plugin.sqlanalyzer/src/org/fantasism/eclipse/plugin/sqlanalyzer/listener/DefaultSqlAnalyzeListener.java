/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.listener;

import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class DefaultSqlAnalyzeListener implements SqlAnalyzeListener {

    /* (非 Javadoc)
     * @see org.fantasism.eclipse.plugin.sqlanalyzer.core.SqlAnalyzeListener#processSql(java.lang.String)
     */
    @Override
    public String processSql(String queryName, String sql) {
        return sql;
    }

    /* (非 Javadoc)
     * @see org.fantasism.eclipse.plugin.sqlanalyzer.core.SqlAnalyzeListener#beginAnalyzeing(java.lang.String)
     */
    @Override
    public void beginAnalyzeing(String queryName, String sql) {
        // 処理なし
    }

    /* (非 Javadoc)
     * @see org.fantasism.eclipse.plugin.sqlanalyzer.core.SqlAnalyzeListener#endAnalyzeing(java.lang.String)
     */
    @Override
    public void endAnalyzeing(String queryName, String sql, Query query) {
        // 処理なし
    }

    /* (非 Javadoc)
     * @see org.fantasism.eclipse.plugin.sqlanalyzer.core.SqlAnalyzeListener#fineQuery(org.fantasism.eclipse.plugin.sqlanalyzer.model.Query)
     */
    @Override
    public void fineQuery(String queryName, Query query) {
        // 処理なし
    }

}
