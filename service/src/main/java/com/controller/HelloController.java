package com.controller;

import com.HelloRpcService;
import com.param.SayHelloParam;
import com.result.RpcResult;
import com.result.SayHelloResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 7:05 下午
 * description：
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloRpcService helloRpcService;

    @PostMapping("/say/hello")
    public RpcResult<SayHelloResult> sayHello(@RequestBody SayHelloParam sayHelloParam){
        return this.helloRpcService.sayHello(sayHelloParam);
    }

}
