package week2.lecture;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 表达式求值，高级版
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class AdvancedEvaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            // fix a little problem in the lecture code sample: the while never ends
            if (s.equals("show")) {
                break;
            }

            if (s.equals("(")) { ; } else if (s.equals("+")) { ops.push(s); } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) { vals.push(vals.pop() + vals.pop()); } else if (op.equals("*")) {
                    vals.push(vals.pop() * vals.pop());
                }
            } else { vals.push(Double.parseDouble(s)); }
        }
        StdOut.println(vals.pop());
    }
}
