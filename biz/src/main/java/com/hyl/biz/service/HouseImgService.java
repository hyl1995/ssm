package com.hyl.biz.service;

import com.hyl.biz.model.HouseImg;
import com.hyl.biz.model.query.HouseImgQuery;

import java.util.List;

public interface HouseImgService {
    int add(HouseImg houseImg);

    int update(HouseImg houseImg);

    void delete(String id);

    List<HouseImg> selectByList(HouseImgQuery query);

    HouseImg selectBySingle(HouseImgQuery query);
}
