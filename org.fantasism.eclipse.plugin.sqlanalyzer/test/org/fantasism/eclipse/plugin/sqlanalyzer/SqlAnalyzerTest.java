/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.fantasism.eclipse.plugin.resource.core.ResourceFinder;
import org.fantasism.eclipse.plugin.sqlanalyzer.listener.SqlAnalyzeListener;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SqlAnalyzerTest {

    public static void main(String[] args) {

        SqlAnalyzer analyzer = new SqlAnalyzer();
        final SqlAnalyzerWriter writer = new SqlAnalyzerWriter("C:\\Users\\TAKAHIDE\\");
        SqlAnalyzerContext context = new SqlAnalyzerContext(new SqlAnalyzeListener() {

            @Override
            public String processSql(String queryName, String sql) {
                return sql;
            }

            @Override
            public void fineQuery(String queryName, Query query) {
                System.out.println("[INFO ] SQL ANALYZER : FIND QUERY");
            }

            @Override
            public void endAnalyzeing(String queryName, String sql, Query query) {
                try {
                    writer.write(queryName, query);

                    System.out.println("[END  ] SQL ANALYZER : " + queryName);

                } catch (Exception e) {
                    System.out.println("[ABEND] SQL ANALYZER : " + queryName);
                }
            }

            @Override
            public void beginAnalyzeing(String queryName, String sql) {
                System.out.println("[BEGIN] SQL ANALYZER : " + queryName);
            }
        });

        File resourcesDir = new File("C:\\Users\\TAKAHIDE\\git\\SQLAnalyzer\\org.fantasism.eclipse.plugin.sqlanalyzer\\resources");

        for (File sqlFile : resourcesDir.listFiles()) {
            StringBuilder sb = new StringBuilder();

            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFile)));

                for (String temp = br.readLine(); temp != null; temp = br.readLine()) {
                    sb.append(temp);
                }

                context.setQueryName(sqlFile.getName());
                analyzer.analyze(context, sb.toString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                if (br!=null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        // TODO 自動生成された catch ブロック
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
