package 剑指offer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 公司有编号为 1 到 n 的 n 个工程师，给你两个数组 speed 和 efficiency ，其中 speed[i] 和 efficiency[i] 分别代表第
 * i 位工程师的速度和效率。请你返回由最多 k 个工程师组成的 ​​​​​​最大团队表现值 ，由于答案可能很大，请你返回结果对 10^9 + 7 取余后的结果。
 * <p>
 * 团队表现值 的定义为：一个团队中「所有工程师速度的和」乘以他们「效率值中的最小值」。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-performance-of-a-team 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最大的团队表现值 {
    public static void main(String[] args) {

    }
}

class Solution {

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] items = new int[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = speed[i];
            items[i][1] = efficiency[i];
        }
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });

        //优先级队列每次poll出来的是当前最小speed的员工
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        long res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (queue.size() > k - 1) {
                sum -= queue.poll(); //优先级队列里面存的是效率大于等于i，且speed最小的员工的speed
            }
            //items已经按效率从大道小排序过了，所以items[i]是效率第i的
            res = Math.max(res, (sum + items[i][0]) * items[i][1]); //这个max永远
            queue.add(items[i][0]);
            sum += items[i][0];
        }
        return (int)(res % ((int)1e9 + 7));
    }
}

