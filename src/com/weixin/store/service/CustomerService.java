package com.weixin.store.service;

import com.weixin.store.domain.Customer;

import javax.xml.ws.Service;

public interface CustomerService {
    /**
     *
     * @param customer
     * @return 成功返回true
     */
    boolean login(Customer customer) throws ClassNotFoundException;

    /**
     * 客户注册
     * @param customer
     * @throws ServiceException
     */

    void register(Customer customer) throws ServiceException, ClassNotFoundException;
}
