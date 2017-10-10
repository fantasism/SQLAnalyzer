/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.core;

import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.fantasism.eclipse.plugin.sqlanalyzer.model.Query;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class SearchConditionAnalyzer {

    public <T extends Query> void analyze(T owner, QuerySearchCondition cond) {

        if (cond instanceof SearchConditionCombined) {
            analyzeCombined(owner, (SearchConditionCombined) cond);

        } else if (cond instanceof SearchConditionDefault) {
            analyzeDefault(owner, (SearchConditionDefault) cond);

        } else if (cond instanceof SearchConditionNested) {
            analyzeNested(owner, (SearchConditionNested) cond);

        } else if (cond instanceof Predicate) {
            PredicateAnalyzer analyzer = SqlAnalyzerManager.getInstance().getPredicateAnalyzer();
            analyzer.analyze(owner, (Predicate) cond);

        } else {
            System.out.println(cond);
            throw new RuntimeException("サポートしてません。");
        }

    }

    private <T extends Query> void analyzeCombined(T owner, SearchConditionCombined searchCond) {
        System.out.println(SearchConditionCombined.class + ":[BEGIN]" + searchCond);
        analyze(owner, searchCond.getLeftCondition());
        analyze(owner, searchCond.getRightCondition());
        System.out.println(SearchConditionCombined.class + ":[END  ]" + searchCond);
    }

    private <T extends Query> void analyzeDefault(T owner, SearchConditionDefault searchCond) {
        System.out.println(SearchConditionDefault.class + ":" + searchCond);
        throw new RuntimeException("サポートしてません。");
    }

    private <T extends Query> void analyzeNested(T owner, SearchConditionNested searchCond) {
        System.out.println(SearchConditionNested.class + ":[BEGIN]" + searchCond);
        analyze(owner, searchCond.getNestedCondition());
        System.out.println(SearchConditionNested.class + ":[END  ]" + searchCond);
    }

}
