package com.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 4:51 下午
 * description：用于对象的深拷贝,要求源对象与目标对象的字段名一致
 */
public class BeanMapper {
    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    private BeanMapper() {
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        }
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     *
     */
    public static void copy(Object source, Object destinationObject) {
        if (destinationObject == null || source == null) {
            return;
        }
        dozer.map(source, destinationObject);
    }
}
