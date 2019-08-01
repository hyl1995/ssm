package com.hyl.biz.service;

import com.hyl.biz.model.House;
import com.hyl.biz.model.query.HouseQuery;

import java.util.List;

public interface HouseService {
    boolean add(House house);

    boolean update(House house);

    boolean delete(Long id);

    List<House> selectByList(HouseQuery query);

    House selectBySingle(HouseQuery query);
}
