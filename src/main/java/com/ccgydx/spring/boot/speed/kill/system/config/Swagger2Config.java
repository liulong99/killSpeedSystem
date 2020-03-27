package com.ccgydx.spring.boot.speed.kill.system.config;

/**
 * @Description : Swigger2配置
 * @Author 刘龙
 * @Date 2020/3/22 13:57
 * @Version 1.0
 **/

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("秒杀项目管理API文档")
                .description("本文档描述了秒杀项目微服务接口定义")
                .version("1.0")
                .contact(new Contact("liulong", "https://home.cnblogs.com/u/liulong99/", "706750966@qq.com"))
                .build();
    }
}
