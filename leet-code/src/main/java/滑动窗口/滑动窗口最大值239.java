package 滑动窗口;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author Zhang Yi
 */
public class 滑动窗口最大值239 {

    public static void main(String[] args) {
        int[] ans = maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3);

        for (int val : ans) {
            System.out.println(val);
        }
    }

    //利用双端队列来做
    //利用 max priority queue来做

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) { return null; }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        int[] ans = new int[nums.length - k + 1];
        //初始填充: [0:k-1]
        while (queue.size() < k) {
            queue.offer(new int[] {nums[queue.size()], queue.size()});
        }
        ans[0] = queue.peek()[0];

        //遍历nums，滑动窗口
        for (int i = k; i < nums.length; i++) {
            //滑动:先删除上个元素 然后 添加
            //fixme 这里remove是O(N)的时间复杂度，超出时间限制，所以要将index也记录到queue中
            //queue.remove(nums[i - k]);
            //queue.offer(nums[i]);
            queue.offer(new int[] {nums[i], i});

            //删除不在窗口内的最大元素，其他较小的元素在窗口内也不影响，所以不用管
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }

            //获得最大值
            ans[i - k + 1] = queue.peek()[0];
        }
        return ans;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {

        //维护一个deque:
        // 保证 左边放当前最大值即可
        //add时 如果当前元素比左边的大，while删除左边的（比较大又比较新的元素会留到最后）
        //取最大值后，先要判断是否在窗口内
        //为了判断新老元素，但是由于元素会移动，我们需要记忆位置

        LinkedList<Integer> idxQueue = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {

            while (!idxQueue.isEmpty() && nums[idxQueue.peekLast()] < nums[i]) {
                idxQueue.pollLast();
            }

            idxQueue.add(i);

            //判断当前队首是否有效
            while (idxQueue.peek() <= i - k) {
                idxQueue.poll();
            }

            if (i >= k - 1) {
                ans[i - k + 1] = nums[idxQueue.peekFirst()];
            }
        }

        return ans;
    }
}
