package com.weixin.store.dao.Imp;

import com.weixin.db.core.JdbcTemplate;
import com.weixin.db.core.PreparedStatementCreator;
import com.weixin.db.core.RowCallbackHandler;
import com.weixin.store.dao.OrderLineItemDao;
import com.weixin.store.domain.Goods;
import com.weixin.store.domain.OrderLineItem;
import com.weixin.store.domain.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineItemDaoImp implements OrderLineItemDao {

    JdbcTemplate template=new JdbcTemplate();


    @Override
    public OrderLineItem findByPK(long pk) throws ClassNotFoundException {

        String sql="SELECT * FROM orderlineitems where id=?";

        List<OrderLineItem> list=new ArrayList<>();

        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setLong(1,pk);


                return statement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                OrderLineItem orderLineItem=new OrderLineItem();
                orderLineItem.setId(resultSet.getLong("id"));
                orderLineItem.setQuantity(resultSet.getInt("quantity"));
                orderLineItem.setSubtotal(resultSet.getFloat("sub_total"));

                Orders orders=new Orders();
                orders.setId(resultSet.getString("orderid"));
                orderLineItem.setOrders(orders);

                Goods goods=new Goods();
                goods.setId(resultSet.getLong("goodsid"));
                orderLineItem.setGoods(goods);
                list.add(orderLineItem);

            }
        });

        if(list.size()==1)
            return list.get(0);
        else
            return null;


    }

    @Override
    public List<OrderLineItem> findAll() throws ClassNotFoundException {
        String sql="SELECT * FROM orderlineitems ";

        List<OrderLineItem> list=new ArrayList<>();

        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);


                return statement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                OrderLineItem orderLineItem=new OrderLineItem();
                orderLineItem.setId(resultSet.getLong("id"));
                orderLineItem.setQuantity(resultSet.getInt("quantity"));
                orderLineItem.setSubtotal(resultSet.getFloat("sub_total"));

                Orders orders=new Orders();
                orders.setId(resultSet.getString("orderid"));
                orderLineItem.setOrders(orders);

                Goods goods=new Goods();
                goods.setId(resultSet.getLong("goodsid"));
                orderLineItem.setGoods(goods);
                list.add(orderLineItem);

            }
        });
            return list;

    }

    @Override
    public void create(OrderLineItem orderLineItem) {
      String sql="INSERT INTO orderlineitems (id, goodsid, orderid, quantity, sub_total) VALUES (?,?,?,?,?)";

      template.update(new PreparedStatementCreator() {
          @Override
          public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
              PreparedStatement statement=connection.prepareStatement(sql);
              statement.setLong(1,orderLineItem.getId());
              statement.setLong(2,orderLineItem.getGoods().getId());
              statement.setString(3,orderLineItem.getOrders().getId());
              statement.setInt(4,orderLineItem.getQuantity());
              statement.setDouble(5,orderLineItem.getSubtotal());

              return statement;
          }
      });


    }

    @Override
    public void modify(OrderLineItem orderLineItem) {
        String sql="update orderlineitems set  goodsid=?, orderid=?, quantity=?, sub_total=?  where id=?";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setLong(5,orderLineItem.getId());
                statement.setLong(1,orderLineItem.getGoods().getId());
                statement.setString(2,orderLineItem.getOrders().getId());
                statement.setInt(3,orderLineItem.getQuantity());
                statement.setDouble(4,orderLineItem.getSubtotal());

                return statement;
            }
        });


    }

    @Override
    public void remove(String pk) {
        String sql="delete from orderlineitems where id=?";

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
