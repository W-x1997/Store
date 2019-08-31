package com.weixin.store.service.Imp;

import com.weixin.store.domain.Goods;
import com.weixin.store.service.GoodsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceImpTest {

    GoodsService goodsService;


    @BeforeEach
    void setUp() {
        goodsService=new GoodsServiceImp();
    }

    @AfterEach
    void tearDown() {
        goodsService=null;
    }

    @Test
    void queryAll() throws ClassNotFoundException {
        List<Goods> list=goodsService.queryAll();
        assertEquals(list.size(),2);

        assertEquals(list.get(0).getId(),1);
        assertEquals(list.get(0).getPrice(),13350);
        assertEquals(list.get(1).getId(),2);

    }

    @Test
    void queryByStartEnd() {
    }

    @Test
    void querDetail() {
    }
}