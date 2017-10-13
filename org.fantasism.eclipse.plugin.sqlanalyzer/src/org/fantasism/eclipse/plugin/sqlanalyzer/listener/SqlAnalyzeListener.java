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
public interface SqlAnalyzeListener {

    String processSql(String queryName, String sql);

    void beginAnalyzeing(String queryName, String sql);

    void endAnalyzeing(String queryName, String sql, Query query);

    void fineQuery(String queryName, Query query);

}
