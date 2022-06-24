package com.service;

import com.dto.UserOrderDTO;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 6/23/22 8:30 下午
 * description：
 */
public interface UserOrderInfoBizService {

    /**
     * 根据订单ID查询订单信息
     * @param orderId 订单ID
     * @return
     */
    UserOrderDTO queryUserOrderInfo(Integer orderId);

}
