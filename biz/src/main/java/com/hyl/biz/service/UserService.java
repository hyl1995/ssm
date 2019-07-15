package com.hyl.biz.service;

import com.hyl.biz.model.User;
import com.hyl.biz.model.query.UserQuery;

import java.util.List;

public interface UserService {
    int add(User user);

    int update(User user);

    void delete(String id);

    List<User> selectByList(UserQuery query);

    User selectBySingle(UserQuery query);
}
