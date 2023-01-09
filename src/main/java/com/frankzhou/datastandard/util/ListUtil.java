package com.frankzhou.datastandard.util;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表转换工具类
 */
public class ListUtil<T> {

    /**
     * 将单个对象转换成列表
     */
    public static <T> List<T> singletonList(T item) {
        List<T> resList = new ArrayList<>();
        resList.add(item);
        return resList;
    }

    /**
     * 列表类型转换-->用于DO、DTO、VO的转换
     *
     */
    public static <E, T> List<T> listConvert(List<E> source,Class<T> clazz) {
        if (CollectionUtil.isEmpty(source)) {
            return Lists.newArrayList();
        }

        List<T> resList = new ArrayList<>(source.size());
        source.forEach(s -> {
            T t = BeanUtils.instantiateClass(clazz);
            BeanUtils.copyProperties(s,t);
            resList.add(t);
        });

        return resList;
    }

}
