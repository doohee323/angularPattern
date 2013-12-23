package com.tz.log.service;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.tz.common.utils.StringUtil;
import com.tz.log.config.SpringMongoTempalte;
import com.tz.log.domain.Log;

@Service("LogService")
public class LogService {

	private static final Logger logger = LoggerFactory
			.getLogger(LogService.class);

	@Autowired
	@Qualifier("appProperties")
	private Properties appProperties;

	private MongoOperations dao;
	
	@Autowired
	private SpringMongoTempalte springMongo;

	public void log(Object obj, String level, String message) {
		try {
			if (!StringUtil.getText(appProperties.get("com.tz.dblog.useYn"))
					.equals("true")) {
				return;
			}

			Log log = new Log(obj, level, message);
			logger(level, log);

			dao = (MongoOperations) springMongo.mongoTemplate();
			dao.save(log);
			
			Query searchLogQuery = new Query(Criteria.where("id").is(
					log.getTime()));
			Log savedLog = dao.findOne(searchLogQuery, Log.class);
			logger.debug("2. find - savedLog : " + savedLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Log log) {
		Query searchLogQuery = new Query(Criteria.where("id").is(log.getTime()));
		dao.updateFirst(searchLogQuery, Update.update("message", "ok!!!"),
				Log.class);
		Log updatedLog = dao.findOne(
				new Query(Criteria.where("id").is(log.getTime())), Log.class);
		logger.debug("3. updatedLog : " + updatedLog);
	}

	public void delete(Log log) {
		Query searchLogQuery = new Query(Criteria.where("id").is(log.getTime()));
		dao.remove(searchLogQuery, Log.class);
		List<Log> listLog = dao.findAll(Log.class);
		logger.debug("4. Number of log = " + listLog.size());
	}

	public void logger(String level, Log log) {
		if (level.equals("debug")) {
			logger.debug(log.toString());
		} else if (level.equals("error")) {
			logger.debug(log.toString());
		}
	}
}