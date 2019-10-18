package com.hyl.biz.model;

import com.hyl.biz.enums.OrderStatus;
import com.hyl.core.model.BaseModel;
import lombok.Data;

import java.sql.Date;

@Data
public class Order extends BaseModel {
    private String id;
    private String userId;
    private String houseId;
    private Date startTime;
    private Date endTime;
    private Integer totalMonth;
    private OrderStatus orderStatus;
}
