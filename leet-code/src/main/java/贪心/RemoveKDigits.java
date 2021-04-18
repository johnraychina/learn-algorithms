package 贪心;

/**
 * 402. Remove K Digits
 * <pre>
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 *
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        String ans = num;

        //todo 遍历
        //todo choose: 假设一个选择S={ p1..pk }，然后对这个选择进行优化为T
        //todo 优化过程：观察发现：选第一个下降的值交换，使得新的结果更小

        return ans;
    }
}
