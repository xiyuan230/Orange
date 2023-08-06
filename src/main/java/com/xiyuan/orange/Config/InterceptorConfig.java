package com.xiyuan.orange.Config;

import com.xiyuan.orange.Interceptor.JWTInterceptor;
import com.xiyuan.orange.Interceptor.UserDetailInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/test/**","/error","/api/login/**","/test/**","/static/**","/api/register/**","/api/carousel/**");
        registry.addInterceptor(new UserDetailInterceptor())
                .addPathPatterns("/api/product","/api/product/search","/api/product/publish");
    }
}
