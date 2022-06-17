package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author ruyuan
 * 动态主从数据源切换
 */
@Slf4j
public class DataSourceRouter extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        log.info("当前数据源为" + DataSourceContextHolder.getDataSourceType());
        //返回选择的数据源
        return DataSourceContextHolder.getDataSourceType();
    }
}

