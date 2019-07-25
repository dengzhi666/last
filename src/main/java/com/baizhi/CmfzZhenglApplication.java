package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.baizhi.dao")
public class CmfzZhenglApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzZhenglApplication.class, args);
    }

}
