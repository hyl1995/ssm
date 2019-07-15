package com.hyl.biz.service.impl;

import com.hyl.biz.dao.HouseImagesMapper;
import com.hyl.biz.service.HouseImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseImagesServiceImpl implements HouseImagesService {
    @Autowired
    private HouseImagesMapper houseImagesMapper;
}
