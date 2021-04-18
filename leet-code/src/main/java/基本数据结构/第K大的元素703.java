package 基本数据结构;

import java.util.PriorityQueue;

/**
 * @author Zhang Yi
 */
public class 第K大的元素703 {
    public static void main(String[] args) {

        KthLargest kl = new KthLargest(3, new int[] {4, 5, 8, 2});
        int[] input = new int[] {3, 5, 10, 9, 4};
        for (int val : input) {
            System.out.println(kl.add(val));
        }
    }
}

class KthLargest {
    private final int k;
    private PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
