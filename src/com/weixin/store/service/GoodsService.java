package com.weixin.store.service;

import com.weixin.store.domain.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> queryAll() throws ClassNotFoundException;

    List<Goods> queryByStartEnd(int start,int end) throws ClassNotFoundException;


    Goods querDetail(long goodid) throws ClassNotFoundException;

}
