package 整数反转;

/**
 * 29. 两数相除
 * <p>
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 * @hint
 */
public class 两数相除29 {
    public int divide(int dividend, int divisor) {

        //todo
        return 0;
    }

    public static long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }

    public static long power(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans *= a;
            k >>= 1;
            a *= a;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mul(2, 3));
    }
}
