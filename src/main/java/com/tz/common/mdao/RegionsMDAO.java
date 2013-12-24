package com.tz.common.mdao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tz.common.model.Region;
import com.tz.common.utils.MongodbUtil;
import com.tz.log.config.SpringMongoTempalte;

/**
 * @author TZ
 * 
 */
@Repository
@Transactional
public class RegionsMDAO {

    @Autowired
    private CentersMDAO centersDAO;

    @Autowired
    private SpringMongoTempalte springMongo;

    private MongoOperations dao;

    public Region getByCode(String uipCenterCode, String code){
        dao = (MongoOperations)springMongo.mongoTemplate();
        int uipCenterId = centersDAO.getByCode(uipCenterCode).getId();
        return dao.findOne(new Query(Criteria.where("uip_center_id").is(uipCenterId).and("code").is(code)),
                Region.class, "uip_regions");
    }

    public List<Region> getByCode2(String uipCenterCode){
        dao = (MongoOperations)springMongo.mongoTemplate();
        int uipCenterId = centersDAO.getByCode(uipCenterCode).getId();
        return dao.find(new Query(Criteria.where("uip_center_id").is(uipCenterId)).with(new Sort(Sort.Direction.DESC, "_id")), Region.class, "uip_regions");
    }

    public Region getById(int uipCenterId, int id){
        dao = (MongoOperations)springMongo.mongoTemplate();
        return dao.findOne(new Query(Criteria.where("uip_center_id").is(uipCenterId).and("_id").is(id)), Region.class,
                "uip_regions");
    }

    public List<Region> searchRegions(String name){
        dao = (MongoOperations)springMongo.mongoTemplate();
        return dao.find(new Query(Criteria.where("name").regex(name + "*")).with(new Sort(Sort.Direction.DESC, "_id")), Region.class, "uip_regions");
    }

    public List<Region> getAllRegions(){
        dao = (MongoOperations)springMongo.mongoTemplate();
        Query q = new Query().with(new Sort(Sort.Direction.DESC, "_id"));
        return dao.find(q, Region.class, "uip_regions"); //dao.findAll(Region.class, "uip_regions");
    }

    public Region save(Region region){
        dao = (MongoOperations)springMongo.mongoTemplate();
        region.setId(MongodbUtil.getNextId(dao, "uip_regions"));
        dao.save(region, "uip_regions");
        return region;
    }

    public Region update(Region region){
        dao = (MongoOperations)springMongo.mongoTemplate();
        dao.updateFirst(new Query(Criteria.where("_id").is(region.getId())), MongodbUtil.setUpdate(region),
                "uip_regions");
        return region;
    }

    public void delete(int uipCenterId, int id){
        dao = (MongoOperations)springMongo.mongoTemplate();
        dao.remove(new Query(Criteria.where("_id").is(id)), "uip_regions");
    }

}
