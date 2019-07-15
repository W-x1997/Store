package com.weixin.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    /**
     *
     * @param pscreator 创建语句对象
     * @param callbackHandler 结果集处理对象
     * @throws DataAccessException
     * @throws ClassNotFoundException
     */

    public void query(PreparedStatementCreator pscreator,RowCallbackHandler callbackHandler) throws DataAccessException,ClassNotFoundException{
        Connection conn=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {
            conn=DBHelper.getConnection();
            preparedStatement=pscreator.createPreparedStatement(conn);
            resultSet=preparedStatement.executeQuery();


            while(resultSet.next()){
                callbackHandler.processRow(resultSet);
            }


        } catch (SQLException e) {
        //    e.printStackTrace();
            throw new DataAccessException("JdbcTemplate中的Exception",e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("ClassNotFound Exception",e);
        } finally {

            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                  //  e.printStackTrace();
                    throw new DataAccessException("不能释放数据库连接",e);
                }
            }

            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    //  e.printStackTrace();
                    throw new DataAccessException("不能释放语句对象",e);
                }
            }

            if(resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //  e.printStackTrace();
                    throw new DataAccessException("不能释放结果集",e);
                }
            }

        }





    }


    public void update(PreparedStatementCreator pscreator)throws DataAccessException{

        Connection conn=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try {
            conn=DBHelper.getConnection();
            preparedStatement=pscreator.createPreparedStatement(conn);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            //    e.printStackTrace();
            throw new DataAccessException("JdbcTemplate中的Exception",e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("ClassNotFound Exception",e);
        } finally {

            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    //  e.printStackTrace();
                    throw new DataAccessException("不能释放数据库连接",e);
                }
            }

            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    //  e.printStackTrace();
                    throw new DataAccessException("不能释放语句对象",e);
                }
            }



        }
    }



}
