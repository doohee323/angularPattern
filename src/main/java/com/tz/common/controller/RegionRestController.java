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

import com.tz.common.dao.RegionsDAO;
import com.tz.common.model.Region;

/**
 * @author TZ
 * 
 */

@Controller
public class RegionRestController {

    @Autowired
    private RegionsDAO regionsDAO;

    @RequestMapping(value = "/uip_regions/{uip_center_code}/{code}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> uipRegion(@PathVariable("uip_center_code") String uip_center_code, @PathVariable("code") String code){
        Map<String, Object> list = new HashMap<String, Object>();
        Region region = regionsDAO.getByCode(uip_center_code, code);
        list.put("uip_region", region);
        return list;
    }
    
    @RequestMapping("/uip_regions")
    public @ResponseBody
    Map<String, Object> uipRegions(){
        Map<String, Object> list = new HashMap<String, Object>();
        List<Region> regions = regionsDAO.getAllRegions();
        list.put("uip_regions", regions);
        return list;
    }

    @RequestMapping(value = "/uip_regions", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(@RequestBody Region region){
        Map<String, Object> data = new HashMap<String, Object>();
        regionsDAO.save(region);
        data.put("uip_region", region);
        return data;
    }

    @RequestMapping(value = "/uip_regions/{uip_center_id}/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> update(@PathVariable("uip_center_id") int uipCenterId, @PathVariable("id") int id, @RequestBody Region region){
        Map<String, Object> list = new HashMap<String, Object>();
        region = regionsDAO.update(region);
        list.put("uip_region", region);
        return list;
    }

    @RequestMapping(value = "/uip_regions/{uip_center_id}/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("uip_center_id") int uipCenterId, @PathVariable("id") int id){
        regionsDAO.delete(uipCenterId, id);
    }
}
