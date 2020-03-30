package com.ccgydx.spring.boot.speed.kill.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.ccgydx.spring.boot.speed.kill.system.mapper")
public class SpringBootSpeedKillSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpeedKillSystemApplication.class, args);
    }

}



/*@SpringBootApplication
@MapperScan("com.ccgydx.spring.boot.speed.kill.system.mapper")
public class SpringBootSpeedKillSystemApplication  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootSpeedKillSystemApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpeedKillSystemApplication.class, args);
    }

}*/
