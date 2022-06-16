package com.common;

import com.BaseTestApplication;
import com.alibaba.fastjson.JSON;
import com.param.SayHelloParam;
import com.utils.HttpUtil;
import com.utils.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/9 7:17 下午
 * description：
 */
public class TestHttpUtil extends BaseTestApplication {

    private static final Logger serviceLog = LogFactory.SERVICE_LOG;

    @Autowired
    private HttpUtil httpUtil;

    @Value("${test-getUrl}")
    private String getUrl;

    @Value("${test-postUrl}")
    private String postUrl;

    /**
     * 简单测试post请求
     */
    @Test
    public void testSimpleWithPost() {
        SayHelloParam param = new SayHelloParam(1);
        String result = httpUtil.post(postUrl, JSON.toJSONString(param));
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 简单测试get请求
     */
    @Test
    public void testSimpleWithGet() {
        String result = httpUtil.get(getUrl, null);
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    /**
     * 测试配置项
     * connectTimeout，配置时间为1s,服务器启动，睡眠1s。期望报超时错误
     */
    @Test
    public void testConfigWith0() {
        try {
            String result = httpUtil.get(getUrl, null);
            System.out.println(result);
        } catch (Exception e) {
            serviceLog.error("[TestHttpUtil#testConfigWith] error, e={}", e);
        }
    }

    /**
     * 测试配置项
     * maxConnectPerRoute以及maxTotalConnect
     */
    @Test
    public void testConfigWith1() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    String result = httpUtil.get(getUrl, null);
                    System.out.println(result);
                } catch (Exception e) {
                    serviceLog.error("[TestHttpUtil#testConfigWith] error, e={}", e);
                }
            }).start();
        }
    }
}
