package 每日一题;

/**
 * <pre>
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @hint
 * </pre>
 */
public class 整数反转7 {

    public static int reverse(int x) {
        //        123 -> 321
        //        -123 -> -321

        int y = 0;
        int L = Integer.MIN_VALUE / 10;
        int R = Integer.MAX_VALUE / 10;
        while (x != 0) {
            // 反转超过范围
            if (y < L || y > R) {
                return 0;
            }
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
    }
}
