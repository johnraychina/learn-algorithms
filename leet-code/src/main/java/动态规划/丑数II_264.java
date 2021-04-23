package 动态规划;

/**
 * @author Zhang Yi
 */
public class 丑数II_264 {

    public static int nthUglyNumber(int n) {
        //目标：第n个丑数数值
        //状态： 第i个数
        //另外，再定义p2=以2为质因数的前一个丑数, p3=以3为质因数的前一个丑数，p5=以5为质因数的前一个丑数
        //状态转移：dp[i] = min( dp[p2]*2, dp[p3]*3, dp[p5]*5)
        int dp[] = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i < n + 1; i++) {
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int number = nthUglyNumber(10);
        System.out.println(number == 12);
    }
}
