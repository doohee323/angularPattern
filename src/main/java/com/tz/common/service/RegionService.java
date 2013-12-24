package com.tz.common.service;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tz.common.dao.RegionsDAO;
import com.tz.common.mdao.RegionsMDAO;
import com.tz.common.model.Region;
import com.tz.common.utils.StringUtil;
import com.tz.log.service.LogService;

@Service
public class RegionService {

    private static final Logger logger = LoggerFactory.getLogger(RegionService.class);

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    @Autowired
    private RegionsDAO regionsDAO;

    @Autowired
    private RegionsMDAO regionsMDAO;

    @Autowired
    private LogService logService;

    public Region getByCode(String uipCenterCode, String code){
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            return regionsMDAO.getByCode(uipCenterCode, code);
        }else{
            return regionsDAO.getByCode(uipCenterCode, code);
        }
    }

    public List<Region> getByCode2(String uipCenterCode){
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            return regionsMDAO.getByCode2(uipCenterCode);
        }else{
            return regionsDAO.getByCode2(uipCenterCode);
        }
    }

    public Region getById(int uipCenterId, int id){
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            return regionsMDAO.getById(uipCenterId, id);
        }else{
            return regionsDAO.getById(uipCenterId, id);
        }
    }

    public List<Region> searchRegions(String name){
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            return regionsMDAO.searchRegions(name);
        }else{
            return regionsDAO.searchRegions(name);
        }
    }

    public List<Region> getAllRegions(){
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            return regionsMDAO.getAllRegions();
        }else{
            return regionsDAO.getAllRegions();
        }
    }

    public Region save(Region region){
        logService.log(this, "debug", "save");
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            return regionsMDAO.save(region);
        }else{
            return regionsDAO.save(region);
        }
    }

    public Region update(Region region){
        logService.log(this, "debug", "update");
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            return regionsMDAO.update(region);
        }else{
            return regionsDAO.update(region);
        }
    }

    public void delete(int uipCenterId, int id){
        logService.log(this, "debug", "delete");
        if(StringUtil.getText(appProperties.get("com.tz.db.type")).equals("mongodb")){
            regionsMDAO.delete(uipCenterId, id);
        }else{
            regionsDAO.delete(uipCenterId, id);
        }
    }

}