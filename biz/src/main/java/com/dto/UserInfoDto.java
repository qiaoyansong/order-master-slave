package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 4:46 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;
}
