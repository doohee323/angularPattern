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

import com.tz.common.model.Center;
import com.tz.common.model.CenterFormValidator;
import com.tz.common.service.CenterService;

/**
 * @author TZ
 * 
 */

@Controller
public class CenterRestController {

    @Autowired
    private CenterService centerService;

    @Autowired
    private CenterFormValidator validator;

    @RequestMapping(value = "/uip_centers/{queryCode}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> uipCenter(@PathVariable("queryCode") String code){
        Map<String, Object> list = new HashMap<String, Object>();
        Center center = centerService.getByCode(code);
        list.put("uip_centers", center);
        return list;
    }
    
    @RequestMapping(value = "/uip_centers", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllCenters(){
        Map<String, Object> list = new HashMap<String, Object>();
        List<Center> centers = centerService.getAllCenters();
        list.put("uip_centers", centers);
        return list;
    }

    @RequestMapping(value = "/uip_centers", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(@RequestBody Center center){
        Map<String, Object> list = new HashMap<String, Object>();
        Center centers = centerService.save(center);
        list.put("uip_centers", centers);
        return list;
    }

    @RequestMapping(value = "/uip_centers/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> update(@PathVariable("id") int id, @RequestBody Center center){
        Map<String, Object> list = new HashMap<String, Object>();
        center = centerService.update(center);
        list.put("uip_centers", center);
        return list;
    }

    @RequestMapping(value = "/uip_centers/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") int id){
        centerService.delete(id);
    }
}
