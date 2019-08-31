package com.weixin.store.service.Imp;

import com.weixin.store.dao.GoodsDao;
import com.weixin.store.dao.Imp.GoodsDaoImp;
import com.weixin.store.dao.Imp.OrderDaoImp;
import com.weixin.store.dao.Imp.OrderLineItemDaoImp;
import com.weixin.store.dao.OrderDao;
import com.weixin.store.dao.OrderLineItemDao;
import com.weixin.store.domain.Goods;
import com.weixin.store.domain.OrderLineItem;
import com.weixin.store.domain.Orders;
import com.weixin.store.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImp implements OrderService {
    GoodsDao goodsDao=new GoodsDaoImp();
    OrderDao orderDao=new OrderDaoImp();
    OrderLineItemDao orderLineItemDao=new OrderLineItemDaoImp();


    @Override
    public String submitOrders(List <Map<String,Object>> cart) throws ClassNotFoundException {

        Orders orders=new Orders();
        Date date=new Date();

        String id=String.valueOf(date.getTime())+String.valueOf((int)(Math.random()*10));

        orders.setId(id);
        orders.setOrderDate(date);
        orders.setStatus(1);//1 表示待付款
        orders.setTotal(0);
        //将订单插入数据库

        orderDao.create(orders);//??????


        double total=0.0;

        for(Map item:cart){
            //item 结构 【商品id，数量】
            long goodsid=(long) item.get("goodsid");
            Integer quantity=(Integer)item.get("quantity");
            Goods goods=goodsDao.findByPK(goodsid);

            double subtotal=quantity * goods.getPrice();
            total=total+subtotal;


            //订单详细

            OrderLineItem orderLineItem=new OrderLineItem();

            orderLineItem.setQuantity(quantity);
            orderLineItem.setOrders(orders);
            orderLineItem.setGoods(goods);
            orderLineItem.setSubtotal(subtotal);


            orderLineItemDao.create(orderLineItem);
        }
            orders.setTotal(total);
            orderDao.modify(orders);

        return id;

    }
}
