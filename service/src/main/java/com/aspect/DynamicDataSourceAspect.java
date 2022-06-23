package com.aspect;

import com.annotation.SelectDataSource;
import com.config.DataSourceConfig;
import com.config.DataSourceContextHolder;
import com.constant.CommonConstant;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/6/20 7:04 下午
 * description：数据源切面
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    private ImmutableMap<String, Supplier<String>> executorMap = ImmutableMap.of(
            CommonConstant.MASTER, () -> chooseMasterDataSource(),
            CommonConstant.SLAVE, () -> chooseMasterDataSource()
    );


    /**
     * 在service层方法获取datasource对象之前，在切面中指定当前线程数据源slave
     */
    @Before(value = "execution(* *(..)) && @annotation(selectDataSource)")
    public void before(JoinPoint point, SelectDataSource selectDataSource) {
        log.info(point.getSignature().getName() + "走从库");
        DataSourceContextHolder.setTargetDataSource(DataSourceContextHolder.SLAVE);
    }

    @After(value = "execution(* *(..)) && @annotation(selectDataSource)")
    public void restoreDataSource(JoinPoint point, SelectDataSource selectDataSource) {
        log.info(point.getSignature().getName() + "清除数据源");
        //方法执行完后清除数据源。
        DataSourceContextHolder.removeTargetDataSource();
    }


    /**
     * 选择master库
     */
    private String chooseMasterDataSource() {
        Map<String, Map<String, String>> masters = dataSourceConfig.getMasters();
        Set<String> dsName = masters.keySet();
        return dsName.
    }

    private String chooseSlaveDataSource() {
        Map<String, Map<String, String>> masters = dataSourceConfig.getSlaves();
        Set<String> dsName = masters.keySet();
    }

}
