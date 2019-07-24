package com.weixin.store.dao.Imp;

import com.weixin.store.dao.GoodsDao;
import com.weixin.store.domain.Goods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsDaoImpTest {

    GoodsDao goodsDao;

    @BeforeEach
    void setUp() {
        goodsDao=new GoodsDaoImp();
    }

    @AfterEach
    void tearDown() {
        goodsDao=null;
    }

    @Test
    void findByPK() throws ClassNotFoundException {
        Goods goods=goodsDao.findByPK(1);

        assertNotNull(goods);
        assertEquals("i5",goods.getCpu_brand());



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