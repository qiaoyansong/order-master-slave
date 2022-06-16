package com.exception;

import lombok.Data;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:34 下午
 * description：业务异常
 */
@Data
public class BizException extends RuntimeException{

    private static final long serialVersionUID = -8000990074056687855L;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    public BizException() {
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
