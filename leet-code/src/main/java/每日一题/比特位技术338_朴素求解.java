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
public class 比特位技术338_朴素求解 {

    public static void main(String[] args) {
        countBits(3);
    }

    public static int[] countBits(int num) {

        int ans[] = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int bits = 0;
            int x = i;
            while (x > 0) {
                if ((x & 1) == 1) { bits++; }
                x = x >> 1;
            }
            ans[i] = bits;
            System.out.println("bits(" + i + ")=" + bits);
        }
        return ans;

        //hint1: You should make use of what you have produced already.
        //hint2: Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on.
        //And try to generate new range from previous.
        //hint3: Or does the odd/even status of the number help you in calculating the number of 1s?

        //{0,1,2,3,4,5,6,7,8} --> {0, 1, 1, 2, 1, 2, 2, 3, 1}
        //从x -> 2^N 进位导致1减少变为1位
        //number_i = sum(p*2^n), p=0/1, n:{0~31}
        //二进制，将数字拆分为2进制表示法：
        //0 = 0 --> 0
        //1 = 1 --> 1
        //1+1 = 2 --> 1
        //1+2=3 --> 2
        //2+2=4=4 --> 1
        //2+3=5 --> 2
        //2+4=6 --> 2
        //1+2+4=7 --> 3
        //4+4=8 --> 1

        //可以看成由1+1=2,2+2=4, 4+4=8构成的树形结构，一旦 a + b=c =2^n 表示向根节点n升级

        //1）升级时,c=2^n, (1，2，4，8)所以：bits(c) = 1
        //如果没升级：bits(c) = bits(a) + bits(b)
        //按2^n分隔数据区间：[2-3][4-7] [8-15] [16-31]...

        //2）观察区间内：偶数->奇数,bits加1，奇数->偶数时bits变化比较复杂：连续进位bits减少 or 单进位bits不变

    }
}
