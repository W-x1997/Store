package com.weixin.store.dao.Imp;

import com.weixin.store.dao.OrderDao;
import com.weixin.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImpTest {

    OrderDao dao;
    @BeforeEach
    void setUp() {
        dao=new OrderDaoImp();
    }

    @AfterEach
    void tearDown() {
        dao=null;
    }

    @Test
    void findByPK() throws ClassNotFoundException {
        Orders orders=dao.findByPK("1");

        assertNotNull(orders);
        assertEquals(orders.getOrderDate().getTime(),19970929);
        assertEquals(orders.getStatus(),1);
        assertEquals(orders.getTotal(),30000);

    }

    @Test
    void findAll() throws ClassNotFoundException {
        List <Orders> list=dao.findAll();
        assertNotNull(list);
        assertEquals(list.size(),1);

    }

    @Test
    void create() throws ClassNotFoundException {
        Orders orders=new Orders();
        orders.setTotal(1000);
        orders.setStatus(1);
        orders.setId("2");
        orders.setOrderDate(new Date(1000000L));
        dao.create(orders);

        Orders orders1=dao.findByPK("2");
        assertNotNull(orders1);
        assertEquals(orders.getTotal(),orders1.getTotal());
        assertEquals(orders.getStatus(),orders1.getStatus());
        assertEquals(orders.getOrderDate(),orders1.getOrderDate());


    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
    }
}