package com.johnhoo.bootmybatis.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.johnhoo.bootmybatis.pojo.Area;
import com.johnhoo.bootmybatis.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//等价于@controller + @Responsebody，表示返回的不是页面，返回的是一个JSON对象
@RestController
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    public Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> list = areaService.getAreaList();
        modelMap.put("areaList", list);
        return modelMap;
    }

    @RequestMapping(value = "/getareabyid", method = RequestMethod.GET)
    public Map<String, Object> getAreaById(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<>();
        Area area = areaService.getAreaById(areaId);
        modelMap.put("area", area);
        return modelMap;
    }

    @RequestMapping(value = "/addarea", method = RequestMethod.POST)
    public Map<String, Object> addArea(@RequestBody Area area) {    //RequestBody表示从前台接收传参
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 添加事业部信息
        modelMap.put("success", areaService.addArea(area));
        return modelMap;
    }

    @RequestMapping(value = "/modifyarea", method = RequestMethod.POST)
    private Map<String, Object> modifyArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 修改事业部信息
        modelMap.put("success", areaService.modifyArea(area));
        return modelMap;
    }

    @RequestMapping(value = "/removearea", method = RequestMethod.GET)
    private Map<String, Object> removeArea(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 删除事业部信息
        modelMap.put("success", areaService.deleteArea(areaId));
        return modelMap;
    }
}
