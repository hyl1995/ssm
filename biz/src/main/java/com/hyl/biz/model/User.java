package com.hyl.biz.model;

import com.hyl.biz.enums.SexType;
import com.hyl.core.model.BaseModel;
import lombok.Data;

import java.sql.Date;

@Data
public class User extends BaseModel {
    private String id;
    private String nickName;
    private String pass;
    private String name;
    private String phone;
    private SexType sexType;
    private Date birthday;
    private String idcard;
    private String status;
}
