package com.example.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @TableName city
 */
@Data
public class City extends Object {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     *
     */
    private Integer areaId;

    /**
     *
     */
    private List<Address> addressList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name) && Objects.equals(areaId, city.areaId) && Objects.equals(addressList, city.addressList) && Objects.equals(createTime, city.createTime) && Objects.equals(modifedTime, city.modifedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, areaId, addressList, createTime, modifedTime);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", areaId=" + areaId +
                ", addressList=" + addressList +
                ", createTime=" + createTime +
                ", modifedTime=" + modifedTime +
                '}';
    }
}