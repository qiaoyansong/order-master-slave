package com.service.impl;

import com.annotation.SelectDataSource;
import com.convertor.Dto2VoHelper;
import com.dto.UserOrderDTO;
import com.param.OrderInfoQuery;
import com.service.UserOrderInfoBizService;
import com.service.UserOrderInfoService;
import com.utils.ValidatorHelper;
import com.vo.UserOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 6/23/22 7:57 下午
 * description：
 */
@Service
public class UserOrderInfoServiceImpl implements UserOrderInfoService {

    @Autowired
    private UserOrderInfoBizService userOrderInfoBizService;

    @Autowired
    private Dto2VoHelper dto2VoHelper;

    @SelectDataSource(isMaster = false)
    @Override
    public UserOrderVO queryUserOrderInfo(OrderInfoQuery userOrderInfoQuery) {
        ValidatorHelper.validate(userOrderInfoQuery);
        UserOrderDTO userOrderDTO = userOrderInfoBizService.queryUserOrderInfo(userOrderInfoQuery.getOrderId());
        return dto2VoHelper.userOrderDTO2UserOrderVO(userOrderDTO);
    }

}
