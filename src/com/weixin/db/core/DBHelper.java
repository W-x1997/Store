package com.weixin.db.core;



import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {


    static Properties info=new Properties();

    static {
        InputStream in=DBHelper.class.getClassLoader().getResourceAsStream("config.properties");

        try {
            info.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


//    private static  String url="jdbc:mysql://localhost:3306/store";
//    private static String user="root";
//    private static  String password="jsjywx123";


    static Connection getConnection() throws SQLException,ClassNotFoundException {



        Class.forName(info.getProperty("driver"));




        Connection conn=DriverManager.getConnection(info.getProperty("url"), info.getProperty("user"),info.getProperty("password"));


        return conn;
    }
}
