package com.weixin.store.dao;

import com.weixin.store.domain.Goods;

import java.util.List;

public interface GoodsDao {

    Goods findByPK(long pk) throws ClassNotFoundException;

    List<Goods> findAll() throws ClassNotFoundException;


    /**
     * 提供分页查询
     * @param start 开始索引
     * @param end  结束索引
     * @return
     */

    List<Goods> findStartEnd(int start,int end) throws ClassNotFoundException;

    void create(Goods goods);
    void modify(Goods goods);
    void remove(String pk);
}
