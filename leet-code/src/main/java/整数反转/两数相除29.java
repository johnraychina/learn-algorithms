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
    public static int divide(int a, int b) {
        long x = a, y = b;
        boolean isNeg = false;
        if ((x > 0 && y < 0) || (x < 0 && y > 0)) isNeg = true;
        if (x < 0) x = -x;
        if (y < 0) y = -y;
        long l = 0, r = x;
        while (l < r) {
            long mid = (l + r + 1 ) >> 1;
            if (mul(mid, y) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        long ans = isNeg ? -l : l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int) ans;
    }


    public static long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            // 2 * 7 = (10) * (111) = 10 * 1 + 10 * 10 + 10 * 100
            // update ans
            if ((k & 1) == 1) ans += a;

            // half k, double a
            k >>= 1;
            a <<= 1;
        }
        return ans;
    }

    //如果k很大，可以利用倍增，a^2 -> a^4, 达到log2(k)的时间复杂度。
    public static long power(long a, long k) {
        // 2^5 = (2 * 2) * (2 * 2)  * 2
        long ans = 1;
        while (k > 0) {
            //update ans
            if ((k & 1) == 1) ans = ans * a;

            //update k, a
            k >>= 1;
            a *= a;
        }

        return ans;
    }

    public static void main(String[] args) {

        System.out.println(divide(-2147483648, -1));
    }
}
