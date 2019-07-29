package com.weixin.store.dao;

import com.weixin.store.domain.Customer;
import com.weixin.store.domain.Orders;

import java.util.List;

public interface OrderDao {
    Orders findByPK(String pk) throws ClassNotFoundException;

    List<Orders> findAll() throws ClassNotFoundException;

    void create(Orders orders);
    void modify(Orders orders);
    void remove(String pk);
}
