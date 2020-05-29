<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.alib.tunnel.database.${entity + "Mapper"}">
    <resultMap id="BaseResultMap" type="com.alib.tunnel.database.dataobject.${entity + "DO"}">
        <id column="id" jdbcType="BIGINT" property="id"/>
        <#list data as column>
        <result column="${column.columnName}" jdbcType="${column.columnType}" property="${column.javaName}"/>
        </#list>
    </resultMap>

    <sql id="allColumns">
        id,
    <#list data as column>
    <#if column_index != (data?size-1)>
        ${column.columnName},
    <#else>
        ${column.columnName}
    </#if>
    </#list>
    </sql>
</mapper>