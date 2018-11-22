package com.taotao.order.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.SnowFlake;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.dao.impl.JedisClientSingle;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单service
 * @author: xianzhixianzhixian
 * @date: 2018/11/20 9:52 PM
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private JedisClientSingle jedisClientSingle;
    @Autowired
    private SnowFlake snowFlake;

    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) throws Exception {
        int count = 0;
        //获得订单号
        Long orderId = snowFlake.nextId();
        //补全pojo的属性
        order.setOrderId(orderId+"");
        order.setStatus(1); /* 1-未付款，2-已付款，3-未发货，4-已发货，5-交易成功，6-交易关闭 */
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setBuyerRate(0); /* 0-未评价，1-已评价 */
        //向订单表中插入记录
        count += orderMapper.insert(order);
        //插入订单明细
        for (TbOrderItem orderItem : itemList){
            Long orderItemId = snowFlake.nextId();
            //订单明细id
            orderItem.setId(orderItemId+"");
            //订单id
            orderItem.setOrderId(orderId+"");
            count += orderItemMapper.insert(orderItem);
        }
        //订单id
        orderShipping.setOrderId(orderId+"");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        //插入物流表
        orderShippingMapper.insert(orderShipping);
        if (count == 0) {
            return TaotaoResult.build(500, "新建订单失败", orderId);
        }
        return TaotaoResult.ok(orderId);
    }
}
