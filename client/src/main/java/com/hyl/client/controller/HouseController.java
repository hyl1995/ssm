package com.hyl.client.controller;

import com.hyl.biz.model.House;
import com.hyl.biz.model.query.HouseQuery;
import com.hyl.biz.service.HouseService;
import com.hyl.core.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("house/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public Result add(House house) {
        Result result = new Result(false);
        if (houseService.add(house)) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Result list(HouseQuery query) {
        Result result = new Result(true);
        List<House> houses = houseService.selectByList(query);
        result.setData(houses);
        return result;
    }

    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public Result update(House house) {
        Result result = new Result(false);
        if (houseService.update(house)) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        Result result = new Result(false);
        if (houseService.delete(id)) {
            result.setSuccess(true);
        }
        return result;
    }
}
