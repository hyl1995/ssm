package com.hyl.biz.service;

import com.hyl.biz.model.Admin;
import com.hyl.biz.model.query.AdminQuery;

import java.util.List;

public interface AdminService {
    boolean add(Admin admin);

    boolean update(Admin admin);

    boolean delete(Long id);

    List<Admin> selectByList(AdminQuery query);

    Admin selectBySingle(AdminQuery query);
}
