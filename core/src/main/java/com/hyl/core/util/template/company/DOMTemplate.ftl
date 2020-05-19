package com.alib.tunnel.database.dataobject;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
public class ${entity} {

    private Long id;
    <#list data as column>

    /**
    * ${column.comment}
    */
    private ${column.columnType} ${column.columnName};
    </#list>

}
