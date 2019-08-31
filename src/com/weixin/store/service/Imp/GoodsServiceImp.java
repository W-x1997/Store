package com.weixin.store.service.Imp;

import com.weixin.store.dao.GoodsDao;
import com.weixin.store.dao.Imp.GoodsDaoImp;
import com.weixin.store.domain.Goods;
import com.weixin.store.service.GoodsService;

import java.util.List;

public class GoodsServiceImp implements GoodsService {

    GoodsDao goodsDao=new GoodsDaoImp();

    @Override
    public List<Goods> queryAll() throws ClassNotFoundException {
        return goodsDao.findAll();
    }

    @Override
    public List<Goods> queryByStartEnd(int start, int end) throws ClassNotFoundException {
        return goodsDao.findStartEnd(start,end);
    }

    @Override
    public Goods querDetail(long goodid) throws ClassNotFoundException {
        return goodsDao.findByPK(goodid);
    }
}
