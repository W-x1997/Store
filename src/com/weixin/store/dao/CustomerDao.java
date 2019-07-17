package com.weixin.store.dao;

import com.weixin.store.domain.Customer;

import java.util.List;

public interface CustomerDao {
    Customer findByPK(String pk) throws ClassNotFoundException;

    List<Customer> findAll() throws ClassNotFoundException;

    void create(Customer customer);
    void modify(Customer customer);
    void remove(String pk);
}
