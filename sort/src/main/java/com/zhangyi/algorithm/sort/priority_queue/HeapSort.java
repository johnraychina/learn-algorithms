package com.zhangyi.algorithm.sort.priority_queue;

import static com.zhangyi.algorithm.sort.Utils.exchange;
import static com.zhangyi.algorithm.sort.Utils.less;
import static com.zhangyi.algorithm.sort.Utils.show;

/**
 * <pre>
 * Heap sort: Basic plan for in-place sort
 * - create max-heap with all N keys
 * - repeatedly remove the maximum key.
 *
 * we assume array entries are index from 1 to N
 * </pre>
 * <pre>
 * 堆的本质是层有序，同层之间是不保证顺序的：
 * 以最大堆为例子：根节点最大，越底层越小，同层不保证顺序
 * 数组实现array的话，就会像这样：
 * array[1] >= array[Level2_start ~ Level2_end] >= array[Level3_start ~ Level3_end]..
 *
 * 总结：先自底向上构造最大堆，然后再sortDown从上到下做原地排序得到有序数组
 *
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class HeapSort {

    public static void main(String[] args) {
        String[] array = "- s o r t e x a m p l e".split(" ");
        sort(array);
        System.out.println(array);
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        heap(array);
        sortDown(array);
    }

    //First pass: Heap construction
    //Build heap using bottom-up method.
    //heap 保证最大值总是在array[1]
    //自底向上构建：从最下面的非叶子节点N/2开始，向上遍历到根节点1，执行sink
    public static <T extends Comparable<T>> void heap(T[] array) {
        int N = array.length - 1;
        for (int k = N / 2; k >= 1; k--) {
            sink(array, k, N);
            show(array);
        }
    }

    public static <T extends Comparable<T>> void sink(T[] array, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(array, j, j + 1)) { j++; }
            if (!less(array, k, j)) { break; }
            exchange(array, k, j);
            k = j;
        }
    }

    //有了最大堆之后，再通过下面的方法做原地排序：
    // 由于heap 保证最大值总是在array[1]，将最大值exchange到后面
    // 再通过sink构造一个堆（堆的size缩小1）
    // Second pass: Sort down
    // remove the maximum item, one at a time.
    // leave in array instead of nulling out
    public static <T extends Comparable<T>> void sortDown(T[] array) {
        int N = array.length - 1;
        while (N > 1) {
            exchange(array, 1, N--);
            sink(array, 1, N);
            show(array);
        }
    }

}
