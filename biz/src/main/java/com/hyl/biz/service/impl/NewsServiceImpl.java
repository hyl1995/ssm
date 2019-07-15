package com.hyl.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyl.biz.dao.NewsMapper;
import com.hyl.biz.model.News;
import com.hyl.biz.model.query.NewsQuery;
import com.hyl.biz.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    public int add(News news) {
        return newsMapper.add(news);
    }

    public int update(News news) {
        return newsMapper.update(news);
    }

    @Override
    public void delete(String id) {
        newsMapper.delete(id);
    }

    @Override
    public List<News> selectByList(NewsQuery query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return newsMapper.selectByList(query.getFilter());
    }

    @Override
    public News selectBySingle(NewsQuery query) {
        return newsMapper.selectBySingle(query.getFilter());
    }
}
