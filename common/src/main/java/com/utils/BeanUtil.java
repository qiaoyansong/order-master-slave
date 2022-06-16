package com.utils;

import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/27 11:55 上午
 * description：
 */
public class BeanUtil {

    private BeanUtil() {

    }

    /**
     * 将对象装换为map
     *
     * @param bean 对象
     * @return 对象Map
     */
    public static Map<String, String> beanToMap(Object bean) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return BeanUtils.describe(bean);
    }

    /**
     * 将map转换成为对象
     *
     * @param dest 目标对象
     * @param src  原Map
     * @return
     */
    public static <T> T mapToBean(T dest, Map<String, String> src) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.populate(dest, src);
        return dest;
    }

    /**
     * 将对象装换为map
     *
     * @param bean 对象
     * @return 对象Map
     */
    public static <T> Map<String, Object> bean2Map(T bean) {
        Map<String, Object> copy = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.keySet().forEach(key -> copy.put(key.toString(), beanMap.get(key)));
        }
        return copy;
    }
}
