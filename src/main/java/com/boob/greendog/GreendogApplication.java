package com.boob.greendog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boob.greendog.mapper")
public class GreendogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreendogApplication.class, args);
    }

}
