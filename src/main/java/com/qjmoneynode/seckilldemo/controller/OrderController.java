package com.qjmoneynode.seckilldemo.controller;

import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.service.IOrderService;
import com.qjmoneynode.seckilldemo.vo.OrderDetailVo;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import com.qjmoneynode.seckilldemo.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-14
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user, Long orderId){
        if(user==null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }
}
