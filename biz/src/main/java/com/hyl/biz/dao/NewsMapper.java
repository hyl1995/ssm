package com.hyl.biz.dao;

import cn.hutool.db.PageResult;
import com.hyl.biz.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {

    @Insert(" insert into `news` ( id,nick_name,pass,name,phone ) values (#{id},#{nickName},#{pass},#{name},#{phone}) ")
    int add(@Param("News") News news);

    @Update("update `news` set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id} ")
    int update(@Param("News") News news);

    @Delete(" delete from `news` where id= #{id} ")
    void delete(@Param("id") String id);

    @Select("<script> select * from `news` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    PageResult<News> selectByPage(@Param("filter") String filter);

    @Select("<script> select * from `news` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    List<News> selectByList(@Param("filter") String filter);

    @Select("<script> select * from `news` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    News selectBySingle(@Param("filter") String filter);
}