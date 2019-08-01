package com.hyl.biz.service;

import com.hyl.biz.model.Order;
import com.hyl.biz.model.query.OrderQuery;

import java.util.List;

public interface OrderService {
    boolean add(Order order);

    boolean update(Order order);

    boolean delete(Long id);

    List<Order> selectByList(OrderQuery query);

    Order selectBySingle(OrderQuery query);
}
