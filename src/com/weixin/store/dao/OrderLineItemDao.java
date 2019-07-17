package com.weixin.store.dao;

import com.weixin.store.domain.Goods;
import com.weixin.store.domain.OrderLineItem;

import java.util.List;

public interface OrderLineItemDao {
    OrderLineItem findByPK(long pk);

    List<OrderLineItem> findAll();

    void create(OrderLineItem orderLineItem);
    void modify(OrderLineItem orderLineItem);
    void remove(String pk);
}
