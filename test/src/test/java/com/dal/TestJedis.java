package com.dal;

import com.BaseTestApplication;
import com.redis.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/9 2:52 下午
 * description：
 */
public class TestJedis extends BaseTestApplication {

    @Autowired
    private RedisService redisService;

    @Test
    public void testSet() {
        boolean b = redisService.set("test", "test");
        System.out.println(b);
    }

    @Test
    public void testGet() {
        String s = redisService.get("test");
        System.out.println(s);
    }

}
