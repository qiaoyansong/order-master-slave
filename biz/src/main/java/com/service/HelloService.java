package com.service;

import com.param.SayHelloParam;
import com.result.SayHelloResult;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:45 下午
 * description：
 */
public interface HelloService {

    /**
     * 简单的业务实现
     * @param param
     * @return
     */
    SayHelloResult sayHello(SayHelloParam param);

}
