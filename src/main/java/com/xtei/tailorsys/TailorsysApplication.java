package com.xtei.tailorsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 主程序类
 */
@SpringBootApplication
@EnableTransactionManagement
public class TailorsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(TailorsysApplication.class, args);


    }

}
