package 二分查找;

/**
 * @author Zhang Yi
 */
public class 求平方根69 {
    private static final double epsilonL = -1e-4;
    private static final double epsilonR = 1e-4;

    public static double mySqrt_binSearch(int n) {
        double right = n;
        double left = 0;

        int iter = 0;
        while (true) {
            iter++;

            double mid = (left + right) / 2;
            double error = n - mid * mid;

            if (epsilonL <= error && error <= epsilonR) {
                System.out.println("iter:" + iter);
                return mid;
            }
            if (error > epsilonR) {
                //太小，要扩大
                left = mid;
            } else {
                //太大，要缩小
                right = mid;
            }
        }
    }

    public static double mySqrt_log(int n) {
        return Math.exp(0.5 * Math.log(n));
    }

    //牛顿迭代法（泰勒级数简化）: f(x) = f(x_0) + f'(x_0)(x - x_0) => x = 1/2 * (x + n/x)
    public static double mySqrt_Newton(int n) {
        //从大到小迭代逼近平方根
        double x = n;
        int iter = 0;
        while (Math.abs(n - x * x) > epsilonR) {
            x = 0.5 * (x + n / x);
            iter++;
        }

        System.out.println("iter:" + iter);
        return x;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt_binSearch(4));
        System.out.println(mySqrt_binSearch(5));
        System.out.println(mySqrt_binSearch(9));

        System.out.println(mySqrt_log(4));
        System.out.println(mySqrt_log(5));
        System.out.println(mySqrt_log(9));

        System.out.println(mySqrt_Newton(4));
        System.out.println(mySqrt_Newton(5));
        System.out.println(mySqrt_Newton(9));
    }

}
