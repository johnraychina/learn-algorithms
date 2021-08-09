package com.zhangyi;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author 张义 johnraychina@163.com
 */
public class Test {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {



        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(3));
        System.out.println(tableSizeFor(4));
        System.out.println(tableSizeFor(7));

        //hash map Node<K,V>结构可以存null value
        HashMap<String, Object> map = new HashMap<>();
        map.put("123", null);

        //linked list Node<E>结构可以存null value
        LinkedList<Object> list = new LinkedList<>();
        list.add(null);
        list.add(null);



    }

    public static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
