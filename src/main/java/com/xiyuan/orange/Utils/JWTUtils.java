package com.xiyuan.orange.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import java.util.Calendar;
import java.util.Map;

/**
 * JWT工具类
 */
@Slf4j
public class JWTUtils {
    private static final String SALT="Sql123.."; // JWT密钥

    /**
     * 获取token
     * @param payload
     * @return
     */
    public static String getToken(Map<String,String> payload){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,30); // 设置Token有效期
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        payload.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SALT));
        return token;
    }
    /**
     * 验证token  并且放回DecodedJWT  获取token信息方法
     */
    public  static DecodedJWT verify(String token){
        //没有抛出异常 验证通过
        return JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
    }
    /**
     * 获取token信息方法
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
        return verify;
    }
    /**
     * 获取用户openid
     */
    public static String getOpenid(String token) {
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String openid = tokenInfo.getClaim("openid").asString();
        return openid;
    }
}
