package com.convertor;

import com.dto.UserOrderDTO;
import com.utils.BeanMapper;
import com.vo.UserOrderVO;
import org.springframework.stereotype.Component;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 6/23/22 8:38 下午
 * description：dto 转换成 vo辅助类
 */
@Component
public class Dto2VoHelper {

    public UserOrderVO userOrderDTO2UserOrderVO(UserOrderDTO userOrderDTO) {
        return BeanMapper.map(userOrderDTO, UserOrderVO.class);
    }

}
