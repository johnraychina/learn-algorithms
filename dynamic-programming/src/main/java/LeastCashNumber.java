/**
 * https://www.zhihu.com/question/23995189
 *
 * @author Zhang Yi
 */
public class LeastCashNumber {
    public static void main(String[] args) {
        // 纸币面额：1元，5元，11元
        // 求f[n]: 凑齐金额n，使用的最少纸币张数
        // f[n] = min{ f(n-1), f(n-5), f(n-11) } + 1
        //bottomUpSolution();

        topDownSolution();
    }

    //从底向上构建状态转移
    private static void bottomUpSolution() {
        int[] f = new int[100];
        int n = 20; //n < f.length
        int cost = 0;
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            cost = Integer.MAX_VALUE;
            if (i - 1 >= 0) { cost = Math.min(cost, f[i - 1] + 1); }
            if (i - 5 >= 0) { cost = Math.min(cost, f[i - 5] + 1); }
            if (i - 11 >= 0) { cost = Math.min(cost, f[i - 11] + 1); }
            f[i] = cost;
            System.out.printf("f[%d]=%d\n", i, cost);
        }
    }

    //递归式，自顶向下构建状态转移
    private static void topDownSolution() {
        // 纸币面额：1元，5元，11元
        // 求f[n]: 凑齐金额n，使用的最少纸币张数
        // f[n] = min{ f(n-1), f(n-5), f(n-11) } + 1
        int[] f = new int[100];
        int n = 20; //n < f.length
        f[0] = 0; //初始条件
        int cost = recursiveMin(f, n);
    }

    private static int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }

    private static int recursiveMin(int[] f, int n) {
        int cost;
        if (n > 0) {
            if (n == 1 || n == 5 || n == 11) {
                cost = 1; //记录下来中间结果
            } else {
                //拆解为子问题，子问题尽可能复用前面的结果
                int f_n1 = (n > 1 && f[n - 1] > 0) ? f[n - 1] : recursiveMin(f, n - 1);
                int f_n5 = (n > 5 && f[n - 5] > 0) ? f[n - 5] : recursiveMin(f, n - 5);
                int f_n11 = (n > 11 && f[n - 11] > 0) ? f[n - 1] : recursiveMin(f, n - 11);

                cost = min(f_n1, f_n5, f_n11) + 1;
                System.out.printf("f[%d]=%d\n", n, cost);
            }
        } else {
            return Integer.MAX_VALUE; //负数，非可行解，返回最大值，保证被min拒绝掉，避免死循环导致Stack Overflow。
        }

        f[n] = cost; //记录下来中间结果
        return f[n];
    }

}
