package com.example.demo.mapper;

import com.example.demo.entity.Area;
import com.example.demo.entity.AreaQueryVo;
import com.example.demo.entity.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 13693
* @description 针对表【area】的数据库操作Mapper
* @createDate 2025-09-04 22:49:19
* @Entity com.example.demo.entity.Area
*/
public interface AreaMapper {
    List<Area> getAreaList();
    List<Area> findArea(@Param("areaQueryVo") AreaQueryVo areaQueryVo);
    int addArea(Area area);
    int updateArea(Area area);
    int deleteAreaById(@Param("id")Integer id);
}




