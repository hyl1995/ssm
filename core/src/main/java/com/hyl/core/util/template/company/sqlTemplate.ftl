<?xml version="1.0" encoding="UTF-8"?>
<table xmlns="http://schema.tqlab.com/mybatis"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://schema.tqlab.com/mybatis http://schema.tqlab.com/mybatis/tqlab-mybatis-plugin.xsd"
       name="${datebaseName}">

    <!--按条件统计记录行数-->
    <operation id="count" many="false" resultType="java.lang.Integer">
        <params>
            <#noparse>
            <param>#{filter,jdbcType=VARCHAR}</param>
            </#noparse>
        </params>
        <sql>
            <![CDATA[
                <script>
                    select count(*) from   ${datebaseName}
                    <#noparse>
                    <if test = " filter !=null">
                    where 1=1 ${filter}
                    </if>
                </script>
           ]]>
            </#noparse>
        </sql>
    </operation>

    <!--按条件查询分页-->
    <operation id="selectByPage" many="true">
        <params>
            <#noparse>
            <param>#{filter,jdbcType=VARCHAR}</param>
            </#noparse>
        </params>
        <sql>
            <![CDATA[
            <script>
            SELECT * FROM ${datebaseName}
            <#noparse>
                <if test="filter!=null" >
                 <where> 1=1 ${filter} </where>
               </if>
                  limit #{start,jdbcType=INTEGER}, #{size,jdbcType=INTEGER}
               </script>
            ]]>
            </#noparse>
        </sql>
    </operation>

    <!--按条件查询list-->
    <operation id="selectByList" many="true">
        <params>
            <#noparse>
                <param>#{filter,jdbcType=VARCHAR}</param>
            </#noparse>
        </params>
        <sql>
            <![CDATA[
            <script>
                SELECT * FROM ${datebaseName}
                <#noparse>
                <if test="filter!=null" >
                <where> 1=1 ${filter} </where>
                    </if>
            </script>
            ]]>
            </#noparse>
        </sql>
    </operation>

    <!--按条件查询对象-->
    <operation id="selectBySingle" many="false">
        <params>
            <#noparse>
                <param>#{filter,jdbcType=VARCHAR}</param>
            </#noparse>
        </params>
        <sql>
            <![CDATA[
            <script>
                SELECT * FROM ${datebaseName}
                <#noparse>
                <if test="filter!=null" >
                <where> 1=1 ${filter} </where>
                    </if>
            </script>
            ]]>
            </#noparse>
        </sql>
    </operation>
</table>