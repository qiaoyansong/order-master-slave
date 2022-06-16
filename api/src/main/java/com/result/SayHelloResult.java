package com.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:05 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SayHelloResult implements Serializable {

    private static final long serialVersionUID = -2698243142977067627L;

    /**
     * 返回的信息
     */
    private String message;

}
