package 位算法;

/**
 * @author Zhang Yi
 */
public class 比特位计数338 {
    public int[] countBits(int num) {
        int ans[] = new int[num + 1];
        ans[0] = 0;
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }
}
