package com.hyl.biz.model;

import com.hyl.biz.enums.HouseStatus;
import com.hyl.biz.enums.LeaseType;
import lombok.Data;

@Data
public class House {
    private String id;
    /**
     * 区域
     */
    private String area;
    /**
     * 出租方式
     */
    private LeaseType leaseType;
    private Integer price;
    /**
     * 户型
     */
    private String type;
    /**
     * 朝向
     */
    private String orientation;
    /**
     * 面积
     */
    private Integer size;
    /**
     * 特色
     */
    private String feature;
    /**
     * 状态
     */
    private HouseStatus houseStatus;
}
