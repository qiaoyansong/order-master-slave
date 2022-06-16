package com;

import com.mapper.UserMapper;
import com.service.HelloRpcServiceImpl;
import com.service.HelloService;
import com.service.helper.Do2DtoHelper;
import com.service.impl.HelloServiceImpl;
import com.utils.MockHelper;
import lombok.Getter;
import org.junit.Before;
import org.mockito.Mockito;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/26 10:26 上午
 * description：
 */
@Getter
public class BaseTddTest {

    protected HelloRpcService helloRpcService;
    protected HelloService helloService;
    protected UserMapper userMapper;
    protected Do2DtoHelper do2DtoHelper;

    @Before
    public void init() throws Exception {
        // 确定的上下游服务
        helloRpcService = new HelloRpcServiceImpl();
        helloService = new HelloServiceImpl();
        MockHelper.mockField(helloRpcService, "helloService", helloService);
        do2DtoHelper = new Do2DtoHelper();
        MockHelper.mockField(helloService, "do2DtoHelper", do2DtoHelper);
        // 不确定的上下游服务，直接mock出来
        userMapper = Mockito.mock(UserMapper.class);
        MockHelper.mockField(helloService, "userMapper", userMapper);
    }
}
