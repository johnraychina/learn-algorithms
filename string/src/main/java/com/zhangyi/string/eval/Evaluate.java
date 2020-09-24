package com.zhangyi.string.eval;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * <pre>
 * Dijsktra 双栈表达式求值算法
 *
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class Evaluate {

    /**
     * <pre>
     * Shunting yard 调度场算法
     * https://en.wikipedia.org/wiki/Shunting-yard_algorithm
     *
     * 艾兹格·迪科斯彻引入了调度场算法，用于将中缀表达式转换为后缀形式。因其操作类似于火车编组场而得名。
     * 大多数操作符优先级解析器(解析器用简单的查表操作即可实现，优先级表由开发者自己定制，在不同的应用场景中，开发者可自由改变操作符的优先级)能转换为处理后缀表达式，
     * 实际中，一般构造抽象语法树，树的后序遍历即为逆波兰记法。
     *
     * 实际意义：将表达式计算优先级内嵌在操作符顺序当中，简化计算。
     * 从左到右，遇到操作符则取出左边的数字进行计算，结果放回原处。
     * e.g.
     * a + b * c ===> abc*+
     * (a + b) * c ==> ab+c*
     * Graphical illustration of algorithm, using a three-way railroad junction. The input is processed one symbol at
     * a time: if a variable or number is found, it is copied directly to the output a), c), e), h).
     *
     * If the symbol is an operator, it is pushed onto the operator stack b), d), f).
     *
     * If the operator's precedence is lower than that of the operators at the top of the stack
     * or the precedents are equal and the operator is left associative,
     * then that operator is popped off the stack and added to the output g).
     *
     * Finally, any remaining operators are popped off the stack and added to the output i).
     *
     *
     *
     *
     * This implementation does not implement composite functions,functions with variable number of arguments, and unary operators.
     *
     *
     *  while there are tokens to be read:
     *      read a token.
     *      if the token is a number, then:
     *          push it to the output queue.
     *      else if the token is a function then:
     *          push it onto the operator stack
     *      else if the token is an operator then:
     *          while ((there is an operator at the top of the operator stack)
     *              and ((the operator at the top of the operator stack has greater precedence)
     *              or (the operator at the top of the operator stack has equal precedence and the token is left associative))
     *              and (the operator at the top of the operator stack is not a left parenthesis)):
     *          pop operators from the operator stack onto the output queue.
     *          push it onto the operator stack.
     *      else if the token is a left parenthesis (i.e. "("), then:
     *          push it onto the operator stack.
     *      else if the token is a right parenthesis (i.e. ")"), then:
     *          while the operator at the top of the operator stack is not a left parenthesis:
     *              pop the operator from the operator stack onto the output queue.
     *          //If the stack runs out without finding a left parenthesis, then there are mismatched parentheses.
     *          if there is a left parenthesis at the top of the operator stack, then:
     *              pop the operator from the operator stack and discard it
     *  //After while loop, if operator stack not null, pop everything to output queue
     *  if there are no more tokens to read then:
     *          while there are still operator tokens on the stack:
     *              // If the operator token on the top of the stack is a parenthesis, then there are mismatched parentheses.
     *              pop the operator from the operator stack onto the output queue.
     * exit.
     *
     *   循环读取表达式输入 e[i]:
     *        如果为数字，则输出队列
     *        如果是操作符，则对比ops顶部的优先级：
     *           while ops顶部优先级更高  或  优先级相等但是操作符是左关联的，且不是)，则弹出ops送入队列
     *           操作符入栈ops
     *        如果是(，入栈ops
     *        如果是), 弹出ops送入队列，一直到弹出并忽略(为止
     *    如果读取完成，则弹出ops操作符，送入队列
     * </pre>
     */
    public static Queue<String> toRPN(String[] e) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        Queue<String> out = new LinkedList<>();

        for (int i = 0; i < e.length; i++) {
            if (isNumber(e[i])) {
                vals.push(Double.parseDouble(e[i]));
            } else if (isOperator(e[i])) {
                while (comparePriority(ops.peek(), e[i]) > 0 && !isLeftParenthesis(ops.peek())) {
                    out.add(ops.pop());
                }
                ops.push(e[i]);
            } else if (isLeftParenthesis(e[i])) {
                ops.push(e[i]);
            } else if (isRightParenthesis(e[i])) {
                while (!isLeftParenthesis(ops.peek())) {
                    out.add(ops.pop());
                }
                if (isLeftParenthesis(ops.peek())) {
                    ops.pop();
                }
            }
        }

        return out;
    }



    private static boolean isLeftParenthesis(String s) {
        return s.equals("(");
    }

    private static boolean isRightParenthesis(String s) {
        return s.equals(")");
    }
    private static int comparePriority(String op1, String op2) {
        return operatorMap.get(op1) - operatorMap.get(op2);
    }

    /**
     * 支持的操作符和优先级
     */
    private static Map<String, Integer> operatorMap;
    static {
        operatorMap = new HashMap<>();
        operatorMap.put("+", 1);
        operatorMap.put("-", 1);
        operatorMap.put("*", 10);
        operatorMap.put("/", 10);
    }

    private static boolean isOperator(String s) {
        return operatorMap.containsKey(s);
    }

    private static boolean isNumber(String s) {
        return Character.isDigit(s.charAt(0));
    }

    public static double eval(String[] e) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        for (int i = 0; i < e.length; i++) {

            //如果是数字则入栈vals
            //如果是操作符则入栈ops
            //如果是)则弹出ops和2个vals，执行计算，然后push到vals
            if (e[i].equals("+") || e[i].equals("-") || e[i].equals("*") || e[i].equals("/")) {
                ops.push(e[i]);
            } else if (e[i].equals("(")) {
                continue;
            } else if (e[i].equals(")")) {
                String op = ops.pop();
                Double a = vals.pop();
                Double b = vals.pop();
                if (op.equals("+")) { vals.push(a + b); }
                if (op.equals("-")) { vals.push(a - b); }
                if (op.equals("*")) { vals.push(a * b); }
                if (op.equals("/")) { vals.push(a / b); }
            } else {
                vals.push(Double.parseDouble(e[i]));
            }
        }

        return vals.pop();
    }

    public static void main(String[] args) {
        String input = "( ( ( ( 1 + 2 ) * 3 ) + ( 4 * 5 ) ) + 6 )";
        String[] expr = input.split("\\s+");
        System.out.println(eval(expr));
    }

}
