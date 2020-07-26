package com.zhangyi.algorithm.sort.table;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.zhangyi.algorithm.sort.priority_queue.Pair;

/**
 * <pre>
 * My Binary Search Symbol table.
 *
 * Data structure: Maintain an ordered array of key-value pairs.
 *
 * Class conventions:
 * - Null keys and values is not allowed
 * - Unlike java.util.Map, put a null value means delete the pair.
 * </pre>
 *
 * @author 张义 johnraychina@163.com
 * @see java.util.HashMap
 * @see java.util.LinkedHashMap
 * @see java.util.TreeMap
 * @see java.util.HashMap.TreeNode
 */
public class MyBinarySearchST<K extends Comparable<K>, V> {

    //internal array
    private Pair<K, V>[] array;

    //actual number of elements
    private int n;

    @SuppressWarnings("unchecked")
    public MyBinarySearchST(int capacity) {
        if (capacity < 1) {
            capacity = 1;
        }
        array = (Pair<K, V>[])new Pair[capacity];
    }

    public void put(K key, V value) {

        Objects.requireNonNull(key);
        if (value == null) {
            delete(key);
            return;
        }

        int i = rank(key); //number of left elements
        int j = n - i; //number of right elements

        //key already exists: just replace the value
        if (i < n && array[i].getKey().compareTo(key) == 0) {
            array[i].setValue(value);
            return;
        }

        Pair<K, V> newEntry = new Pair<>(key, value);

        // double the size and copy to new array if there is no enough space
        if (array.length <= n) {
            Pair<K, V>[] newArray = (Pair<K, V>[])new Pair[size() * 2];
            System.arraycopy(array, 0, newArray, 0, i);
            newArray[i] = newEntry;
            System.arraycopy(array, i, newArray, i + 1, j);

            //switch to new array
            array = newArray;
        } else {
            //move elements to right by 1 position
            System.arraycopy(array, i, array, i, j);
            array[i] = newEntry;
        }

        n++;
    }

    public void delete(K key) {
        Objects.requireNonNull(key);

        int i = rank(key);

        //the key is not in the table
        if (i == n || array[i].getKey().compareTo(key) != 0) {
            return;
        }

        // left, i, right
        // move the right part to left by 1 position
        int j = n - i - 1;
        System.arraycopy(array, i + 1, array, i, j);

        //avoid loitering
        array[n - 1] = null;

        //todo resize
        n--;
    }

    public V get(K key) {
        Objects.requireNonNull(key);
        if (isEmpty()) { return null; }
        int i = rank(key);
        if (i < n && array[i].getKey().compareTo(key) == 0) { return array[i].getValue(); } else { return null; }
    }

    //number of keys < key
    //when key is less than all: lo = 0, hi = -1
    //when key is bigger than all: lo = hi + 1, hi = n - 1
    private int rank(K key) {
        //binary search
        int lo = 0, hi = n - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(array[mid].getKey());
            if (cmp < 0) { hi = mid - 1; } else if (cmp > 0) { lo = mid + 1; } else { return mid; }
        }

        return lo;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return array.length;
    }

    public Iterator<Pair<K, V>> iterator() {
        return new TableIterator();
    }

    private class TableIterator implements Iterator<Pair<K, V>> {

        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < n;
        }

        @Override
        public Pair<K, V> next() {
            if (hasNext()) { return array[pos++]; } else { throw new NoSuchElementException(); }
        }
    }

    private static void showTable(MyBinarySearchST<String, String> table) {
        Iterator<Pair<String, String>> itr = table.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    public static void main(String[] args) {
        MyBinarySearchST<String, String> table = new MyBinarySearchST<>(2);
        table.contains("a");

        table.put("a", "123");
        table.put("a", "111");
        table.put("b", "222");
        table.put("c", "333");
        table.put("d", "444");
        table.put("e", "555");

        showTable(table);

        System.out.println("table.contains(\"a\") " + table.contains("a"));

        table.delete("a");

        showTable(table);

    }



}
