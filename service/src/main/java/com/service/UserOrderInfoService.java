package com.service;

import com.param.OrderInfoQuery;
import com.vo.UserOrderVO;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 6/23/22 7:56 下午
 * description：
 */
public interface UserOrderInfoService {

    /**
     * 根据查询条件获得订单列表
     *
     * @param userOrderInfoQuery
     * @return
     */
    UserOrderVO queryUserOrderInfo(OrderInfoQuery userOrderInfoQuery);

}
