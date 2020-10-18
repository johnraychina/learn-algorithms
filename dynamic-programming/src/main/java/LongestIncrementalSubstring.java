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

    public static int lengthOfLIS(int[] nums) {
        //问题：最长上升子序列的长度
        //状态：以数组元素a[x]结尾的上升子序列长度为f(x), 问题就是求解max{f(x)}
        //子问题：如果以a[x]结尾的序列，前一个是a[p]，且a[p] < a[x]，那么子问题就是求解max{f(p)}
        //状态转移方程：f(x) = max{f(p)} + 1 , if a[p] < a[x]
        //初始条件：f(0~n-1) = 1，子串只包含自己

        if (nums.length == 0) {
            return 0;
        }

        //[1,3,6,7,9,4,10,5,6]
        int[] dp = new int[nums.length];
        int totalMax = 0; //整体问题最大值
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1; //子问题dp[i]：以i结尾的最长上升子序列的长度初始为1（只包含num[i]自己)
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); //求解子问题dp[i]=max{dp[j] + 1}
                }
            }
            //整体问题 = max{ dp[i] }
            totalMax = Math.max(totalMax, dp[i]);
        }

        return totalMax;
    }

    public static void main(String[] args) {

        //int nums[] = {10, 9, 2, 5, 3, 7, 101, 18};
        int nums[] = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(nums));

    }

    public static void solution() {
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
