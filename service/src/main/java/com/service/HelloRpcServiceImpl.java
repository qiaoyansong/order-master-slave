package com.service;

import com.HelloRpcService;
import com.alibaba.fastjson.JSON;
import com.enums.ErrorEnum;
import com.exception.BizException;
import com.param.SayHelloParam;
import com.result.RpcResult;
import com.result.SayHelloResult;
import com.utils.LogFactory;
import com.utils.RpcResultUtil;
import com.utils.ValidatorHelper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:10 下午
 * description：
 */
@Service
public class HelloRpcServiceImpl implements HelloRpcService {

    private static final Logger log = LogFactory.SERVICE_LOG;

    @Resource
    private HelloService helloService;

    @Override
    public RpcResult<SayHelloResult> sayHello(SayHelloParam param) {
        log.info("[HelloRpcServiceImpl#sayHello] param is {}", JSON.toJSONString(param));
        try {
            // ①参数校验
            ValidatorHelper.validate(param);
            // ②业务计算
            return RpcResultUtil.success(helloService.sayHello(param));
        } catch (BizException e) {
            log.warn("[HelloRpcServiceImpl#sayHello] bizError, msg={}", e.getMsg());
            return RpcResultUtil.fail(null, e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[HelloRpcServiceImpl#sayHello] error,e={}", JSON.toJSONString(e));
            return RpcResultUtil.fail(null, ErrorEnum.INNER_ERROR.getCode(), ErrorEnum.INNER_ERROR.getMsg());
        }
    }

}
