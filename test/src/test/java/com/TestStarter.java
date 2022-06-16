package com;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/24 5:04 下午
 * description：
 */
public class TestStarter {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Starter.class, args);
    }
}
