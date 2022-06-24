package com.convertor;

import com.domain.OrderInfo;
import com.dto.UserOrderDTO;
import com.utils.BeanMapper;
import org.springframework.stereotype.Component;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 4:48 下午
 * description：do转换成为dto 辅助类(不使用beanMapper)
 */
@Component
public class Do2DtoHelper {

    public UserOrderDTO orderInfo2UserOrderDTO(OrderInfo orderInfo) {
        return BeanMapper.map(orderInfo, UserOrderDTO.class);
    }
}
