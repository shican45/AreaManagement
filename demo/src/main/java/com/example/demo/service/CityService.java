package com.example.demo.service;

import com.example.demo.entity.City;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
* @author 13693
* @description 针对表【city】的数据库操作Service
* @createDate 2025-09-04 22:51:08
*/
public interface CityService {
    List<City> getAllCity();
    boolean addCity(City city);
    boolean updateCity(City city) throws ParseException;
    boolean deleteCity(City city);
}
