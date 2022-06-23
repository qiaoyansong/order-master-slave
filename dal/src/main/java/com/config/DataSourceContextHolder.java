package com.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ruyuan
 * 数据源上下文
 */
@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setTargetDataSource(String dataSourceType) {
        if (dataSourceType == null) {
            log.error("dataSource为空");
            throw new NullPointerException();
        }
        log.info("设置dataSource：{}", dataSourceType);
        CONTEXT_HOLDER.set(dataSourceType);
    }

    /**
     * 默认写模式
     *
     * @return
     */
    public static String getTargetDataSource() {
        String dataSource;
        if ((dataSource = CONTEXT_HOLDER.get()) == null) {
            log.error("dataSource为空");
            throw new NullPointerException();
        } else {
            return dataSource;
        }
    }

    public static void removeTargetDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
