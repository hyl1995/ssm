package com.hyl.biz.dao;

import cn.hutool.db.PageResult;
import com.hyl.biz.model.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Insert("insert into `admin` (nick_name,pass,name,phone) values (#{nickName},#{pass},#{name},#{phone})")
    int add(@Param("Admin")Admin admin);

    @Update("update `admin` set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id}")
    int update(@Param("Admin")Admin admin);

    @Delete("delete from `admin` where id= #{id}")
    int delete(@Param("id")Long id);

    @Select("<script> select * from `admin` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    PageResult<Admin> selectByPage(@Param("filter")String filter);

    @Select("<script> select * from `admin` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    List<Admin> selectByList(@Param("filter")String filter);

    @Select("<script> select * from `admin` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    Admin selectBySingle(@Param("filter")String filter);
}

