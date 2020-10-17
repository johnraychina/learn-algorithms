import java.util.Arrays;

/**
 * https://www.zhihu.com/question/23995189
 * <pre>
 * 最长上升子序列（LIS）问题：给定长度为n的序列a，从a中抽取出一个子序列，这个子序列需要单调递增。
 * 问最长的上升子序列（LIS）的长度。
 *
 * e.g. 1,5,3,4,6,9,7,8的LIS为1,3,4,6,7,8，长度为6。
 *
 * 如何设计状态（我是谁）？
 * 我们记f(x)为以x结尾的LIS长度，那么答案就是 max{f(x)}.
 *
 * 状态x从哪里推过来（我从哪里来）？
 * 考虑比x小的每一个p：如果a_x > a_p，那么f(x)可以取f(p)+1.
 *
 * 解释：我们把a_x接在a_p的后面，肯定能构造一个以a_x结尾的上升子序列，长度比以a_p结尾的LIS大1.
 * 那么，我们可以写出状态转移方程了：
 *   f(x) = Max_{x>p, a_x>a_p} {f(p)} + 1
 *
 * 至此解决问题。
 * 两层for循环，复杂度 O(N^2).
 * </pre>
 *
 * @author Zhang Yi
 */
public class LongestIncrementalSubstring {
    public static void main(String[] args) {
        int[] a = {1, 5, 3, 4, 6, 9, 7, 8};
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
        System.out.println();

        int[] f = new int[a.length];//f[i] 代表以a[i]结尾的最长上升子序列的长度
        Arrays.fill(f, 1); //初始条件设置，以元素a[i]结尾的最长上升子序列长度，统一为1（只有自己）

        //f[i]依赖dp[i-1]的值，所以先要将前面的中间结果算出来并记忆
        for (int x = 1; x < a.length; x++) {
            int max_fp = 0;
            for (int p = 0; p < x; p++) {
                if (a[x] > a[p]) {
                    //满足状态转移条件
                    max_fp = Math.max(f[p], max_fp);
                }
            }

            f[x] = max_fp + 1;
            System.out.printf("f[%d]=%d \n", x, f[x]);
        }

    }

}
