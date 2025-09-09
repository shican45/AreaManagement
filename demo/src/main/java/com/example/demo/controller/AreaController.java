package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.mapper.AreaMapper;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.AddressService;
import com.example.demo.service.AreaService;
import com.example.demo.service.CityService;
import com.example.demo.util.ErrorMsg;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

import java.text.ParseException;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Resource
    private AreaMapper areaMapper;

    @Autowired
    private CityService cityService;

    @Resource
    private CityMapper cityMapper;

    @Autowired
    private AddressService addressService;

    @Resource
    private AddressMapper addressMapper;

    @GetMapping("/list")
    public  ResultVo getAreaList(){
        return ResultVo.success(areaService.getAllArea());
    }

    @RequestMapping("/find")
    public  ResultVo findArea(@RequestBody AreaQueryVo areaQueryVo){
        return ResultVo.success(areaService.findArea(areaQueryVo));
    }

    @PostMapping("/add")
    public ResultVo addArea(@RequestBody Area area) throws ParseException {
        if(areaService.addArea(area)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.ADD_FAIL_ERROR);
    }

    @PostMapping("/update")
    public ResultVo updateArea(@RequestBody EditAreaVo editAreaVo) throws ParseException {
        if(areaService.updateArea(editAreaVo.getOldArea(), editAreaVo.getNewArea())){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.EDIT_FAIL_ERROR);
    }

    @PostMapping("/delete")
    public ResultVo deleteArea(@RequestBody Area area) throws ParseException {
        if(areaService.deleteArea(area)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.DELETE_FAIL_ERROR);
    }
}
