package com.weixin.store.service.Imp;

import com.weixin.store.dao.Imp.OrderDaoImp;
import com.weixin.store.dao.OrderDao;
import com.weixin.store.domain.Orders;
import com.weixin.store.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImpTest {

    OrderService orderService;
    //OrderDao orderD;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImp();
       // orderD = new OrderDaoImp();
    }

    @AfterEach
    void tearDown() {
        orderService = null;
        //orderD = null;
    }

    @Test
    void submitOrders() throws ClassNotFoundException {
        List< Map<String, Object> > cart = new ArrayList<Map<String, Object>>();
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("goodsid",1L);
        item1.put("quantity",2);
        cart.add(item1);

//        Map<String, Object> item2 = new HashMap<String,Object>();
//        item2.put("goodsid",1);
//        item2.put("quantity",3);
//        cart.add(item2);item2

       String id = orderService.submitOrders(cart);



       // Orders orders = orderD.findByPK(ordersid);
        assertNotNull(id);
    }
}