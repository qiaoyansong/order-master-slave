package com.service.helper;

import com.domain.User;
import com.dto.UserInfoDto;
import org.springframework.stereotype.Component;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 4:48 下午
 * description：do转换成为dto 辅助类(不使用beanMapper)
 */
@Component
public class Do2DtoHelper {

    public UserInfoDto transfer(User user) {
        UserInfoDto result = new UserInfoDto();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        return result;
    }
}
