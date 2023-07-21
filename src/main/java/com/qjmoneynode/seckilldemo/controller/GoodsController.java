package com.qjmoneynode.seckilldemo.controller;

import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.service.IGoodsService;
import com.qjmoneynode.seckilldemo.service.IUserService;
import com.qjmoneynode.seckilldemo.vo.DetailVo;
import com.qjmoneynode.seckilldemo.vo.GoodsVo;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import com.qjmoneynode.seckilldemo.service.impl.GoodsServiceImpl;
import com.qjmoneynode.seckilldemo.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
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
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author qjmoneynode
 * @version 1.0
 * @description TODO
 * @createTime 2023-07-11  17:43
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    /*
     * @description 跳转商品列表页
     * windows优化前QPS:1749
     * @author qjmoneynode
     * @date 2023/7/11 17:51
     * @param
     * @return
     **/
    @RequestMapping(value = "/toList")
    public String toList(Model model, User user) {
//        //Redis中获取页面，如果不为空，直接返回页面
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        String html = (String) valueOperations.get("goodsList");
//        if (!html.isEmpty()) {
//            return html;
//        }
        model.addAttribute("User", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
////        return "goodsList";
//        //如果为空，手动渲染，存入redis并返回
//        WebContext context = new WebContext(request.getServletContext(), request.getLocale(), model.asMap());
//        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", context);
//        if(!html.isEmpty()){
//            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
//        }
        return "goodsList";
    }


    /*
     * @description 跳转商品详情页
     * @author qjmoneynode
     * @param [model, user, goodsId]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/toDetail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(User user, @PathVariable Long goodsId) {
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int seckillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;

        if (nowDate.before(startDate)) {
            //秒杀还未开始0
            remainSeconds = (int) ((startDate.getTime() - nowDate.getTime()) / 1000);
        } else if (nowDate.after(endDate)) {
            //秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        DetailVo detailVo = new DetailVo();
        detailVo.setGoodsVo(goodsVo);
        detailVo.setUser(user);
        detailVo.setRemainSeconds(remainSeconds);
        detailVo.setSeckillStatus(seckillStatus);
        return RespBean.success(detailVo);
    }
}
