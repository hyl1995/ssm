package com.hyl.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyl.biz.dao.HouseImgMapper;
import com.hyl.biz.model.HouseImg;
import com.hyl.biz.model.query.HouseImgQuery;
import com.hyl.biz.service.HouseImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseImgServiceImpl implements HouseImgService {
    @Autowired
    private HouseImgMapper houseImgMapper;

    public int add(HouseImg houseImg) {
        return houseImgMapper.add(houseImg);
    }

    public int update(HouseImg houseImg) {
        return houseImgMapper.update(houseImg);
    }

    @Override
    public void delete(Long id) {
        houseImgMapper.delete(id);
    }

    @Override
    public List<HouseImg> selectByList(HouseImgQuery query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return houseImgMapper.selectByList(query.getFilter());
    }

    @Override
    public HouseImg selectBySingle(HouseImgQuery query) {
        return houseImgMapper.selectBySingle(query.getFilter());
    }
}
