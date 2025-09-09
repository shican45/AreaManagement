package com.example.demo.service.impl;

import com.example.demo.entity.Address;
import com.example.demo.entity.Area;
import com.example.demo.entity.AreaQueryVo;
import com.example.demo.entity.City;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.AreaService;
import com.example.demo.mapper.AreaMapper;
import com.example.demo.util.IntIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
* @author 13693
* @description 针对表【area】的数据库操作Service实现
* @createDate 2025-09-04 22:49:19
*/
@Service
public class AreaServiceImpl implements AreaService{

    @Resource
    private AreaMapper areaMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private CityMapper cityMapper;

    public List<Area> getAllArea() {
        return areaMapper.getAreaList();
    }

    public List<Area> findArea(AreaQueryVo areaQueryVo) {
        if(areaQueryVo.getStartTime() != null && !areaQueryVo.getStartTime().isEmpty()){
            StringBuilder startTimeBuilder = new StringBuilder(areaQueryVo.getStartTime());
            String startTime = startTimeBuilder.replace(10, 11, " ").substring(0, 19);
            areaQueryVo.setStartTime(addEightHours(startTime));
        }

        if(areaQueryVo.getEndTime() != null && !areaQueryVo.getEndTime().isEmpty()) {
            StringBuilder endTimeBuilder = new StringBuilder(areaQueryVo.getEndTime());
            String endTime = endTimeBuilder.replace(10, 11, " ").substring(0, 19);
            areaQueryVo.setEndTime(addEightHours(endTime));
        }

        return areaMapper.findArea(areaQueryVo);
    }

    @Transactional
    public boolean addArea(Area area) throws ParseException {
        boolean flag = true;
        Date date = new Date();

        IntIdGenerator.reset(3);
        int areaId = IntIdGenerator.nextId();
        area.setId(areaId);
        area.setCreateTime(date);
        flag = areaMapper.addArea(area) == 1;

        List<City> cityList = area.getCityList();
        for(City city : cityList){
            city.setModifedTime(date);
            city.setAreaId(areaId);
            flag = cityMapper.updateCityByIdForAreaId(city) == 1;

            List<Address> addressList = city.getAddressList();
            for(Address address : addressList){
                address.setAreaId(areaId);
                address.setCityId(city.getId());
                address.setCreateTime(date);
                flag = addressMapper.addAddress(address) == 1;
            }
        }
        return flag;
    }

    @Transactional
    public boolean updateArea(Area oldArea, Area area) throws ParseException {
        boolean flag = true;

        Date date = new Date();
        area.setModifedTime(date);
        flag = areaMapper.updateArea(area) == 1;

        List<City> oldCityList = oldArea.getCityList();
        List<City> cityList = area.getCityList();

        //先删除再添加
        for(City city : oldCityList){
            city.setModifedTime(date);
            city.setAreaId(null);
            flag = cityMapper.updateCityByIdForAreaId(city) == 1;

            List<Address> addressList = city.getAddressList();
            for(Address address : addressList){
                flag = addressMapper.deleteAddressById(address.getId()) == 1;
            }
        }
        System.out.println("area.getId():" + area.getId());
        for(City city : cityList){
            city.setModifedTime(date);
            city.setAreaId(area.getId());
            flag = cityMapper.updateCityByIdForAreaId(city) == 1;

            List<Address> addressList = city.getAddressList();
            for(Address address : addressList){
                address.setAreaId(area.getId());
                address.setCityId(city.getId());
                address.setCreateTime(date);
                flag = addressMapper.addAddress(address) == 1;
            }
        }

        return flag;
    }

    @Transactional
    public boolean deleteArea(Area area) throws ParseException {
        boolean flag = true;
        flag = areaMapper.deleteAreaById(area.getId()) == 1;

        Date date = new Date();
        List<City> cityList = area.getCityList();
        for(City city : cityList){
            city.setModifedTime(date);
            city.setAreaId(null);
            flag = cityMapper.updateCityByIdForAreaId(city) == 1;

            List<Address> addressList = city.getAddressList();
            for(Address address : addressList){
                flag = addressMapper.deleteAddressById(address.getId()) == 1;
            }
        }
        return flag;
    }

    public String addEightHours(String timeStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(timeStr, formatter);
        dateTime = dateTime.plusHours(8);
        return dateTime.format(formatter);
    }
}




