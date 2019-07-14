package com.hyl.biz.service;

import com.hyl.biz.model.Admin;
import com.hyl.biz.model.query.AdminQuery;

import java.util.List;

public interface AdminService {
    int add(Admin admin);

    int update(Admin admin);

    void delete(String id);

    List<Admin> selectByList(AdminQuery query);

    Admin selectBySingle(AdminQuery query);
}
