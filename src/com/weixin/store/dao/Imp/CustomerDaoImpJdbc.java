package com.weixin.store.dao.Imp;

import com.weixin.db.core.JdbcTemplate;
import com.weixin.db.core.PreparedStatementCreator;
import com.weixin.db.core.RowCallbackHandler;
import com.weixin.store.dao.CustomerDao;
import com.weixin.store.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDaoImpJdbc implements CustomerDao {
    JdbcTemplate template=new JdbcTemplate();

    @Override
    public Customer findByPK(String pk) throws ClassNotFoundException {
        String sql="select * from customers where id=?";
        List<Customer> list=new ArrayList<Customer>();

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
                Customer customer=new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setBirthday(new Date(resultSet.getLong("birthday")));
                customer.setPassword(resultSet.getString("password"));
                customer.setPhone(resultSet.getString("phone"));

                list.add(customer);
            }
        });

        if(list.size()==1)
            return list.get(0);
        else
            return null;
    }

    @Override
    public List<Customer> findAll() throws ClassNotFoundException {
        String sql="select * from customers ";
        List<Customer> list=new ArrayList<Customer>();

        template.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);



                return statement;


            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Customer customer=new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setBirthday(new Date(resultSet.getLong("birthday")));
                customer.setPassword(resultSet.getString("password"));
                customer.setPhone(resultSet.getString("phone"));

                list.add(customer);
            }
        });

        return list;
    }

    @Override
    public void create(Customer customer) {

        String sql="insert into customers (id,name,password,address,phone,birthday) values(?,?,?,?,?,?)";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(1,customer.getId());
                statement.setString(2,customer.getName());
                statement.setString(3,customer.getPassword());
                statement.setString(4,customer.getAddress());
                statement.setString(5,customer.getPhone());
                statement.setLong(6,customer.getBirthday().getTime());

                return statement;
            }
        });

    }

    @Override
    public void modify(Customer customer) {


        String sql="update customers set name=?,password=?,address=?,phone=?,birthday=? where id=?";

        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(6,customer.getId());
                statement.setString(1,customer.getName());
                statement.setString(2,customer.getPassword());
                statement.setString(3,customer.getAddress());
                statement.setString(4,customer.getPhone());
                statement.setLong(5,customer.getBirthday().getTime());

                return statement;
            }
        });

    }

    @Override
    public void remove(String pk) {

        String sql="delete  from Customers where id=?";

        template.update(connection -> {
            PreparedStatement statement=connection.prepareStatement(sql);
             statement.setString(1,pk);


            return statement;
        });

    }
}
