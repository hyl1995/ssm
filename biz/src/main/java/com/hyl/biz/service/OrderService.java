package com.hyl.biz.service;

import com.hyl.biz.model.Order;
import com.hyl.biz.model.query.OrderQuery;

import java.util.List;

public interface OrderService {
    int add(Order order);

    int update(Order order);

    void delete(String id);

    List<Order> selectByList(OrderQuery query);

    Order selectBySingle(OrderQuery query);
}
