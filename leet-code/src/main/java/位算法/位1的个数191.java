package 位算法;

/**
 * @author Zhang Yi
 */
public class 位1的个数191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {

        int ans = 0;
        while (n != 0) {
            n = n & (n - 1); //将最低位清零，如果不为0
            ans++;
        }

        return ans;
    }
}
