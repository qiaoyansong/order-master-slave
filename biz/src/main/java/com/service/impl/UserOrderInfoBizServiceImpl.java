package com.service.impl;

import com.convertor.Do2DtoHelper;
import com.domain.OrderInfo;
import com.dto.UserOrderDTO;
import com.mapper.UserMapper;
import com.service.UserOrderInfoBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 6/24/22 10:26 上午
 * description：
 */
@Service
public class UserOrderInfoBizServiceImpl implements UserOrderInfoBizService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    Do2DtoHelper do2DtoHelper;

    @Override
    public UserOrderDTO queryUserOrderInfo(Integer orderId) {
        OrderInfo orderInfo =  this.userMapper.queryUserOrderInfo(orderId);
        return do2DtoHelper.orderInfo2UserOrderDTO(orderInfo);
    }

}
