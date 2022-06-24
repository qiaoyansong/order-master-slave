package com.mapper;

import com.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 4:39 下午
 * description：
 */
@Mapper
public interface UserMapper {

    /**
     * 根据订单ID查询订单信息
     * @param orderId 订单ID
     * @return
     */
    OrderInfo queryUserOrderInfo(Integer orderId);

}
