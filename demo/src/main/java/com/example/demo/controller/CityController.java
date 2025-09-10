package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.entity.ResultVo;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import com.example.demo.util.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public  ResultVo getCityList(){
        return ResultVo.success(cityService.getAllCity());
    }

    @PostMapping("/add")
    public ResultVo addCity(@RequestBody City city){
        if(cityService.addCity(city)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.ADD_FAIL_ERROR);
    }

    @GetMapping("/update")
    public ResultVo updateCity(@RequestParam("city")City city) throws ParseException {
        if(cityService.updateCity(city)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.EDIT_FAIL_ERROR);
    }

    @PostMapping("/delete")
    public ResultVo deleteCity(@RequestBody City city){
        if(cityService.deleteCity(city)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.DELETE_FAIL_ERROR);
    }
}
