package com.hyl.biz.dao;

import cn.hutool.db.PageResult;
import com.hyl.biz.model.House;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HouseMapper {
    @Insert("insert into `house` (id,nick_name,pass,name,phone) values (#{id},#{nickName},#{pass},#{name},#{phone})")
    int add(@Param("House") House house);

    @Update("update `house` set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id}")
    int update(@Param("House") House house);

    @Delete("delete from `house` where id= #{id}")
    void delete(@Param("id") String id);

    @Select("<script> select * from `house` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    PageResult<House> selectByPage(@Param("filter") String filter);

    @Select("<script> select * from `house` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    List<House> selectByList(@Param("filter") String filter);

    @Select("<script> select * from `house` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    House selectBySingle(@Param("filter") String filter);
}