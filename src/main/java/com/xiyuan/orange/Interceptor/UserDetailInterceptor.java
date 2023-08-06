package com.xiyuan.orange.Interceptor;

import com.xiyuan.orange.Common.NotCreatedUserDetailException;
import com.xiyuan.orange.Mapper.UserDetailMapper;
import com.xiyuan.orange.Model.UserDetailModel;
import com.xiyuan.orange.Service.UserDetailService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDetailInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        UserDetailService userDetailService = (UserDetailService) factory.getBean("userDetailService");
        UserDetailModel detail = userDetailService.getUserDetailByOpenid(openid);
        if(detail == null) {
            throw new NotCreatedUserDetailException();
        }
        return true;
    }
}
