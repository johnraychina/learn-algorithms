package 每日一题;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <pre>
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
 * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 下一个更大元素503_栈 {

    //https://leetcode-cn.com/problems/next-greater-element-ii/solution/cong-po-su-jie-fa-de-jiao-du-qu-li-jie-d-trht/
    public int[] nextGreaterElements(int[] nums) {

        int N = nums.length;
        int[] ans = new int[N];

        //默认-1
        Arrays.fill(ans, -1);

        //暴力遍历：两个for循环
        //像1-2-3-4-5这样的递增序列，对每个元素只需要向后查找一次
        //像5-4-3-2-1这样的递减序列，对每个元素要一直向后查找，而且每次都向后查找，并没有复用查找。

        //优化：
        //将向后查找转换为向前查找：stack暂存 + 当前值判断（向前查找）
        //栈内存放的永远是还没更新答案的下标
        //每次将当前遍历到的下标存入栈内，
        //将当前下标存入栈内前，检查一下当前值是否能够作为栈内位置的答案（即成为栈内位置的「下一个更大的元素」），
        //如果可以，则将栈内下标弹出。
        Deque<Integer> stack = new LinkedList<>();

        //遍历循环数组：i作为循环数组下标
        //逻辑上：两个数组收尾相接，长度是2N，下标从0~2N-1
        //物理上：通过取模映射为原数组下标，这样就不需要copy数组了
        for (int i = 0; i < 2 * N - 1; i++) {

            while (!stack.isEmpty() && nums[i % N] > nums[stack.peek()]) {
                ans[stack.pop()] = nums[i % N];
            }
            stack.push(i % N);
        }

        return ans;
    }
}
