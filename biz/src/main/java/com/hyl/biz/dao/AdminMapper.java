package com.hyl.biz.dao;

import com.hyl.biz.model.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    public int adminAdd(Admin admin);

    public int adminUpdate(Admin admin);

    public void adminDelete(Admin admin);

    public List<Admin> adminListOne(Admin admin);
}
