package com.weixin.store.dao.Imp;

import com.weixin.store.dao.GoodsDao;
import com.weixin.store.domain.Goods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void findAll() throws ClassNotFoundException {
        List <Goods> list =goodsDao.findAll();
        assertEquals(list.size(),1);
    }

    @Test
    void create() throws ClassNotFoundException {
        Goods goods=new Goods();
        goods.setPrice(2000);
        goods.setHd_capacity("2G");
        goods.setName("Mac air 2015");
        goods.setMemory_capacity("100TB");
        goods.setImage("**");
        goods.setDisplaysize("RETIED");
        goods.setDescprition("WW");
        goods.setCpu_type("1080T");
        goods.setCpu_brand("HP");
        goods.setId(2);
        goods.setBrand("苹果");
        goods.setCard_model("980GTX");

        goodsDao.create(goods);

        Goods goods1=goodsDao.findByPK(2);
        assertEquals("980GTX",goods.getCard_model());


    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
    }
}