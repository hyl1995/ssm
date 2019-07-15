package com.hyl.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyl.biz.dao.HouseMapper;
import com.hyl.biz.model.House;
import com.hyl.biz.model.query.HouseQuery;
import com.hyl.biz.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    public int add(House house) {
        return houseMapper.add(house);
    }

    public int update(House house) {
        return houseMapper.update(house);
    }

    @Override
    public void delete(String id) {
        houseMapper.delete(id);
    }

    @Override
    public List<House> selectByList(HouseQuery query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return houseMapper.selectByList(query.getFilter());
    }

    @Override
    public House selectBySingle(HouseQuery query) {
        return houseMapper.selectBySingle(query.getFilter());
    }
}
