package com.example.kgdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.kgdemo.mapper")
public class KgdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KgdemoApplication.class, args);
    }

}
