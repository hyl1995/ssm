package com.hyl.biz.dao;

import cn.hutool.db.PageResult;
import com.hyl.biz.model.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Insert(" insert into admin ( id,nick_name,pass,name,phone ) values (#{id},#{nickName},#{pass},#{name},#{phone}) ")
    public int add(@Param("Admin")Admin admin);

    @Update("update admin set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id} ")
    public int update(@Param("Admin")Admin admin);

    @Select("select * from admin where id= #{id} ")
    public Admin get(@Param("id")int id);

    @Delete(" delete from admin where id= #{id} ")
    public void delete(@Param("id")String id);

    @Select(" select * from admin <where><if test=\"filter!=null && filter!=''\">${filter}</if></where> ")
    public PageResult<Admin> selectByPage(@Param("filter")String filter);

    @Select(" select * from admin <where><if test=\"filter!=null && filter!=''\">${filter}</if></where> ")
    public List<Admin> selectByList(@Param("filter")String filter);

    @Select(" select * from admin <where><if test=\"filter!=null && filter!=''\">${filter}</if></where> ")
    public Admin selectBySingle(@Param("filter")String filter);
}

