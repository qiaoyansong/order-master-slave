package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:05 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SayHelloResult{

    /**
     * 返回的信息
     */
    private String message;

}
