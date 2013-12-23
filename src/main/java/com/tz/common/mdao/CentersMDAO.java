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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
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

	/**
	 * Get the next unique ID for a named sequence.
	 * @param db Mongo database to work with
	 * @param seq_name The name of your sequence (I name mine after my collections)
	 * @return The next ID
	 */
	public static int getNextId(MongoOperations db, String seq_name) {
	    String sequence_collection = "seq"; // the name of the sequence collection
	    String sequence_field = "seq"; // the name of the field which holds the sequence
	 
	    DBCollection seq = db.getCollection(sequence_collection); // get the collection (this will create it if needed)
	 
	    // this object represents your "query", its analogous to a WHERE clause in SQL
	    DBObject query = new BasicDBObject();
	    query.put("_id", seq_name); // where _id = the input sequence name
	 
	    // this object represents the "update" or the SET blah=blah in SQL
	    DBObject change = new BasicDBObject(sequence_field, 1);
	    DBObject update = new BasicDBObject("$inc", change); // the $inc here is a mongodb command for increment
	 
	    // Atomically updates the sequence field and returns the value for you
	    DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
	    
	    return Integer.parseInt(res.get(sequence_field).toString());
	}

	public Center getByCode(String code) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.findOne(new Query(Criteria.where("code").in(code)),
				Center.class, "uip_centers");
	}

	public Center getById(int id) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.findOne(new Query(Criteria.where("id").in(id)),
				Center.class, "uip_centers");
	}

	public List<Center> searchCenters(String name) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.find(new Query(Criteria.where("name").regex(name + "*")),
				Center.class, "uip_centers");
	}

	public List<Center> getAllCenters() {
		dao = (MongoOperations) springMongo.mongoTemplate();
		return dao.findAll(Center.class, "uip_centers");
	}

	public Center save(Center center) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		center.setId(getNextId(dao, "uip_centers"));
		dao.save(center, "uip_centers");
		return center;
	}

	public Center update(Center center) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		dao.updateFirst(new Query(Criteria.where("id").is(center.getId())),
				Update.update("address", "New Bangalore"), "uip_centers");
		return center;
	}

	public void delete(int id) {
		dao = (MongoOperations) springMongo.mongoTemplate();
		dao.remove(new Query(Criteria.where("id").is(id)), "uip_centers");
	}

}
