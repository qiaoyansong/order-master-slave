package com.service;

import com.BaseTestApplication;
import com.HelloRpcService;
import com.constant.RpcCode;
import com.param.SayHelloParam;
import com.result.RpcResult;
import com.result.SayHelloResult;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 6:45 下午
 * description：
 */
public class TestHelloRpcService extends BaseTestApplication {

    @Autowired
    private HelloRpcService helloRpcService;

    @Test
    public void testSayHello(){
        RpcResult<SayHelloResult> rpcResult = helloRpcService.sayHello(new SayHelloParam(0));
        Assert.assertNotNull(rpcResult);
        Assert.assertEquals(rpcResult.getCode(), RpcCode.SUCCESS);
        Assert.assertNotNull(rpcResult.getData());
        System.out.println(rpcResult.getData().getMessage());
    }
}
