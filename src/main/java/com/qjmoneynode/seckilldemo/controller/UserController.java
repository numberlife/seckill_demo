package com.qjmoneynode.seckilldemo.controller;

import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.rabbitmq.MQSender;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MQSender mqSender;

    @RequestMapping("/info")
    public RespBean info(User user){
        return RespBean.success(user);
    }


    @RequestMapping("/mq")
    @ResponseBody
    public void mq(){
        mqSender.send("Hello");
    }
}
