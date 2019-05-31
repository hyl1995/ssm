package com.hyl.biz.dao;

import com.hyl.biz.model.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Insert(" insert into Admin_ ( name ) values (#{name}) ")
    public int add(Admin admin);

    @Delete(" delete from Admin_ where id= #{id} ")
    public void delete(int id);

    @Select("select * from Admin_ where id= #{id} ")
    public Admin get(int id);

    @Update("update Admin_ set name=#{name} where id=#{id} ")
    public int update(Admin admin);

    @Select(" select * from Admin_ ")
    public List<Admin> list();
}
