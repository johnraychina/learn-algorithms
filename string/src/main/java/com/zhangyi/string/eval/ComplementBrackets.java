package com.zhangyi.string.eval;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 * 1.3.9 补全左括号
 *
 * 输入：1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * 输出：( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *
 *
 * 分析：
 * 1、遇到右括号，触发回退，补充左括号，所以需要用到栈
 * 2、多层括号就很难判断往左回溯到哪个位置来补充左括号了，
 * 所以每次遇到右括号，都pop 3个字符串（右表达式，操作符，左表达式），再加上左括号，
 * 将子表达式合并为一个字符串 push回stack（看做一个整体，或者一颗子树的根节点）
 *
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class ComplementBrackets {
    public static String complement(String[] tokens) {

        Stack<String> stack = new Stack<>();
        List<String> out = new LinkedList<>();
        for (String token : tokens) {
            if (token.equals(")")) {
                String right = stack.pop();
                String operator = stack.pop();
                String left = stack.pop();
                String subExpr = String.join(" ", "(", left, operator, right, ")");
                stack.push(subExpr);
            } else {
                stack.push(token);
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )".split("\\s+");
        String complement = complement(tokens);
        System.out.println(complement);
    }
}
