package com.example.demo.service.impl;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import com.example.demo.mapper.CityMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author 13693
* @description 针对表【city】的数据库操作Service实现
* @createDate 2025-09-04 22:51:08
*/
@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityMapper cityMapper;

    public List<City> getAllCity(){
        return cityMapper.getCityList();
    }

    public boolean addCity(City city){
        city.setCreateTime(new Date());
        return cityMapper.addCity(city)==1;
    }

    public boolean updateCity(City city) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTemp =new Date(System.currentTimeMillis());
        String dateStr = sdf.format(dateTemp);
        Date date = sdf.parse(dateStr);
        city.setModifedTime(date);
        return cityMapper.updateCityById(city)==1;
    }

    public boolean deleteCity(City city){
        return cityMapper.deleteCityById(city.getId())==1;
    }
}




