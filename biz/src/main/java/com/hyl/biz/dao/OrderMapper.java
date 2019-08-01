package com.hyl.biz.dao;

import cn.hutool.db.PageResult;
import com.hyl.biz.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into `order` ( id,nick_name,pass,name,phone ) values (#{id},#{nickName},#{pass},#{name},#{phone})")
    int add(@Param("Order") Order order);

    @Update("update `order` set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id}")
    int update(@Param("Order") Order order);

    @Delete("delete from `order` where id= #{id}")
    int delete(@Param("id") Long id);

    @Select("<script> select * from `order` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    PageResult<Order> selectByPage(@Param("filter") String filter);

    @Select("<script> select * from `order` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    List<Order> selectByList(@Param("filter") String filter);

    @Select("<script> select * from `order` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    Order selectBySingle(@Param("filter") String filter);
}