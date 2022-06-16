package com.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 7:55 下午
 * description：PRC返回结果基类
 */
@Data
public class RpcResult<T> implements Serializable {

    private static final long serialVersionUID = -539152367760093770L;

    private T data;

    private int code;

    private String msg;

}

