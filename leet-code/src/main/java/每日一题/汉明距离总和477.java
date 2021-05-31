package 每日一题;

/**
 * 477. 汉明距离总和
 *
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 *
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 */
public class 汉明距离总和477 {
    public static void main(String[] args) {
        System.out.println(totalHammingDistance(new int[] {4, 14, 2}));
    }
    public static int totalHammingDistance(int[] nums) {

        // 暴力计算，任意两个数汉明距离，C(n,2) = n * (n-1) / 2, 时间复杂度O(n^2)
        // 优化：减少重复计算，子问题的解能否复用？
        // 观察性质：
        // 1. 两次运算中 x^y,  a&(a-1)能否复用呢？ 使用记忆表 mem?
        // 2. 能否减少运算次数呢：我们只考虑各个元素在同一位置上的bit是否相同，
        // 不同bit位置互不影响，n个元素，假设第i位有c个为1，n-c个为0，那么汉明距离就是c * (n-c)
        // https://leetcode-cn.com/problems/total-hamming-distance/solution/yi-ming-ju-chi-zong-he-by-leetcode-solut-t0ev/
        int distance = 0;

        int n = nums.length;
        for (int i = 0; i < 30; i++) {
            int c = 0;
            for (int j = 0; j < n; j++) {
                c += (nums[j] >> i) & 1;
            }
            distance += c * (n - c);
        }
        return distance;
    }

}
