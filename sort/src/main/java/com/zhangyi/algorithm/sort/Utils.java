package com.zhangyi.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class Utils {
    public static <T> void exchange(T[] a, int i, int indexOfMin) {
        T temp = a[i];
        a[i] = a[indexOfMin];
        a[indexOfMin] = temp;
    }

    public static <T> void show(T[] array) {
        System.out.println(JSON.toJSONString(array));
    }
}
