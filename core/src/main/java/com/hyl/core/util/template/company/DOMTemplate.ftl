package com.alib.tunnel.database.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ${entity} {

    private Long id;
    <#list data as column>

    @ApiModelProperty(value = "${column.comment}")
    <#if column.javaType == "Date">
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.javaType} ${column.javaName};
    </#list>

}
