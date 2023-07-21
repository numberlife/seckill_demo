package com.qjmoneynode.seckilldemo.controller;

import com.qjmoneynode.seckilldemo.service.IUserService;
import com.qjmoneynode.seckilldemo.vo.LoginVo;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/*
 * @description
 * @author qjmoneynode 
 * @date 2023/7/11 13:01
 * @param 
 * @return 
 **/

@Controller
@RequestMapping("/login")
@Slf4j
@Api(value = "登录", tags = "登录")
public class LoginController {

    @Autowired
    private IUserService userService;

    /*
     * @description 跳转登陆界面
     * @author qjmoneynode
     * @date 2023/7/11 13:02
     * @param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    /*
     * @description 登录功能
     * @author qjmoneynode
     * @date 2023/7/11 13:23
     * @param [loginVo]
     * @return com.qjmoneynode.seckilldemo.vo.RespBean
     **/
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request,HttpServletResponse response){
        return userService.doLogin(loginVo,request,response);
    }
}
