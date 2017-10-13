/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 *
 * Licensed to CallStackFinder under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. CallStackFinder licenses this file to you
 * under the Eclipse Public License, Version 1.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * https://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SqlAnalyzerWriter {

    private String filePath;
    private BufferedWriter writer;
    private Set<String> writedSet;
    private String queryName;

    public SqlAnalyzerWriter(String basePath) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDhhmmss");
        this.filePath = basePath + "\\SqlAnalyzerList_" + sdf.format(new Date()) + ".tsv";
    }

    public void write(String queryName, Query query) throws IOException {

        this.queryName = queryName;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.filePath, true), "UTF-8"));
            writedSet = new HashSet<String>();

            writeQuery(query);

        } finally {
            this.writedSet = null;
            if (writer != null) {
                writer.close();
            } else {
                // 処理なし
            }
            this.writer = null;
        }
    }

    private void writeQuery(Query query) throws IOException {
        String crud = "";
        switch (query.getQueryType()) {
        case QUERY_SELECT:
            crud = "R";
            break;

        case QUERY_SELECT_INSERT:
            crud = "C";
            break;

        case QUERY_INSERT:
            crud = "C";
            break;

        case QUERY_UPDATE:
            crud = "U";
            break;

        case QUERY_DELETE:
            crud = "D";
            break;

        default:
            throw new RuntimeException("サポートしてません。" + query.getQueryType());
        }

        for (String tableName : query.getJoinTableNames()) {
            String text = queryName + "\t" + tableName + "\t" + crud;
            if (writedSet.contains(text)) {

            } else {
                writedSet.add(text);
                writer.write(text + "\r\n");
            }
        }

        if (query.getNestedQuery() != null) {
            writeQuery(query.getNestedQuery());

        } else {

        }

        if (query.getUnionQuery() != null) {
            writeQuery(query.getUnionQuery());

        } else {

        }

        for (Query subquery : query.getSubQueryList()) {
            writeQuery(subquery);
        }
    }

    /**
     * filePathを取得します。
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }

}
