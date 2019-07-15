package com.hyl.biz.service;

import com.hyl.biz.model.News;
import com.hyl.biz.model.query.NewsQuery;

import java.util.List;

public interface NewsService {
    int add(News news);

    int update(News news);

    void delete(String id);

    List<News> selectByList(NewsQuery query);

    News selectBySingle(NewsQuery query);
}
