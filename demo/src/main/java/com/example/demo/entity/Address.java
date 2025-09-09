package com.example.demo.entity;

import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @TableName address
 */
@Data
public class Address extends Object {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private Integer cityId;

    /**
     *
     */
    private Integer areaId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date modifedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifedTime() {
        return modifedTime;
    }

    public void setModifedTime(Date modifedTime) {
        this.modifedTime = modifedTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(id, address1.id) && Objects.equals(address, address1.address) && Objects.equals(cityId, address1.cityId) && Objects.equals(areaId, address1.areaId) && Objects.equals(createTime, address1.createTime) && Objects.equals(modifedTime, address1.modifedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, cityId, areaId, createTime, modifedTime);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", createTime=" + createTime +
                ", modifedTime=" + modifedTime +
                '}';
    }
}