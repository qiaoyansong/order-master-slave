package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/9 2:26 下午
 * description：Jedis配置类
 */
@Configuration
public class JedisConfiguration {

    @Value("${jedis.host}")
    private String hostName;

    @Value("${jedis.port}")
    private Integer port;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        return new JedisPool(config, hostName, port);
    }

}
