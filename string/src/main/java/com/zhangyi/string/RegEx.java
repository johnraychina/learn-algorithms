package com.zhangyi.string;

import algs4.Bag;
import algs4.Stack;
import algs4.StdOut;

/**
 * Regular expression
 *
 * @author Zhang Yi
 */
public class RegEx {

    private Digraph epsilonG; // epsilon graph
    private String pat; //pattern string

    public RegEx(String pattern) {
        this.pat = pattern;
        epsilonG = buildEpsilonGraph();
    }

    public static void main(String[] args) {
        //String regexp = "(.*(art|science).*)";
        String regexp = ".*(art|science).*";
        String txt = "The art of computer Science";
        RegEx regEx = new RegEx(regexp);
        StdOut.println(regEx.recognizes(txt));
    }

    private Digraph buildEpsilonGraph() {

        // one status for each character + 1 accept state
        Digraph g = new Digraph(pat.length() + 1);
        //iterate pat characters to build epsilon transfer edges
        //针对符号(, |, ), * 加"跳线"，用一个栈来记录位置，遇到右括号则触发加跳线操作
        Stack<Integer> ops = new Stack<>();
        for (int i = 0; i < pat.length(); i++) {

            int lp = i; //may be left parenthesis

            if (pat.charAt(i) == '(' || pat.charAt(i) == '|') {
                ops.push(i);
            } else if (pat.charAt(i) == ')') { //触发加跳线
                Integer op = ops.pop();
                if (pat.charAt(op) == '|') {
                    lp = ops.pop(); //左括号
                    g.addEdge(op, i); //从|到)
                    g.addEdge(lp, i + 1); //从(到|后面一个字符

                } else if (pat.charAt(op) == '(') {
                    lp = op;
                } else {
                    assert false;
                }
            }

            //向右多看一位，如果是*闭包，则要加多2根跳线
            //从(到*, 从*到(
            if (i < pat.length() - 1 && pat.charAt(i + 1) == '*') {
                g.addEdge(lp, i + 1);
                g.addEdge(i + 1, lp);
            }

            //前面跳线加完后，加上顺序的状态转换
            if (pat.charAt(i) == '(' || pat.charAt(i) == ')' || pat.charAt(i) == '*') {
                g.addEdge(i, i + 1);
            }
        }

        //合法性校验
        if (ops.size() != 0) { throw new IllegalArgumentException("Invalid regular expression"); }

        return g;
    }

    public boolean recognizes(String txt) {

        //当前可达状态
        Bag<Integer> pc = new Bag<Integer>();

        //从0开始的epsilon可达状态
        DirectedDFS dfs = new DirectedDFS(epsilonG, 0);
        for (int i = 0; i < epsilonG.V(); i++) {
            if (dfs.marked(i)) { pc.add(i); }
        }

        //对文本逐个匹配，计算NFA可能的状态
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '(' || txt.charAt(i) == ')') {
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");
            }

            //当前匹配状态
            Bag<Integer> match = new Bag<>();
            for (int v : pc) {
                //终态不与text做匹配
                if (v == pat.length()) { continue; }
                //相等或者是.通配符，则往后遍历
                if (pat.charAt(v) == txt.charAt(i) || pat.charAt(v) == '.') {
                    match.add(v + 1);
                }
            }

            //针对当前匹配状态match走一次epsilon转换，放入新的匹配状态pc中
            pc = new Bag<>();
            dfs = new DirectedDFS(epsilonG, match);
            for (int j = 0; j < epsilonG.V(); j++) {
                if (dfs.marked(j)) {
                    pc.add(j);
                }
            }
        }

        //匹配完txt文本后， 当前状态集包含终态则说明匹配成功，否则匹配失败
        for (int v : pc) { if (v == pat.length()) { return true; } }
        return false;
    }
}
