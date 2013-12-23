package com.tz.common.mdao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tz.common.model.Center;
import com.tz.log.config.SpringMongoTempalte;
import com.tz.log.service.LogService;

/**
 * @author TZ
 * 
 */
@Repository
@Transactional
public class CentersMDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
    @Autowired
    private LogService logService;
    
	@Autowired
	private SpringMongoTempalte springMongo;
	private MongoOperations dao;
    
	public Center getByCode(String code) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.findOne(new Query(Criteria.where("code").in(code)), Center.class, "uip_centers");
	}

	public Center getById(int id) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.findOne(new Query(Criteria.where("id").in(id)), Center.class, "uip_centers");
	}

	public List<Center> searchCenters(String name) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.find(new Query(Criteria.where("name").regex(name + "*")), Center.class, "uip_centers");
	}

	public List<Center> getAllCenters() {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.findAll(Center.class, "uip_centers");
	}

	public int save(Center center) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		dao.save(center, "uip_centers");
		return 1;
	}

	public Center update(Center center) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		dao.updateFirst(new Query(Criteria.where("id").is(center.getId())), Update.update("address", "New Bangalore"), "uip_centers");
		return center;
	}

	public void delete(int id) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		dao.remove(new Query(Criteria.where("id").is(id)), "uip_centers");
	}

}
