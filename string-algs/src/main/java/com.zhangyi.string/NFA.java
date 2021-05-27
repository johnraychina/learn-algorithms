package com.zhangyi.string;

import algs4.Bag;
import algs4.Digraph;
import algs4.DirectedDFS;
import algs4.Stack;
import algs4.StdOut;

/**
 * Non-deterministic  Finite Automation.
 * <p>
 * find one of specified set of strings in text
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class NFA {

    private Digraph G; //directed graph of epsilon transitions
    private char[] re; // regular expression
    private int M; // number of characters in regular expression

    //有限确定自动机要穷举所有的状态转换，需要指数级别的代价
    //需要使用非确定自动机来表示

    public NFA(String regex) {
        this.re = regex.toCharArray();
        this.M = regex.length();

        //构造epsilon transition, 加多一个1是因为有个accept state
        G = buildEpsilonTransitionDigraph();

    }

    private Digraph buildEpsilonTransitionDigraph() {
        //这里+1是要多出一个终态
        Digraph epsilonG = new Digraph(M + 1);
        //ops存放re正则字符中操作符: (, ), |, *, 用于给跳线的两个顶点配对.
        Stack<Integer> ops = new Stack<>();
        for (int i = 0; i < M; i++) {
            int lp = i;

            if (re[i] == '(' || re[i] == '|') {   //做push
                ops.push(i);
            } else if (re[i] == ')') {
                int op = ops.pop();               //做pop: 得到|或者(
                if (re[op] == '|') {
                    lp = ops.pop();
                    epsilonG.addEdge(lp, op + 1);     // 如果是操作符|, 再pop一下得到左括号状态位，加2根跳线
                    epsilonG.addEdge(op, i);             // todo 这里没考虑多个|的情况

                } else if (re[op] == '(') {
                    lp = op;
                } else {
                    assert false;
                }
            }

            //向右多看一位，如果是闭包*，加2根跳线
            if (i < M - 1 && re[i + 1] == '*') {
                epsilonG.addEdge(lp, i + 1);
                epsilonG.addEdge(i + 1, lp);
            }

            //前面跳线加完了，现在加上顺序的状态转换
            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                epsilonG.addEdge(i, i + 1);
            }
        }

        //合法性校验
        if (ops.size() != 0) { throw new IllegalArgumentException("Invalid regular expression"); }

        return epsilonG;
    }

    public boolean recognizes(String txt) {

        //当前可达的状态
        Bag<Integer> pc = new Bag<>();
        //从0开始可达的epsilon 转换图
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) { pc.add(v); }
        }

        //对文本字符逐个匹配，计算NFA中可能的状态
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '(' || txt.charAt(i) == ')')
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");

            //对每个当前状态pc 做字符匹配，如果匹配成功则将下一个状态加到match中（注意忽略终态v==M）
            Bag<Integer> match = new Bag<>();
            for (int v : pc) {
                if (v == M) { continue; }
                if (txt.charAt(i) == re[v] || re[v] == '.') {
                    match.add(v + 1);
                }
            }

            //针对当前匹配状态集match，走一次epsilon状态转换，得到新的当前状态pc
            pc = new Bag<>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++) { if (dfs.marked(v)) { pc.add(v); } }

        }

        //匹配完txt文本后， 当前状态集包含终态则说明匹配成功，否则匹配失败
        for (int v : pc) { if (v == M) { return true; } }
        return false;
    }

    public static void main(String[] args) {
        //String regexp = "(.*(art|science).*)";
        String regexp = ".*(art|science).*";
        String txt = "The art of computer Science";
        NFA nfa = new NFA(regexp);
        StdOut.println(nfa.recognizes(txt));
    }

}
