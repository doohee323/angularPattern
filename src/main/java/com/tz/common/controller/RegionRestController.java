package com.tz.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tz.common.model.Region;
import com.tz.common.service.RegionService;

/**
 * @author TZ
 * 
 */

@Controller
public class RegionRestController {

    @Autowired
    private RegionService regionService;
    
    @RequestMapping(value = "/uip_regions/{queryCenterCode}/{queryCode}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> uipRegion(@PathVariable("queryCenterCode") String uipCenterCode, @PathVariable("queryCode") String code){
        Map<String, Object> list = new HashMap<String, Object>();
        Region region = regionService.getByCode(uipCenterCode, code);
        list.put("uip_regions", region);
        return list;
    }
    
    @RequestMapping(value = "/uip_regions/{queryCenterCode}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> uipRegion2(@PathVariable("queryCenterCode") String uipCenterCode){
        Map<String, Object> list = new HashMap<String, Object>();
        List<Region> regions = regionService.getByCode2(uipCenterCode);
        list.put("uip_regions", regions);
        return list;
    }
    
    @RequestMapping(value = "/uip_regions", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllRegions(){
        Map<String, Object> list = new HashMap<String, Object>();
        List<Region> regions = regionService.getAllRegions();
        list.put("uip_regions", regions);
        return list;
    }

    @RequestMapping(value = "/uip_regions/{uip_center_id}", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(@PathVariable("uip_center_id") int uipCenterId, @RequestBody Region region){
        Map<String, Object> data = new HashMap<String, Object>();
        region = regionService.save(region);
        data.put("uip_regions", region);
        return data;
    }

    @RequestMapping(value = "/uip_regions/{uip_center_id}/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> update(@PathVariable("uip_center_id") int uipCenterId, @PathVariable("id") int id, @RequestBody Region region){
        Map<String, Object> data = new HashMap<String, Object>();
        region = regionService.update(region);
        data.put("uip_regions", region);
        return data;
    }

    @RequestMapping(value = "/uip_regions/{uip_center_id}/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("uip_center_id") int uipCenterId, @PathVariable("id") int id){
        Map<String, Object> data = new HashMap<String, Object>();
        regionService.delete(uipCenterId, id);
        data.put("id", id);
        return data;
    }
}
