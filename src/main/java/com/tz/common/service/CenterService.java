package com.tz.common.service;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tz.common.dao.CentersDAO;
import com.tz.common.mdao.CentersMDAO;
import com.tz.common.model.Center;
import com.tz.common.utils.StringUtil;
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
    private CentersMDAO centersMDAO;
    
    @Autowired
    private LogService logService;
    
	public Center getByCode(String code) {
		if (StringUtil.getText(appProperties.get("com.tz.db.type"))
				.equals("mongodb")) {
			return centersMDAO.getByCode(code);
		} else {
			return centersDAO.getByCode(code);
		}
	}

	public Center getById(int id) {
		if (StringUtil.getText(appProperties.get("com.tz.db.type"))
				.equals("mongodb")) {
			return centersMDAO.getById(id);
		} else {
			return centersDAO.getById(id);
		}
	}

	public List<Center> searchCenters(String name) {
		if (StringUtil.getText(appProperties.get("com.tz.db.type"))
				.equals("mongodb")) {
			return centersMDAO.searchCenters(name);
		} else {
			return centersDAO.searchCenters(name);
		}
	}

	public List<Center> getAllCenters() {
		if (StringUtil.getText(appProperties.get("com.tz.db.type"))
				.equals("mongodb")) {
			return centersMDAO.getAllCenters();
		} else {
			return centersDAO.getAllCenters();
		}
	}

	public Center save(Center center) {
		logService.log(this, "debug", "save");
		if (StringUtil.getText(appProperties.get("com.tz.db.type"))
				.equals("mongodb")) {
			return centersMDAO.save(center);
		} else {
			return centersDAO.save(center);
		}
	}

	public Center update(Center center) {
		logService.log(this, "debug", "update");
		if (StringUtil.getText(appProperties.get("com.tz.db.type"))
				.equals("mongodb")) {
			return centersMDAO.update(center);
		} else {
			return centersDAO.update(center);
		}
	}

	public void delete(int id) {
		logService.log(this, "debug", "delete");
		if (StringUtil.getText(appProperties.get("com.tz.db.type"))
				.equals("mongodb")) {
			centersMDAO.delete(id);
		} else {
			centersDAO.delete(id);
		}
	}


}