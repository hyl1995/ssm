package com.hyl.biz.dao;

import com.hyl.biz.model.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Insert(" insert into admin ( id,nick_name,pass,name,phone ) values (#{id},#{nickName},#{pass},#{name},#{phone}) ")
    public int add(Admin admin);

    @Update("update admin set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id} ")
    public int update(Admin admin);

    @Select("select * from admin where id= #{id} ")
    public Admin get(int id);

    @Delete(" delete from admin where id= #{id} ")
    public void delete(int id);

    @Select(" select * from admin ")
    public List<Admin> list();
}
