package 每日一题;

import java.util.Stack;

/**
 * <pre>
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 * @hint dp
 * @hint stack
 * @author Zhang Yi
 */
public class 最长有效括号32 {

    public static void main(String[] args) {
        int len = new 最长有效括号32().longestValidParentheses_dp("()(())");
        System.out.println(len);
    }

    //思路1：暴力解法 O(N^3)
    //思路2：看到前后匹配就想到用stack，然而，简单的stack无法处理中间不连续有断开，需要重置的情况
    //思路3：看到最长序列就想到用动态规划或者贪心算法
    // () (  () (())
    public static int longestValidParentheses_dp(String s) {
        //target 最长有效括号
        //state 开始位置，结束位置，长度
        //sub-problem 子问题: dp[i]是以i结尾的最长有效括号长度
        //有效的字串以)结尾，以(结尾的dp值一定是0，我们只需要求解)在dp数组中对应位置的值。
        //state transfer  状态转移:
        // 形如 “……()” if s[i-1]=( & s[i]=) then dp[i] = dp[i-2] + 2
        // 形如 “……((……))”: s[i - dp[i - 1] - 1] = (
        // 那么内部字串基础上加二： dp[i-1] + 2
        // 然后再加前面的字串长度：dp[i - dp[i-1] - 2]
        //dp[i]= dp[i−1] + 2 + dp[i − dp[i−1] − 2]
        int maxLen = 0;
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            //todo
        }

        return 0;
    }



    public static int longestValidParentheses_brutal(String s) {
        int len = s.length();
        len = len & (len - 1); //括号成对出现，处理成偶数

        //try each length
        while (len >= 2) {
            //starting from i, check if it is valid
            for (int i = 0; i <= s.length() - len; i++) {
                if (isValid(s, i, len)) {
                    return len;
                }
            }

            //next
            len -= 2;
        }

        return len;
    }

    public static boolean isValid(String s, int i, int len) {
        Stack<Character> stack = new Stack<>();
        //push j in [i~i+len)
        for (int j = i; j < i + len; j++) {
            char c = s.charAt(j);
            if (c == '(') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
