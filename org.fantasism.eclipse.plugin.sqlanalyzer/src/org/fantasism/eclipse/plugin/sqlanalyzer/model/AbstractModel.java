/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.sqlanalyzer.model;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class AbstractModel<T extends AbstractModel<?>> {

    /** 所有者 */
    private AbstractModel<?> owner;

    /** 親クエリ */
    private Query<?> ownerQuery;

    public AbstractModel(T owner) {
        this.owner = owner;
        if (owner != null) {
            this.ownerQuery = owner.getOwnerQuery();
        } else {
            // 処理なし
        }
    }

    /**
     * 所有者を取得します。
     * @return 所有者
     */
    public AbstractModel<?> getOwner() {
        return owner;
    }

    /**
     * 所有者を設定します。
     * @param owner 所有者
     */
    public void setOwner(AbstractModel<?> owner) {
        this.owner = owner;
    }

    /**
     * 親クエリを取得します。
     * @return 親クエリ
     */
    public Query<?> getOwnerQuery() {
        return ownerQuery;
    }

    /**
     * 親クエリを設定します。
     * @param ownerQuery 親クエリ
     */
    public void setOwnerQuery(Query<?> ownerQuery) {
        this.ownerQuery = ownerQuery;
    }

}
