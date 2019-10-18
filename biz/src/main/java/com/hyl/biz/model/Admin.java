package com.hyl.biz.model;

import com.hyl.core.model.BaseModel;
import lombok.Data;

@Data
public class Admin extends BaseModel {
    private String id;
    private String nickName;
    private String pass;
    private String name;
    private String phone;
}
