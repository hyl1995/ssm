package com.hyl.core.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ObjectUtil {
    public static final String[] customProperties = new String[]{"id", "companyId", "departmentId", "createTime", "updateTime", "creatorId", "updatorId"};

    public static <R, T> R buildSingle(T source, Class<R> target) {
        try {
            R tartgetObject = target.newInstance();
            BeanUtils.copyProperties(source, tartgetObject);
            return tartgetObject;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <R, T> List<R> buildList(List<T> source, Class<R> target) {
        try {
            List<R> targetList = new ArrayList<>();
            for (T object : source) {
                R tartgetObject = target.newInstance();
                BeanUtils.copyProperties(object, tartgetObject);
                targetList.add(tartgetObject);
            }
            return targetList;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <R, T> R buildIgnorePropertyByCustom(T source, Class<R> target) {
        try {
            R tartgetObject = target.newInstance();
            buildIgnorePropertyByCustom(source, tartgetObject);
            return tartgetObject;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 过滤固定属性
     * @param source
     * @param tartgetObject
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> R buildIgnorePropertyByCustom(T source, R tartgetObject) {
        BeanUtils.copyProperties(source, tartgetObject, customProperties);
        return tartgetObject;
    }

    /**
     * 过滤自定义属性
     * @param source
     * @param tartgetObject
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> R buildIgnorePropertyByCustomize(T source, R tartgetObject,boolean isCustom, String...customize) {
        if (isCustom) {
            customize = Stream.concat(Stream.of(customize), Stream.of(customProperties)).toArray(String[]::new);
        }
        BeanUtils.copyProperties(source, tartgetObject, customize);
        return tartgetObject;
    }

    public static void main(String[] args) {
        String[] customize = {"sadsa","234234","43264dfgfdg434"};
        customize = Stream.concat(Stream.of(customize), Stream.of(customProperties)).toArray(String[]::new);
        for (String s : customize) {
            System.out.println(s.toString());
        }
    }
}
