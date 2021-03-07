package 每日一题;

/**
 * <pre>
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 比特位技术338_奇数偶数 {

    public static void main(String[] args) {
        countBits(7);
    }

    public static int[] countBits(int num) {

        int ans[] = new int[num + 1];

        //hint1: You should make use of what you have produced already.
        //hint2: Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on.
        //And try to generate new range from previous.
        //hint3: Or does the odd/even status of the number help you in calculating the number of 1s?

        ans[0] = 0;
        for (int i = 1; i < num + 1; i++) {
            //奇数countBits = 偶数countBits + 1
            if (i % 2 == 1) {
                ans[i] = ans[i - 1] + 1;
            }
            //偶数countBits = (偶数/2)bits
            else {
                ans[i] = ans[i / 2];
            }
            System.out.println("bits(" + i + ")=" + ans[i]);
        }

        return ans;
    }
}
