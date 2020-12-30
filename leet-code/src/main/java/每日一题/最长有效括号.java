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
 *
 * @author Zhang Yi
 */
public class 最长有效括号 {

    public static void main(String[] args) {
        int len = new 最长有效括号().longestValidParentheses("()(()");
        System.out.println(len);
    }

    //思路1：看到前后匹配就想到用stack
    public int longestValidParentheses(String s) {
        Stack<Character> leftParenthesisStack = new Stack<>();
        int maxLen = 0;
        int len = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftParenthesisStack.push(c);
            } else if (c == ')') {
                if (!leftParenthesisStack.empty()) {
                    leftParenthesisStack.pop();
                    len += 2;
                    maxLen = Math.max(maxLen, len);
                } else {
                    //没有左括号匹配则说明是新的一组括号，保留maxLen后，需要重新计算
                    len = 0;
                    leftParenthesisStack = new Stack<>();
                }
            }
        }

        return maxLen;
    }

    //思路2：看到最长序列就想到用动态规划或者贪心算法
}
