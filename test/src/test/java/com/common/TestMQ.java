package com.common;

import com.BaseTestApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/2/8 7:42 下午
 * description：测试rocketmq
 */
public class TestMQ extends BaseTestApplication {

    @Value("${rocketmq.topic.test}")
    private String testTopic;

    @Autowired
    private RocketMQTemplate template;

    @Test
    public void sendSync() {
        SendResult result = template.syncSend(testTopic, "hello");
        System.out.println(result);
    }

    @Test
    public void sendAsync() {
        template.asyncSend(testTopic, "hello1", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }

}
