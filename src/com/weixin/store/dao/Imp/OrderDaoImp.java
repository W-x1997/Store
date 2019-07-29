package com.weixin.store.dao.Imp;

import com.weixin.db.core.JdbcTemplate;
import com.weixin.db.core.PreparedStatementCreator;
import com.weixin.db.core.RowCallbackHandler;
import com.weixin.store.dao.OrderDao;
import com.weixin.store.domain.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImp implements OrderDao {

    JdbcTemplate template=new JdbcTemplate();

    @Override
    public Orders findByPK(String pk) throws ClassNotFoundException {
        String sql="SELECT * FROM orders where id=?;";
        List <Orders> list=new ArrayList<>();

        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(1,pk);

                return statement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Orders order=new Orders();
                order.setId(resultSet.getString("id"));
                order.setOrderDate(new Date(resultSet.getLong("order_date")));
                order.setStatus(resultSet.getInt("status"));
                order.setTotal(resultSet.getDouble("total"));
                list.add(order);

            }
        });
        if(list.size()==1)
            return  list.get(0);
        else
            return null;


    }

    @Override
    public List<Orders> findAll() throws ClassNotFoundException {
        String sql="SELECT * FROM orders ";
        List <Orders> list=new ArrayList<>();

        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);


                return statement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Orders order=new Orders();
                order.setId(resultSet.getString("id"));
                order.setOrderDate(new Date(resultSet.getLong("order_date")));
                order.setStatus(resultSet.getInt("status"));
                order.setTotal(resultSet.getDouble("total"));
                list.add(order);

            }
        });

        return list;


    }

    @Override
    public void create(Orders orders) {
        String sql="INSERT INTO orders (id, order_date, status, total) VALUES (?, ?, ?, ?);";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
               PreparedStatement statement=connection.prepareStatement(sql);
               statement.setString(1,orders.getId());
               statement.setLong(2,orders.getOrderDate().getTime());
               statement.setInt(3,orders.getStatus());
               statement.setDouble(4,orders.getTotal());

               return statement;
            }
        });



    }

    @Override
    public void modify(Orders orders) {
        String sql="update orders set order_date=?, status=?, total=? where id=? ";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(4,orders.getId());
                statement.setLong(1,orders.getOrderDate().getTime());
                statement.setInt(2,orders.getStatus());
                statement.setDouble(3,orders.getTotal());

                return statement;
            }
        });

    }

    @Override
    public void remove(String pk) {
        String sql="delete  from orders where id=?";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
               statement.setString(1,pk);

                return statement;
            }
        });

    }
}
