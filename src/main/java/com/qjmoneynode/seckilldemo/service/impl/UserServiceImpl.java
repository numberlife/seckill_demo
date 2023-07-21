package com.qjmoneynode.seckilldemo.service.impl;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qjmoneynode.seckilldemo.exception.GlobalException;
import com.qjmoneynode.seckilldemo.mapper.UserMapper;
import com.qjmoneynode.seckilldemo.service.IUserService;
import com.qjmoneynode.seckilldemo.utils.CookieUtil;
import com.qjmoneynode.seckilldemo.utils.MD5Util;
import com.qjmoneynode.seckilldemo.utils.UUIDUtil;
import com.qjmoneynode.seckilldemo.utils.ValidatorUtil;
import com.qjmoneynode.seckilldemo.vo.LoginVo;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import com.qjmoneynode.seckilldemo.vo.RespBeanEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.qjmoneynode.seckilldemo.entity.User;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

//        //参数校验
//        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        User user = userMapper.selectById(mobile);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
//        System.out.println(MD5Util.formPassToDBPass(password, user.getSalt()));
        //判断密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成Cookie
        String userTicket = UUIDUtil.uuid();
        //将用户信息存入redis
        redisTemplate.opsForValue().set("user:" + userTicket, user);

        //request.getSession().setAttribute(userTicket, user);
        CookieUtil.setCookie(request, response, "userTicket", userTicket);
        return RespBean.success(userTicket);
    }

    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response) {
        if(userTicket.isEmpty()){
            return null;
        }
        User user = (User)redisTemplate.opsForValue().get("user:" + userTicket);
        if(user != null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }

    @Override
    public RespBean updatePassword(String userTicket, Long id, String password,HttpServletRequest request, HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToDbPass(password, user.getSalt()));
        int result = userMapper.updateById(user);
        if (1 == result) {
            //删除Redis
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }

}
