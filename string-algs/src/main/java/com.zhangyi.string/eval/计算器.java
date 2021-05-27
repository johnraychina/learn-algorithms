package com.zhangyi.string.eval;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 *
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格。 整数除法仅保留整数部分。
 *
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/calculator-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 计算器 {
    public static void main(String[] args) {
        System.out.println(new 计算器().calculate("1-1+1"));
    }

    public int calculate(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();
        //由于输入是不规整的，需要用一个方法切分数字、符号
        List<String> tokens = split(s);

        //整体思路: Dijsktra调度场算法构建双stack，解决优先级问题：当后续操作符优先级低于栈顶操作符的优先级时，先计算当前栈
        //边界退出条件： token遍历完成
        //先拆分出来数字、符号
        for (String token : tokens) {
            if (isOperator(token)) {
                if (!operators.isEmpty() && priorityOf(token) < priorityOf(operators.peek())) {
                    calculateTop(numbers, operators);
                }
                operators.push(token);
            } else {
                numbers.push(Integer.parseInt(token.trim()));
            }
        }

        //计算operators中剩余的操作：这下是从左到右计算
        while (!operators.isEmpty()) {
            calculateBottom(numbers, operators);
        }

        return numbers.pop();
    }

    private void calculateBottom(Stack<Integer> numbers, Stack<String> operators) {
        //todo
    }

    private List<String> split(String s) {

        int numberStart = 0;
        List<String> tokens = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char startChar = s.charAt(i);
            //利用符号切分字符串，遇到符号则执行切分，前面[numberStart,operatorStart)的是数字，转成数字时注意trim即可
            if (isOperator(startChar)) {
                tokens.add(s.substring(numberStart, i)); //number
                numberStart = i + 1;

                tokens.add(s.substring(i, i + 1)); //operator
            }
        }

        //最后一个数字
        tokens.add(s.substring(numberStart));

        return tokens;
    }

    private void calculateTop(Stack<Integer> numbers, Stack<String> operators) {
        //触发计算，先pop出来的是右操作数，然后pop左操作数，计算完成后push back to numbers
        String operator = operators.pop();
        int right = numbers.pop();
        int left = numbers.pop();
        if (operator.equals("+")) { numbers.push(left + right); } else if (operator.equals("-")) {
            numbers.push(left - right);
        } else if (operator.equals("*")) { numbers.push(left * right); } else if (operator.equals("/")) {
            numbers.push(left / right);
        }
    }

    private boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/';
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int priorityOf(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 0;
        } else {
            return 1;
        }
    }
}
