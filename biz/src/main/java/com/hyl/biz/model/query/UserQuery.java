package com.hyl.biz.model.query;

import com.hyl.core.model.BaseQuery;
import lombok.Data;

@Data
public class UserQuery extends BaseQuery {
    private String filter;
}