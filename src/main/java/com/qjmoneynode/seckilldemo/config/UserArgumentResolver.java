package com.qjmoneynode.seckilldemo.config;

import com.qjmoneynode.seckilldemo.entity.User;
import com.qjmoneynode.seckilldemo.service.IUserService;
import com.qjmoneynode.seckilldemo.utils.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;


/**
 * @author qjmoneynode
 * @version 1.0
 * @description 自定义用户参数
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private IUserService itUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return parameterType == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

//        return UserContext.getUser();

        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        String userTicket = CookieUtil.getCookieValue(nativeRequest, "userTicket");
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        return itUserService.getUserByCookie(userTicket, nativeRequest, nativeResponse);
    }

}
