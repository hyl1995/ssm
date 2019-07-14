package com.hyl.biz.service.impl;

import com.hyl.biz.dao.UserMapper;
import com.hyl.biz.model.User;
import com.hyl.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> userListOne(User user){
        return userMapper.userListOne(user);
    }
    public int userAdd(User user){
        return userMapper.userAdd(user);
    }
    public int userUpdate(User user){
        return userMapper.userUpdate(user);
    }
    public void userDelete(User user){
        userMapper.userDelete(user);
    }
}
