package 分而治之;

/**
 * @author Zhang Yi
 */
public class x的n次方50 {

    public static void main(String[] args) {
        System.out.println(myPow(2, -2147483648));
    }

    public static double myPow(double x, int n) {
        long N = n; //极端情况：n= Integer.MIN_VALUE
        return N >= 0 ? myPow_loop(x, N) : 1.0 / myPow_loop(x, -N);
    }

    public static double myPow_loop(double x, long n) {
        double ans = 1.0;
        //结束条件: n == 0
        double x_contribute = x;
        while (n > 0) {
            //最低位为1，对应 , x^n = x^(1 +...2^K),  令 x_contribute = 2^k, k从1迭代到K
            if (n % 2 == 1) {
                ans *= x_contribute;
            }
            x_contribute *= x_contribute;
            //下次循环: n = n / 2
            n >>= 1;
        }
        return ans;
    }

    public static double myPow_recursion(double x, int n) {
        if (n == 0) { //递归结束条件
            return 1;
        }
        //拆分
        //n为正数则乘x，负数则乘1/x
        //如果n是奇数，整除后要多乘一下
        double half = myPow_recursion(x, n / 2);
        int remain = n % 2;
        if (remain == 0) {
            return half * half;
        } else {
            return remain > 0 ? half * half * x : half * half * (1 / x);
        }
    }

}
