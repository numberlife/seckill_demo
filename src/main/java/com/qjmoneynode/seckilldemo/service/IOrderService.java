package com.qjmoneynode.seckilldemo.service;

import com.qjmoneynode.seckilldemo.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.vo.GoodsVo;
import com.qjmoneynode.seckilldemo.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-14
 */
public interface IOrderService extends IService<Order> {

    /*
     * @description 秒杀
     * @author qjmoneynode
     * @param [user, goodsVo]
     * @return com.qjmoneynode.seckilldemo.entity.Order
     **/
    Order seckill(User user, GoodsVo goodsVo);

    /*
     * @description 订单详情
     * @author qjmoneynode
     * @param [order]
     * @return com.qjmoneynode.seckilldemo.vo.OrderDetailVo
     **/
    OrderDetailVo detail(Long orderId);
}
