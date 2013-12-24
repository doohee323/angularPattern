package com.tz.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * <pre>
 * </pre>
 */
public class MongodbUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(MongodbUtil.class);
    
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

	public static Object getFieldValue(Field field, Object object) throws Exception {
		String aMethodNm = "get" + StringUtil.upperFirst(field.getName());
		Method method = ClassUtils.getMethod(object, aMethodNm, false);
		if (method != null) {
			return ClassUtils.invokeSimple(object, aMethodNm);
		} else {
			return null;
		}
	}

	public static void setFieldValue(Field field, Object object, Object value)
			throws Exception {
		field.setAccessible(true);
		if (field.toGenericString().indexOf(" static ") > -1
				|| field.toGenericString().indexOf(" final ") > -1)
			return;
		field.set(object, value);
		field.setAccessible(false);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public static <T> Update setUpdate(Object obj) {
		Class<T> type = (Class<T>) obj.getClass();
		Update update = new Update();
		T object = BeanUtils.instantiate(type);
		Field[] fields = type.getDeclaredFields();

		for (Field field : fields) {
			try {
				if (field.getName().equals("id"))
					continue;
				update.set(field.getName(), getFieldValue(field, obj));
			} catch (Exception e) {
                logger.error("메소드 없음 : " + field);
			}
		}

		return update;
	}
	
}
