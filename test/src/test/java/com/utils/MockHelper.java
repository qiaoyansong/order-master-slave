package com.utils;

import java.lang.reflect.Field;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/26 10:25 上午
 * description：mock工具类
 */
public class MockHelper {

    /**
     * 递归查找父类属性
     *
     * @param clz
     * @param fieldName
     * @return
     */
    private static Field getDeclaredField(Class clz, String fieldName) {
        try {
            Field field = clz.getDeclaredField(fieldName);
            return field;
        } catch (NoSuchFieldException e) {
            if (clz.getSuperclass() != null) {
                return getDeclaredField(clz.getSuperclass(), fieldName);
            }
            return null;
        }
    }

    public static void mockField(Object object, String fieldName, Object mock) throws Exception {
        Field field = getDeclaredField(object.getClass(), fieldName);
        field.setAccessible(true);
        field.set(object, mock);
    }

}
