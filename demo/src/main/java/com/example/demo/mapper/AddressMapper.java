package com.example.demo.mapper;

import com.example.demo.entity.Address;
import com.example.demo.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 13693
* @description 针对表【address】的数据库操作Mapper
* @createDate 2025-09-04 21:27:40
* @Entity com.example.demo.entity.Address
*/
public interface AddressMapper {
    List<Address> selectByCityId(@Param("cityId")Integer cityId);
    int addAddress(Address address);
    int deleteAddressById(@Param("id")Integer id);
}




