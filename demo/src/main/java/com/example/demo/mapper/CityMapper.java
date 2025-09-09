package com.example.demo.mapper;

import com.example.demo.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 13693
* @description 针对表【city】的数据库操作Mapper
* @createDate 2025-09-04 22:51:08
* @Entity com.example.demo.entity.City
*/
public interface CityMapper {
    List<City> getCityList();
    List<City> selectByAreaId(@Param("areaId")Integer areaId);
    List<Integer> selectAreaIdListByName(@Param("name")String name);
    int addCity(City city);
    int updateCityById(@Param("city")City city);
    int updateCityByIdForAreaId(City city);
    int updateCityByName(@Param("oldName")String oldName, @Param("name")String name);
    int deleteCityById(@Param("id")Integer id);
}




