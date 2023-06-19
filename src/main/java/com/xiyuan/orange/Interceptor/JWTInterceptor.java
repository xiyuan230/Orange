package com.xiyuan.orange.Interceptor;

import com.xiyuan.orange.Common.NoLoginException;
import com.xiyuan.orange.Utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(token == null ) {
            throw new NoLoginException("非法请求");
        }
        JWTUtils.verify(token);
        return true;
    }
}
