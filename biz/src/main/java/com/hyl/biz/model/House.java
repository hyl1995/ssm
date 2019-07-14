package com.hyl.biz.model;
import com.hyl.biz.enums.HouseStatus;
import com.hyl.biz.enums.LeaseType;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LeaseType getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(LeaseType leaseType) {
        this.leaseType = leaseType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public HouseStatus getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(HouseStatus houseStatus) {
        this.houseStatus = houseStatus;
    }
}
