package com.tz.log.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tz.common.utils.DateUtil;

@Document(collection = "logs")
public class Log {

    @Id
    private String time;

    private String level;

    String className;

    String message;

    public Log(Object obj, String level, String message) {
        super();
        this.time = DateUtil.getCurrentDateString("yyyyMMddHHmmsss");
        this.level = level;
        this.className = obj.getClass().getName();
        this.message = message;
    }

    @Override
    public String toString(){
        return time + "\t" + level + "\t" + className + "\t" + message;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getLevel(){
        return level;
    }

    public void setLevel(String level){
        this.level = level;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}