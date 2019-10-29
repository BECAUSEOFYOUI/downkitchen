package com.self.kitchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.self.kitchen.*")
@MapperScan("com.self.kitchen.dao")
@EnableSwagger2
@EnableCaching // 开启缓存
public class DownKitchenApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DownKitchenApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DownKitchenApplication.class);
    }
}
