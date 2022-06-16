package com.enums;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 7:45 下午
 * description：错误码枚举
 */
public enum ErrorEnum {
    /**
     * 前置校验
     */
    PARAM_ERROR(10001, "参数错误"),
    PARAM_ERROR_WITH_MSG(10001, "参数错误,%S"),

    /**
     * 系统错误
     */
    INNER_ERROR(20001, "内部错误"),
    INNER_ERROR_WITH_MSG(20001, "内部错误,错误信息为%s"),

    /**
     * 业务错误
     */

    /**
     * 下游服务调用错误
     */
    DOWNSTREAM_SERVICES_ERROR_WITH_MSG(40001, "调用%s服务错误"),
    ;

    /**
     * 错误代码
     */
    private final int code;

    /**
     * 错误消息
     */
    private final String msg;

    /**
     * 构造函数
     *
     * @param code 错误代码
     * @param msg  错误消息
     */
    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 根据错误码，获取对应的错误枚举
     * @param code 错误码
     * @return 错误枚举
     *
     */
    public static ErrorEnum getEunm(int code) {
        ErrorEnum[] values = ErrorEnum.values();
        ErrorEnum temp;
        int i;
        for (i = 0; i < values.length; i++) {
            if ((temp = values[i]).getCode() == code) {
                return temp;
            }
        }
        return null;
    }
}
