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
         //dp 状态定义：以)结尾的最长有效括号长度
        return -1;
    }

}
