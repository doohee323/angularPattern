package com.tz.common.service;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tz.common.dao.CentersDAO;
import com.tz.common.model.Center;
import com.tz.log.service.LogService;

@Service
public class CenterService {

    private static final Logger logger = LoggerFactory.getLogger(CenterService.class);

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    @Autowired
    private CentersDAO centersDAO;
    
    @Autowired
    private LogService logService;
    
	public Center getByCode(String code) {
		logService.log(this, "debug", "getByCode");
		return centersDAO.getByCode(code);
	}

	public Center getById(int id) {
		return centersDAO.getById(id);
	}

	public List<Center> searchCenters(String name) {
		return centersDAO.searchCenters(name);
	}

	public List<Center> getAllCenters() {
		return centersDAO.getAllCenters();
	}

	public int save(Center center) {
		return centersDAO.save(center);
	}

	public Center update(Center center) {
		return centersDAO.update(center);
	}

	public void delete(int id) {
		centersDAO.delete(id);
	}


}