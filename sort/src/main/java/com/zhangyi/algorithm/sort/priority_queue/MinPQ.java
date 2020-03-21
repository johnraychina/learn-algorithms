package com.zhangyi.algorithm.sort.priority_queue;

/**
 *
 * @author 张义 johnraychina@163.com
 */
public class MinPQ<Key extends Comparable<Key>> {
    private final int min;
    private Key[] array;

    MinPQ() {
        min = 100;
    }

    MinPQ(int max) {
        this.min = max;
    }

    MinPQ(Key[] array) {
        min = array.length;
        //todo
    }

    void insert(Key v) {
        //todo
    }

    //delete and return the minimum element
    Key delMin() {
        return null; //todo
    }

    boolean isEmpty() {
        return true;//todo
    }

    int size() {
        return 0; //todo
    }
}
