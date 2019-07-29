package com.weixin.store.dao.Imp;

import com.weixin.db.core.JdbcTemplate;
import com.weixin.db.core.PreparedStatementCreator;
import com.weixin.db.core.RowCallbackHandler;
import com.weixin.store.dao.GoodsDao;
import com.weixin.store.domain.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImp implements GoodsDao {

    JdbcTemplate template=new JdbcTemplate();

    @Override
    public Goods findByPK(long pk) throws ClassNotFoundException {

        String sql="select * from goods where id=?";
        List<Goods> list=new ArrayList<Goods>();

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
                  Goods goods=new Goods();
                  goods.setId(resultSet.getLong("id"));
                  goods.setBrand(resultSet.getString("brand"));
                  goods.setCard_model(resultSet.getString("card_model"));
                  goods.setCpu_brand(resultSet.getString("cpu_brand"));
                  goods.setCpu_type(resultSet.getString("cpu_type"));
                  goods.setDescprition(resultSet.getString("description"));
                  goods.setDisplaysize(resultSet.getString("displaysize"));
                  goods.setImage(resultSet.getString("image"));
                  goods.setMemory_capacity(resultSet.getString("memory_capacity"));
                  goods.setName(resultSet.getString("name"));
                  goods.setHd_capacity(resultSet.getString("hd_capacity"));
                  goods.setPrice(resultSet.getDouble("price"));

                  list.add(goods);

            }
        });

        if(list.size()==1)
            return list.get(0);
        else
            return null;
    }


    @Override
    public List<Goods> findAll() throws ClassNotFoundException {
        String sql="select * from goods ";
        List<Goods> list=new ArrayList<Goods>();

        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement statement=connection.prepareStatement(sql);

                return statement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Goods goods=new Goods();
                goods.setId(resultSet.getLong("id"));
                goods.setBrand(resultSet.getString("brand"));
                goods.setCard_model(resultSet.getString("card_model"));
                goods.setCpu_brand(resultSet.getString("cpu_brand"));
                goods.setCpu_type(resultSet.getString("cpu_type"));
                goods.setDescprition(resultSet.getString("description"));
                goods.setDisplaysize(resultSet.getString("displaysize"));
                goods.setImage(resultSet.getString("image"));
                goods.setMemory_capacity(resultSet.getString("memory_capacity"));
                goods.setName(resultSet.getString("name"));
                goods.setHd_capacity(resultSet.getString("hd_capacity"));
                goods.setPrice(resultSet.getDouble("price"));

                list.add(goods);

            }
        });

        return list;
    }

    @Override
    public List<Goods> findStartEnd(int start, int end) throws ClassNotFoundException {
        List<Goods> list=new ArrayList<Goods>();
        StringBuffer sql=new StringBuffer("select * from goods");
        sql.append(" LIMIT ").append(end-start);
        sql.append(" OFFSET ").append(start);


        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql.toString());

                return statement;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Goods goods=new Goods();
                goods.setId(resultSet.getLong("id"));
                goods.setBrand(resultSet.getString("brand"));
                goods.setCard_model(resultSet.getString("card_model"));
                goods.setCpu_brand(resultSet.getString("cpu_brand"));
                goods.setCpu_type(resultSet.getString("cpu_type"));
                goods.setDescprition(resultSet.getString("description"));
                goods.setDisplaysize(resultSet.getString("displaysize"));
                goods.setImage(resultSet.getString("image"));
                goods.setMemory_capacity(resultSet.getString("memory_capacity"));
                goods.setName(resultSet.getString("name"));
                goods.setHd_capacity(resultSet.getString("hd_capacity"));
                goods.setPrice(resultSet.getDouble("price"));

                list.add(goods);

            }

        });

        if(list.size()>0)
            return list;
        else
            return null;

    }

    @Override
    public void create(Goods goods) {

        String sql="INSERT INTO goods (id, name, price, description, brand, cpu_brand, cpu_type, memory_capacity, hd_capacity, card_model, displaysize, image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setLong(1,goods.getId());
                statement.setString(2,goods.getName());
                statement.setDouble(3,goods.getPrice());
                statement.setString(4,goods.getDescprition());
                statement.setString(5,goods.getBrand());
                statement.setString(6,goods.getCpu_brand());
                statement.setString(7,goods.getCpu_type());
                statement.setString(8,goods.getMemory_capacity());
                statement.setString(9,goods.getHd_capacity());
                statement.setString(10,goods.getCard_model());
                statement.setString(11,goods.getDisplaysize());
                statement.setString(12,goods.getImage());


                return statement;

            }
        });

    }

    @Override
    public void modify(Goods goods) {
         String sql="update goods set  name=?, price=?, description=?, brand=?, cpu_brand=?, cpu_type=?, memory_capacity=?, hd_capacity=?, card_model=?, displaysize=?, image=? where id=?";

         template.update(new PreparedStatementCreator() {
             @Override
             public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                 PreparedStatement statement=connection.prepareStatement(sql);

                 statement.setLong(12,goods.getId());
                 statement.setString(1,goods.getName());
                 statement.setDouble(2,goods.getPrice());
                 statement.setString(3,goods.getDescprition());
                 statement.setString(4,goods.getBrand());
                 statement.setString(5,goods.getCpu_brand());
                 statement.setString(6,goods.getCpu_type());
                 statement.setString(7,goods.getMemory_capacity());
                 statement.setString(8,goods.getHd_capacity());
                 statement.setString(9,goods.getCard_model());
                 statement.setString(10,goods.getDisplaysize());
                 statement.setString(11,goods.getImage());

                 return statement;
             }
         });


    }

    @Override
    public void remove(String pk) {
        String sql="delete  from goods where id=?";

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
