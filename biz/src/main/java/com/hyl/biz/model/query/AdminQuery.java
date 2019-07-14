package com.hyl.biz.model.query;

import com.hyl.core.model.BaseQuery;

public class AdminQuery extends BaseQuery {
    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
