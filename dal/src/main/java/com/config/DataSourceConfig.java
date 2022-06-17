package com.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/6/16 9:13 下午
 * description：数据源配置
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Slf4j
@Data
public class DataSourceConfig {

    /**
     * 主库数据源信息
     * 注解使用参考网址 https://blog.csdn.net/csdn_cai/article/details/117949600
     */
    @NestedConfigurationProperty
    private Map<String, Map<String, String>> masters = Maps.newLinkedHashMap();

    /**
     * 从库数据源信息
     */
    @NestedConfigurationProperty
    private Map<String, Map<String, String>> slaves = Maps.newLinkedHashMap();

    @Bean
    public List<ImmutablePair<String, DataSource>> masterDataSources() throws Exception {
        if (MapUtils.isEmpty(masters)) {
            throw new Exception("主库数据源不能为空");
        }
        List<ImmutablePair<String, DataSource>> result = Lists.newArrayListWithExpectedSize(masters.size());
        for (Map.Entry<String, Map<String, String>> entrys : masters.entrySet()) {
            result.add(ImmutablePair.of(entrys.getKey(), DruidDataSourceFactory.createDataSource(entrys.getValue())));
        }
        return result;
    }

    @Bean
    public List<ImmutablePair<String, DataSource>> slaveDataSources() throws Exception {
        if (MapUtils.isEmpty(slaves)) {
            throw new Exception("从库数据源不能为空");
        }
        List<ImmutablePair<String, DataSource>> result = Lists.newArrayListWithExpectedSize(masters.size());
        for (Map.Entry<String, Map<String, String>> entrys : masters.entrySet()) {
            result.add(ImmutablePair.of(entrys.getKey(), DruidDataSourceFactory.createDataSource(entrys.getValue())));
        }
        return result;
    }

    @Bean
    @Primary
    @DependsOn({"masterDataSources", "slaveDataSources"})
    public DataSourceRouter routingDataSource() throws Exception {
        final Map<Object, Object> targetDataSources = new HashMap<>();
        List<ImmutablePair<String, DataSource>> masters = masterDataSources();
        for (ImmutablePair<String, DataSource> pair : masters) {
            targetDataSources.put(pair.getLeft(), pair.getRight());
        }
        List<ImmutablePair<String, DataSource>> slaves = slaveDataSources();
        for (ImmutablePair<String, DataSource> pair : slaves) {
            targetDataSources.put(pair.getLeft(), pair.getRight());
        }
        DataSourceRouter routingDataSource = new DataSourceRouter();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(masters);
        return routingDataSource;
    }


    /**
     * 设置事务，事务需要知道当前使用的是哪个数据源才能进行事务处理
     *
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
        return new DataSourceTransactionManager(routingDataSource());
    }

}