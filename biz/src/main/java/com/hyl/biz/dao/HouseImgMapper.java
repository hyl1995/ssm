package com.hyl.biz.dao;

import cn.hutool.db.PageResult;
import com.hyl.biz.model.HouseImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HouseImgMapper {
    @Insert("insert into `house_img` (id,nick_name,pass,name,phone) values (#{id},#{nickName},#{pass},#{name},#{phone})")
    int add(@Param("HouseImg") HouseImg houseImg);

    @Update("update `house_img` set nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id}")
    int update(@Param("HouseImg") HouseImg houseImg);

    @Delete("delete from `house_img` where id= #{id}")
    int delete(@Param("id") Long id);

    @Select("<script> select * from `house_img` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    PageResult<HouseImg> selectByPage(@Param("filter") String filter);

    @Select("<script> select * from `house_img` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    List<HouseImg> selectByList(@Param("filter") String filter);

    @Select("<script> select * from `house_img` <where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where> </script>")
    HouseImg selectBySingle(@Param("filter") String filter);
}