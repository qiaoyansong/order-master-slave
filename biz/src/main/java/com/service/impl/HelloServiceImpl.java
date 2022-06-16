package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.domain.User;
import com.dto.UserInfoDto;
import com.mapper.UserMapper;
import com.param.SayHelloParam;
import com.result.SayHelloResult;
import com.service.HelloService;
import com.service.helper.Do2DtoHelper;
import com.utils.LogFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:46 下午
 * description：
 */
@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger LOG = LogFactory.BIZ_SERVICE_LOG;

    @Resource
    private UserMapper userMapper;

    @Resource
    private Do2DtoHelper do2DtoHelper;

    @Override
    public SayHelloResult sayHello(SayHelloParam param) {
        LOG.info("[HelloServiceImpl#sayHello] begin,param={}", JSON.toJSONString(param));
        SayHelloResult result = new SayHelloResult();
        User user = userMapper.fetchUserInfo(param.getUid());
        if(user != null) {
            UserInfoDto userInfoDto = do2DtoHelper.transfer(user);
            result.setMessage(userInfoDto.getUsername() + ",你好");
        }
        return result;
    }

}
