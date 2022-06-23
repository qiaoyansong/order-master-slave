package com.annotation;

import java.lang.annotation.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/6/20 7:21 下午
 * description：数据库选择数据源
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SelectDataSource {

    /**
     * 是否读主库
     */
    boolean isMaster() default true;

    /**
     * 数据源名字，未填则随机选择
     */
    String dataSourceName();

}
