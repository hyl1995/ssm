package com.hyl.biz.dao;

import cn.hutool.db.PageResult;
import com.hyl.biz.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert(" insert into `user` ( id,nick_name,pass,name,phone ) values (#{id},#{nickName},#{pass},#{name},#{phone}) ")
    int add(@Param("User") User user);

    @Update("update `user` set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id} ")
    int update(@Param("User") User user);

    @Delete(" delete from `user` where id= #{id} ")
    void delete(@Param("id") String id);

    @Select("<script> select * from `user` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    PageResult<User> selectByPage(@Param("filter") String filter);

    @Select("<script> select * from `user` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    List<User> selectByList(@Param("filter") String filter);

    @Select("<script> select * from `user` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    User selectBySingle(@Param("filter") String filter);
}