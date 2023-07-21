package com.qjmoneynode.seckilldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qjmoneynode.seckilldemo.entity.Order;
import com.qjmoneynode.seckilldemo.entity.SeckillGoods;
import com.qjmoneynode.seckilldemo.entity.SeckillOrder;
import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.exception.GlobalException;
import com.qjmoneynode.seckilldemo.mapper.OrderMapper;
import com.qjmoneynode.seckilldemo.service.IGoodsService;
import com.qjmoneynode.seckilldemo.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qjmoneynode.seckilldemo.service.ISeckillGoodsService;
import com.qjmoneynode.seckilldemo.service.ISeckillOrderService;
import com.qjmoneynode.seckilldemo.vo.GoodsVo;
import com.qjmoneynode.seckilldemo.vo.OrderDetailVo;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import com.qjmoneynode.seckilldemo.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * @description 秒杀
     * @author qjmoneynode
     * @param [user, goodsVo]
     * @return com.qjmoneynode.seckilldemo.entity.Order
     **/
    @Transactional
    @Override
    public Order seckill(User user, GoodsVo goods) {
        //秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>()
                .eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        boolean result = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>()
                .setSql("stock_count = " + "stock_count-1")
                .eq("goods_id", goods.getId())
                .gt("stock_count", 0));
        if(!result){
            return null;
        }
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goods.getId(), seckillOrder);
        return order;
    }

    @Override
    public OrderDetailVo detail(Long orderId) {
        if (orderId == null) {
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo orderDetailVo = new OrderDetailVo(order, goodsVo);
        return orderDetailVo;
    }
}
