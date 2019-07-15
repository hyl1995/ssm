package com.hyl.biz.service;

import com.hyl.biz.model.House;
import com.hyl.biz.model.query.HouseQuery;

import java.util.List;

public interface HouseService {
    int add(House house);

    int update(House house);

    void delete(String id);

    List<House> selectByList(HouseQuery query);

    House selectBySingle(HouseQuery query);
}
