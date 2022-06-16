package com.redis.impl;

import com.redis.RedisService;
import com.utils.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/9 2:37 下午
 * description：
 */
@Component
public class RedisServiceImpl implements RedisService {
    
    @Autowired
    private JedisPool jedisPool;

    private static final Logger REDIS_SYNC_LOG = LogFactory.REDIS_SYNC_LOG;

    private static final Logger REDIS_ASYNC_LOG = LogFactory.REDIS_ASYNC_LOG;

    @Override
    public boolean set(String key, String value){
        try(Jedis jedis = jedisPool.getResource()){
            String result = jedis.set(key, value);
            REDIS_SYNC_LOG.info("[RedisServiceImpl#set] key={}, value={}, result={}", key, value, result);
            return "OK".equalsIgnoreCase(result);
        }
    }

    @Override
    public String get(String key) {
        try(Jedis jedis = jedisPool.getResource()){
            String result = jedis.get(key);
            REDIS_ASYNC_LOG.info("[RedisServiceImpl#get] key={}, result={}", key, result);
            return result;
        }
    }

}
