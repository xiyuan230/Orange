package com.xiyuan.orange.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticFileConfig implements WebMvcConfigurer {
    @Value("${spring.mvc.static-path-pattern}")
    private String RESOURCE_URL;
    @Value("${spring.resources.static-locations}")
    private String STATIC_FILE_PATH;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCE_URL)
                .addResourceLocations("file:"+STATIC_FILE_PATH);
    }
}
