package com.hyl.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hyl.biz.dao.AdminMapper;
import com.hyl.biz.model.Admin;
import com.hyl.biz.model.query.AdminQuery;
import com.hyl.biz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public boolean add(Admin admin) {
        if (adminMapper.add(admin) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Admin admin) {
        if (adminMapper.update(admin) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        if (adminMapper.delete(id) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Admin> selectByList(AdminQuery query) {
        //分页查询
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return adminMapper.selectByList(query.getFilter());
    }

    @Override
    public Admin selectBySingle(AdminQuery query) {
        return adminMapper.selectBySingle(query.getFilter());
    }
}
