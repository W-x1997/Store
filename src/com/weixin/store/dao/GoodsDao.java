package com.weixin.store.dao;

import com.weixin.store.domain.Goods;

import java.util.List;

public interface GoodsDao {

    Goods findByPK(long pk) throws ClassNotFoundException;

    List<Goods> findAll() throws ClassNotFoundException;

    void create(Goods goods);
    void modify(Goods goods);
    void remove(String pk);
}
