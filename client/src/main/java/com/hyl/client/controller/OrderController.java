package com.hyl.client.controller;

import com.hyl.biz.model.Order;
import com.hyl.biz.model.query.OrderQuery;
import com.hyl.biz.service.OrderService;
import com.hyl.core.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public Result add(Order order) {
        Result result = new Result(false);
        if (orderService.add(order)) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Result list(OrderQuery query) {
        Result result = new Result(true);
        List<Order> orders = orderService.selectByList(query);
        result.setData(orders);
        return result;
    }

    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public Result update(Order order) {
        Result result = new Result(false);
        if (orderService.update(order)) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        Result result = new Result(false);
        if (orderService.delete(id)) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "ZtAdda", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Object ZtAdda(Order order) {
        orderService.update(order);
        ModelAndView map = new ModelAndView();
        map.addObject("jg", "修改成功");
        map.setViewName("jg");
        return map;
    }
}
