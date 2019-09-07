package com.zhangyi.algorithm.sort.priority_queue;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.zhangyi.algorithm.sort.DatasetUtils;

import static com.zhangyi.algorithm.sort.Utils.exchange;
import static com.zhangyi.algorithm.sort.Utils.less;

/**
 * Priority queue that
 *
 * @author johnraychina@163.com
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private int N;
    private Key[] array;

    MaxPQ(int capacity) {
        this.N = 0;
        this.array = (Key[])new Comparable[capacity + 1];
    }

    void insert(Key v) {
        if (N >= array.length) {
            delMax();
        }

        //insert the node to the last of array, then swim
        N++;            //move pointer to the right beside the last node(the first available position)

        array[N] = v;   //insert the value to the right of the last node and then become the last node itself
        swim(N);        //restore the order by swim(N)
    }

    //delete and return the maximum element
    Key delMax() {
        Key max = array[1];     //the maximum element(first node)
        array[1] = array[N];    //move the last node to the first node
        array[N] = null;        //detach the pointer
        N--;
        sink(1);              //restore the order by sink(1)

        return max;
    }

    //上浮
    private void swim(int i) {

        //i/2: parent of target node i

        while (i > 1 && less(array, i / 2, i)) {
            //exchange parent and i, if array[i/2] < array[i]
            exchange(array, i / 2, i);

            //reset index to keep track of the target node
            i = i / 2;
        }
    }

    //上浮
    //private void swim(int i) {
    //
    //    int p = i / 2; //p: parent of target node i
    //
    //    while (p >= 1 && less(array, p, i)) {
    //
    //        //exchange p and i, if array[p] < array[i]
    //        exchange(array, p, i);
    //
    //        //reset index to keep track of the target node: i as p, p as i/2
    //        i = p;
    //        p = i / 2;
    //    }
    //}

    //下潜

    private void sink(int i) {

        while (i * 2 <= N) {

            int c = i * 2; //the bigger child
            if (c + 1 <= N && less(array, c, c + 1)) {
                c += 1;
            }

            if (less(array, i, c)) {
                //exchange i with c, if array[i] < array[c]
                exchange(array, i, c);
                //reset i as c
                i = c;
            } else {
                break;
            }
        }
    }

    //private void sink(int i) {
    //
    //    //l: left child of target node i
    //    //r: right child of target node i
    //    int l = i * 2, r = l + 1;
    //
    //    while (l <= N) {
    //
    //        int c = l; //the bigger child
    //        if (less(array, l, r)) {
    //            c = r;
    //        }
    //
    //        if (less(array, i, c)) {
    //            //exchange i with c, if array[i] < array[c]
    //            exchange(array, i, c);
    //            //reset i as c
    //            i = c;
    //        } else {
    //            break;
    //        }
    //
    //        l = i * 2;
    //        r = l + 1;
    //    }
    //}

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }

    public static void main(String[] args) throws IOException {

        List<Pair<String, BigDecimal>> transactions = DatasetUtils.loadTransactions(
            Files.readAllLines(Paths.get("transactions.txt")));

        //just keep top 3 transactions
        MaxPQ queue = new MaxPQ(3);

    }


}
