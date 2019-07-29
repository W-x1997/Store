package com.weixin.store.dao.Imp;

import com.weixin.store.dao.OrderDao;
import com.weixin.store.dao.OrderLineItemDao;
import com.weixin.store.domain.OrderLineItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineItemDaoImpTest {
     OrderLineItemDao dao;


    @BeforeEach
    void setUp() {
        dao=new OrderLineItemDaoImp();
    }

    @AfterEach
    void tearDown() {
        dao=null;
    }

    @Test
    void findByPK() throws ClassNotFoundException {
        OrderLineItem item=dao.findByPK(1);

        assertNotNull(item);
        assertEquals(2,item.getQuantity());
        assertEquals(1,item.getId());
        assertEquals(3433,item.getSubtotal());
        assertEquals(1,item.getGoods().getId());
        assertEquals("1",item.getOrders().getId());


    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
    }
}