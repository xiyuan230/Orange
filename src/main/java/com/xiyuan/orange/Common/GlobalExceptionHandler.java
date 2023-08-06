package com.xiyuan.orange.Common;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R ExceptionHandler(Exception e, HttpServletRequest req){
        log.info(e.toString());
        log.info("错误路径: {}",req.getRequestURL());
        return R.error("未知错误");
    }

    @ExceptionHandler(NoLoginException.class)
    public R NoLoginExceptionHandler (NoLoginException e) {
        log.info(e.getMessage());
        return R.error("非法请求").setCode(401);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public R JWTDecodeExceptionHander(JWTDecodeException e) {
        log.info(e.getMessage());
        return R.error("非法请求").setCode(401);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public R JWTDecodeExceptionHander(TokenExpiredException e) {
        log.info(e.getMessage());
        return R.error("身份信息失效").setCode(401);
    }
    @ExceptionHandler(NotCreatedUserDetailException.class)
    public R NotCreatedUserDetailExceptionHander(NotCreatedUserDetailException e) {
        log.info(e.getMessage());
        return R.error("请先完善详细信息").setCode(402);
    }
}
