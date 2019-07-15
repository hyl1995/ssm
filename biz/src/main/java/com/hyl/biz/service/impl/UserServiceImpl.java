package com.hyl.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyl.biz.dao.UserMapper;
import com.hyl.biz.model.User;
import com.hyl.biz.model.query.UserQuery;
import com.hyl.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public int add(User user) {
        return userMapper.add(user);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public void delete(String id) {
        userMapper.delete(id);
    }

    @Override
    public List<User> selectByList(UserQuery query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return userMapper.selectByList(query.getFilter());
    }

    @Override
    public User selectBySingle(UserQuery query) {
        return userMapper.selectBySingle(query.getFilter());
    }
}
