package com.xiyuan.orange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiyuan.orange.Mapper")
public class OrangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeApplication.class, args);
    }

}
