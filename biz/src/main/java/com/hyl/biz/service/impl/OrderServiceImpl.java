package com.hyl.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyl.biz.dao.OrderMapper;
import com.hyl.biz.model.Order;
import com.hyl.biz.model.query.OrderQuery;
import com.hyl.biz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public boolean add(Order order) {
        if (orderMapper.add(order) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Order order) {
        if (orderMapper.update(order) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (orderMapper.delete(id) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Order> selectByList(OrderQuery query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return orderMapper.selectByList(query.getFilter());
    }

    @Override
    public Order selectBySingle(OrderQuery query) {
        return orderMapper.selectBySingle(query.getFilter());
    }
}
