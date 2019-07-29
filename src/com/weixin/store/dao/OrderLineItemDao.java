package com.weixin.store.dao;

import com.weixin.store.domain.Goods;
import com.weixin.store.domain.OrderLineItem;

import java.util.List;

public interface OrderLineItemDao {
    OrderLineItem findByPK(long pk) throws ClassNotFoundException;

    List<OrderLineItem> findAll() throws ClassNotFoundException;

    void create(OrderLineItem orderLineItem);
    void modify(OrderLineItem orderLineItem);
    void remove(String pk);
}
