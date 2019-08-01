import cn.hutool.db.PageResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ${entity + "Mapper"} {

    @Insert(" insert into `${datebaseName}` ( id,nick_name,pass,name,phone ) values (<#noparse>#{id},#{nickName},#{pass},#{name},#{phone}</#noparse>) ")
    int add(@Param("${entity}")${entity} ${entityName});

    @Update("update `${datebaseName}` set <#noparse>nick_name=#{nickName},pass=#{pass},name=#{name},phone=#{phone} where id=#{id}</#noparse> ")
    int update(@Param("${entity}")${entity} ${entityName});

    @Delete(" delete from `${datebaseName}` <#noparse>where id= #{id}</#noparse> ")
    void delete(@Param("id")Long id);

    @Select("<script> select * from `${datebaseName}` <#noparse><where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where></#noparse> </script>")
    PageResult<${entity}> selectByPage(@Param("filter")String filter);

    @Select("<script> select * from `${datebaseName}` <#noparse><where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where></#noparse> </script>")
    List<${entity}> selectByList(@Param("filter")String filter);

    @Select("<script> select * from `${datebaseName}` <#noparse><where><if test=\"filter!=null &amp;&amp; filter!=''\">${filter}</if></where></#noparse> </script>")
    ${entity} selectBySingle(@Param("filter")String filter);
}