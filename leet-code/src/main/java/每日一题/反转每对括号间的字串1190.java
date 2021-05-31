package 每日一题;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 1190. 反转每对括号间的子串
 * <p>
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * <p>
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * <p>
 * 注意，您的结果中 不应 包含任何括号。
 */
public class 反转每对括号间的字串1190 {

    /* 使用stack模拟括号嵌套，使用string builder来构造一层括号内的字符
    遍历字符串
    - 遇到左括号，push string into stack
    - 遇到右括号，reverse string 然后拼接stack的字符串
    - 正常情况： add to string builder
    */
    public static String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (c == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        /**
         e.g.  (ed(et(oc))el)

         on ( push stack: (
         on ed and stack not empty push stack: (ed
         on ( push stack: (ed(
         on et push stack: (ed(et
         on ( push stack: (ed(et(oc
         on ) pop to reverse stack:  (ed(et co
         on ) reverse : (ed oc te
         on el and stack not empty push stack: (ed oc te el
         on ) reverse stack: leetcode

         on end output stack or queue.
         */
        String s = "(ed(et(oc))el)";
//        String s = "a(bcdefghijkl(mno)p)q";
        System.out.println(reverseParentheses(s));
    }
}
