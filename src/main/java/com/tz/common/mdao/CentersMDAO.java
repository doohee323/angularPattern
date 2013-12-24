package com.tz.common.mdao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tz.common.model.Center;
import com.tz.common.utils.MongodbUtil;
import com.tz.log.config.SpringMongoTempalte;
import com.tz.log.service.LogService;

/**
 * @author TZ
 * 
 */
@Repository
@Transactional
public class CentersMDAO {

    private static final Logger logger = LoggerFactory.getLogger(CentersMDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LogService logService;

    @Autowired
    private SpringMongoTempalte springMongo;

    private MongoOperations dao;

    public Center getByCode(String code){
        dao = (MongoOperations)springMongo.mongoTemplate();
        return dao.findOne(new Query(Criteria.where("code").in(code)), Center.class, "uip_centers");
    }

    public Center getById(int id){
        dao = (MongoOperations)springMongo.mongoTemplate();
        return dao.findOne(new Query(Criteria.where("_id").in(id)), Center.class, "uip_centers");
    }

    public List<Center> searchCenters(String name){
        dao = (MongoOperations)springMongo.mongoTemplate();
        return dao.find(new Query(Criteria.where("name").regex(name + "*")).with(new Sort(Sort.Direction.DESC, "_id")), Center.class, "uip_centers");
    }

    public List<Center> getAllCenters(){
        dao = (MongoOperations)springMongo.mongoTemplate();
        Query q = new Query().with(new Sort(Sort.Direction.DESC, "_id"));
        return dao.find(q, Center.class, "uip_centers"); //dao.findAll(Center.class, "uip_centers");
    }

    public Center save(Center center){
        dao = (MongoOperations)springMongo.mongoTemplate();
        center.setId(MongodbUtil.getNextId(dao, "uip_centers"));
        dao.save(center, "uip_centers");
        return center;
    }

    public Center update(Center center){
        dao = (MongoOperations)springMongo.mongoTemplate();
        dao.updateFirst(new Query(Criteria.where("_id").is(center.getId())), MongodbUtil.setUpdate(center), "uip_centers");
        return center;
    }

    public void delete(int id){
        dao = (MongoOperations)springMongo.mongoTemplate();
        dao.remove(new Query(Criteria.where("_id").is(id)), "uip_centers");
    }

}
