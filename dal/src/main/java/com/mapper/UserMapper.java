package com.mapper;

import com.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 4:39 下午
 * description：
 */
@Mapper
public interface UserMapper {

    /**
     * 查看用户信息
     *
     * @param uid 用户ID
     * @return 用户信息
     */
    User fetchUserInfo(Integer uid);
}
