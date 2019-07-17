package com.weixin.store.dao;

import com.weixin.store.domain.Goods;

import java.util.List;

public interface GoodsDao {

    Goods findByPK(long pk);

    List<Goods> findAll();

    void create(Goods goods);
    void modify(Goods goods);
    void remove(String pk);
}
