package com.tz.log.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

public class SpringMongoTempalte {

	private String host;
	private int port;
	private String dbname;
	
    public @Bean MongoDbFactory mongoDbFactory() throws Exception{
        return new SimpleMongoDbFactory(new MongoClient(host, port), dbname);
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception{
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

}