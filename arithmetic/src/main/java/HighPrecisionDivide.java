/**
 * <pre>
 * 目标：只用整数方式求一定精度的 a/b
 * 分析：先求出来1/b，再乘以a即可
 * 1/b整除变为floor(1/b)会是0，精度丢失了，怎么办呢？
 * 我们可以 先乘以一个比较大的R(R=2^k，2的k次方是为了后续位移方便)然后向下取整得到 floor(R/b)
 * k就是我们可以调整的2进制精度参数了，最后想得到真实值时 位移<<k即可得到。
 * 利用牛顿迭代法求解R/b
 *      另 x = R/b
 *      f(x) = 1/x - b/R = 0
 *      迭代公式: x_i+1 = 2x_i - x_i^2 * b/R
 * 基本思路：将除法转化为加减法、乘法、位移 等CPU支持的简单操作
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class HighPrecisionDivide {
    static int k = 16;
    static int R = 1 << k;//放大器，精度控制

    public static void main(String[] args) {
        oneBy(3); // 1/3
    }

    public static int oneBy(int b) {
        // 固定迭代次数，收敛速度=迭代次数的平方，即 Error_i+1 = − Error_i^2
        // 可以改成动态迭代error < epsilon
        int x = R / b; //由于只能用整除，所以x不能相对R太小，否则第二项会被忽略
        for (int i = 0; i < 4; i++) {
            x = (x << 1) - ((b * x * x) / R);

            check(i, b, x);
        }
        return x;
    }

    public static void check(int i, int b, int x) {
        System.out.printf("#%d iteration: %d / %d = %d \n", i, R, b, x);
    }
}
