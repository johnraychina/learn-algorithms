package shopee;

/**
 *
 作者：songhouhou
 链接：https://leetcode-cn.com/problems/simplified-fractions/solution/javajie-fa-tong-guo-shuang-zhong-xun-hua-o3yt/
 来源：力扣（LeetCode）
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 最大公约数 {
    public static void main(String[] args) {

    }

    public int gcd2(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
