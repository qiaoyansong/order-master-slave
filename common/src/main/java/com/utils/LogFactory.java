package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 2:33 下午
 * description：日志管理工具类
 */
public class LogFactory {

    /**
     * service日志
     */
    public static final Logger SERVICE_LOG = LoggerFactory.getLogger("serviceLogger");

    /**
     * 业务日志
     */
    public static final Logger BIZ_SERVICE_LOG = LoggerFactory.getLogger("bizServiceLogger");

    /**
     * 通用日志
     */
    public static final Logger COMMON_LOG = LoggerFactory.getLogger("commonLogger");

    /**
     * 防腐层日志
     */
    public static final Logger INTEGRATION_LOG = LoggerFactory.getLogger("integrationLogger");

    /**
     * 警告日志
     */
    public static final Logger WARN_LOG = LoggerFactory.getLogger("warnLogger");

    /**
     * 错误日志
     */
    public static final Logger ERROR_LOG = LoggerFactory.getLogger("errorLogger");

    /**
     * redis同步日志
     */
    public static final Logger REDIS_SYNC_LOG = LoggerFactory.getLogger("redisLogger");

    /**
     * redis异步日志
     */
    public static final Logger REDIS_ASYNC_LOG = LoggerFactory.getLogger("redisAsyncLogger");


}
