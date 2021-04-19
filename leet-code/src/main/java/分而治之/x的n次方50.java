package 分而治之;

/**
 * @author Zhang Yi
 */
public class x的n次方50 {

    public static void main(String[] args) {
        System.out.println(myPow_recursion(2, 20));
    }

    public static double myPow_loop(double x, int n) {

        //结束条件: n == 0
        while (n != 0) {

            //下次循环: n = n / 2
            n >>= 1;
        }

        //todo
        return 0;

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
