package com.weixin.db.core;

public class DataAccessException extends RuntimeException {


    public  DataAccessException(String msg){
       super(msg);

    }


    public  DataAccessException(String msg,Throwable ex){
        super(msg,ex);

    }


}
