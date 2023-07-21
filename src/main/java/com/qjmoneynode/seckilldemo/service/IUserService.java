package com.qjmoneynode.seckilldemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.vo.LoginVo;
import com.qjmoneynode.seckilldemo.vo.RespBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qjmoneynode
 * @since 2023-07-10
 */
public interface IUserService extends IService<User> {

    /*
     * @description 登录
     * @author qjmoneynode
     * @date 2023/7/11 14:44
     * @param [loginVo]
     * @return com.qjmoneynode.seckilldemo.vo.RespBean
     **/

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /*
     * @description 根据cookie获取用户
     * @author qjmoneynode
     * @param [userTicket]
     * @return com.qjmoneynode.seckilldemo.entity.User
     **/
    User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);

    /*
     * @description 更新密码
     * @author qjmoneynode
     * @param [userTicket, id, password]
     * @return com.qjmoneynode.seckilldemo.vo.RespBean
     **/
    RespBean updatePassword(String userTicket,Long id,String password,
                            HttpServletRequest request, HttpServletResponse response);
}
