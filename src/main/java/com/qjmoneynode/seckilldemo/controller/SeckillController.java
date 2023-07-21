package com.qjmoneynode.seckilldemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qjmoneynode.seckilldemo.entity.Order;
import com.qjmoneynode.seckilldemo.entity.SeckillOrder;
import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.service.IGoodsService;
import com.qjmoneynode.seckilldemo.service.IOrderService;
import com.qjmoneynode.seckilldemo.service.ISeckillOrderService;
import com.qjmoneynode.seckilldemo.utils.JsonUtil;
import com.qjmoneynode.seckilldemo.vo.GoodsVo;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import com.qjmoneynode.seckilldemo.vo.RespBeanEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

/**
 * @author qjmoneynode
 * @version 1.0
 * @description
 * @createTime 2023-07-15  20:35
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * @description 秒杀功能
     * windows优化前QPS:967.4
     * @author qjmoneynode
     * @param [model, user, goodsId]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSecKill(User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if (goods.getStockCount() < 1) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //判断是否重复抢购
//        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId())
//                .eq("goods_id", goodsId));
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get
                ("order:" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
        Order order = orderService.seckill(user, goods);
        return RespBean.success(order);
    }
//    @RequestMapping("/doSeckill")
//    public String doSecklii(Model model, User user, Long goodsId){
//        if(user == null){
//            return "login";
//        }
//        model.addAttribute("User",user);
//        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
//        //判断库存
//        if(goods.getStockCount()<1){
//            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
//            return "secKillFail";
//        }
//        //判断是否重复抢购
//        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId())
//                .eq("goods_id", goodsId));
//        if(seckillOrder != null){
//            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
//            return "secKillFail";
//        }
//        Order order = orderService.seckill(user,goods);
//        model.addAttribute("order",order);
//        model.addAttribute("goods",goods);
//        return "orderDetail";
//    }
}
