package com.zhangyi.string.eval;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
public class DijkstraShuntingYardEvaluation {

    /**
     * <pre>
     *
     * 逆波兰表示法（Reverse Polish notation，RPN，或逆波兰记法），是一种是由波兰数学家扬·武卡谢维奇1920年引入的数学表达式方式。
     * 在逆波兰记法中，所有操作符置于操作数的后面，因此也被称为后缀表示法。逆波兰记法不需要括号来标识操作符的优先级。
     * 逆波兰结构由弗里德里希·鲍尔（Friedrich L. Bauer）和艾兹格·迪科斯彻在1960年代早期提议用于表达式求值，以利用堆栈结构减少计算机内存访问。
     *
     * Shunting yard 调度场算法，将中缀算数表达式转换为后缀表达式（逆波兰表达式）
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
     * Graphical illustration of algorithm, using a three-way railroad junction.
     * The input is processed one symbol at a time:
     *  - if a variable or number is found, it is copied directly to the output.
     *  - If the symbol is an operator, it is pushed onto the operator stack.
     *  - If the operator's precedence is lower than that of the operators at the top of the stack
     *    or the precedents are equal and the operator is left associative,
     *    then that operator is popped off the stack and added to the output.
     *
     * Finally, any remaining operators are popped off the stack and added to the output.
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
     *           while ops顶部不是),  且优先级更高  或  优先级相等但是操作符是左关联的，则弹出ops送入队列
     *           操作符入栈ops
     *        如果是(，入栈ops
     *        如果是), 弹出ops送入队列，一直到弹出并忽略(为止
     *    如果读取完成，则弹出ops操作符，送入队列
     * </pre>
     */
    public static Queue<String> toRPN(String[] e) {
        Stack<String> ops = new Stack<>();
        Queue<String> out = new LinkedList<>();

        for (String s : e) {
            if (isNumber(s)) {
                out.add(s);
            } else if (isOperator(s)) {
                while (!ops.isEmpty() && !isLeftParenthesis(ops.peek()) && comparePriority(ops.peek(), s) > 0) {
                    out.add(ops.pop());
                }
                ops.push(s);
            } else if (isLeftParenthesis(s)) {
                ops.push(s);
            } else if (isRightParenthesis(s)) {
                while (!isLeftParenthesis(ops.peek())) {
                    out.add(ops.pop());
                }
                if (isLeftParenthesis(ops.peek())) {
                    ops.pop();
                }
            }
        }

        //将ops栈剩下的操作符输出
        while (!ops.isEmpty()) {
            out.add(ops.pop());
        }

        return out;
    }

    private static boolean isLeftParenthesis(String s) {
        return s.equals("(");
    }

    private static boolean isRightParenthesis(String s) {
        return s.equals(")");
    }

    /**
     * 支持的操作符和优先级
     */
    private final static Map<String, Integer> operatorMap;

    static {
        operatorMap = new HashMap<>();
        operatorMap.put("+", 1);
        operatorMap.put("-", 1);
        operatorMap.put("*", 10);
        operatorMap.put("/", 10);
    }

    private static int comparePriority(String op1, String op2) {
        return operatorMap.get(op1) - operatorMap.get(op2);
    }

    private static boolean isOperator(String s) {
        return operatorMap.containsKey(s);
    }

    private static boolean isNumber(String s) {
        return Character.isDigit(s.charAt(0));
    }

    /**
     * 仅支持左右括号齐全（每个运算都带一堆左右括号）的算数表达式运算，使用右括号触发计算。
     *
     * @param tokens 表达式元素数组
     * @return 结果值
     */
    public static double simpleEval(String[] tokens) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        for (String s : tokens) {

            //如果是数字则入栈vals
            //如果是操作符则入栈ops
            //如果是)则弹出ops和2个vals，执行计算，然后push到vals
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                ops.push(s);
            } else if (s.equals("(")) {
                continue;
            } else if (s.equals(")")) {
                String op = ops.pop();
                Double a = vals.pop();
                Double b = vals.pop();
                if (op.equals("+")) { vals.push(a + b); }
                if (op.equals("-")) { vals.push(a - b); }
                if (op.equals("*")) { vals.push(a * b); }
                if (op.equals("/")) { vals.push(a / b); }
            } else {
                vals.push(Double.parseDouble(s));
            }
        }

        return vals.pop();
    }

    /**
     * 支持优先级运算，支持括号中多个运算符。 触发计算case1：优先级更低的新操作数来了，得先将ops中优先级高的算完 触发计算case2：右括号来了，说明当前ops中(...)之间的计算都比后续新操作数高，括号里面的运算要先做。
     * <p>
     * while循环读取表达式元素： 如果是数字则入栈vals 如果是操作符则： 如果ops顶部优先级更高，则先计算ops顶部操作符 操作符入栈ops 如果是)则计算括号里面的内容 将ops中剩余操作执行完
     *
     * @param tokens 表达式元素数组
     * @return 结果值
     */
    public static double advancedEval(String[] tokens) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        for (String s : tokens) {
            if (operatorMap.containsKey(s)) {
                //保证当前ops栈优先级高的运算符先算完
                while (!ops.isEmpty() && !isLeftParenthesis(ops.peek()) && comparePriority(ops.peek(), s) > 0) {
                    evalTopOperator(ops, vals);
                }
                //再push新的运算符
                ops.push(s);
            } else if (s.equals("(")) {
                //与simpleEval全括号不同的是，括号里面有多个运算符，需要保留左括号作为一个标记：优先计算遇到右括号开始，遇到到左括号结束。
                //continue;
                ops.push(s);
            } else if (s.equals(")")) {
                //如果是右括号，要一直计算到左括号，相对于后续的运算，括号里面都是优先级高的运算
                while (!ops.isEmpty() && !isLeftParenthesis(ops.peek())) {
                    evalTopOperator(ops, vals);
                }
                if (ops.isEmpty() || !isLeftParenthesis(ops.pop())) { throw new IllegalArgumentException("缺少左括号"); }
            } else {
                vals.push(Double.parseDouble(s));
            }
        }

        //将ops栈算完
        while (!ops.isEmpty()) {
            evalTopOperator(ops, vals);
        }

        Double result = vals.pop();
        if (vals.isEmpty()) {
            return result;
        } else {
            throw new IllegalArgumentException("缺失操作符");
        }
    }

    private static void evalTopOperator(Stack<String> ops, Stack<Double> vals) {
        String op = ops.pop();
        Double a = vals.pop();
        Double b = vals.pop();
        if (op.equals("+")) { vals.push(a + b); }
        if (op.equals("-")) { vals.push(a - b); }
        if (op.equals("*")) { vals.push(a * b); }
        if (op.equals("/")) { vals.push(a / b); }
    }

    public static void main(String[] args) {
        String inputExpr = "( ( ( ( 1 + 2 ) * 3 ) + ( 4 * 5 ) ) + 6 )";
        String[] tokens = inputExpr.split("\\s+");

        System.out.println(inputExpr);
        String rpn = String.join(" ", toRPN(tokens));
        System.out.println(" 逆波兰表达式:" + rpn);

        System.out.println(inputExpr);
        System.out.println("全括号模式计算：" + simpleEval(tokens));

        inputExpr = "( 1 + 2 ) * 3 + 4 * 5";
        tokens = inputExpr.split("\\s+");
        System.out.println(inputExpr);
        System.out.println("改进版计算：" + advancedEval(tokens));
    }

}
