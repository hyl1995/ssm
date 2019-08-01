package com.hyl.biz.service;

import com.hyl.biz.model.User;
import com.hyl.biz.model.query.UserQuery;

import java.util.List;

public interface UserService {
    boolean add(User user);

    boolean update(User user);

    boolean delete(Long id);

    List<User> selectByList(UserQuery query);

    User selectBySingle(UserQuery query);
}
