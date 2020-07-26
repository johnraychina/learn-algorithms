package com.zhangyi.algorithm.sort.priority_queue;

public class Pair<K, V> {

    private static final long serialVersionUID = 1L;

    private K key;

    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String toString() {
        return key.toString() + ":" + value.toString();
    }
}
