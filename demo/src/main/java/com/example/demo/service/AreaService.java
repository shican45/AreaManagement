package com.example.demo.service;

import com.example.demo.entity.Area;
import com.example.demo.entity.AreaQueryVo;
import com.example.demo.entity.City;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

/**
* @author 13693
* @description 针对表【area】的数据库操作Service
* @createDate 2025-09-04 22:49:19
*/
public interface AreaService {
    List<Area> getAllArea();
    List<Area> findArea(AreaQueryVo areaQueryVo);
    boolean addArea(Area area) throws ParseException;
    boolean updateArea(Area oldArea, Area area) throws ParseException;
    boolean deleteArea(Area area) throws ParseException;
}
