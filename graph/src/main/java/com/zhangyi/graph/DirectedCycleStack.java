package com.zhangyi.graph;

import algs4.Digraph;
import algs4.In;
import algs4.Stack;
import algs4.StdOut;

/**
 * <pre>
 * 需求：很多调度任务中，不允许有环。
 *
 * 如何判断是否有环？
 * 对图进行dfs遍历，mark，如果v-w, w已经在当前dfs栈中，说明w在一个环中。
 *
 * 一个图可能有多个环，想要知道所有的环，这是个V的指数级问题。
 *
 * 这里我们检测到一个环就返回。
 * </pre>
 *
 * @author Zhang Yi
 */
public class DirectedCycleStack {

    private boolean[] marked; //控制重复遍历，避免死循环

    /**
     * <pre>
     * 需要注意的是光有marked还不能得到环：
     * 1、dfs返回时，不会清空已经marked的节点，一个节点是可以有多个节点指向它的 所以不能光凭adj(v)被marked就推断它有环，而是要按dfs栈判断。
     * 2、因为栈退出时pop节点，如果在栈中，说明在一条路径中出现了2次，肯定就有环。
     * </pre>
     */
    private boolean[] onStack; //onStack[v]节点v是否在当前dfs栈上?
    private boolean hasCycle;
    private Stack<Integer> dfsStack; //保留dfs当前栈，小心维护这个dfsStack，使数据与实际代码stack遍历一致
    //private int[] edgeTo; //保留path的从后向前指针

    public DirectedCycleStack(Digraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        //edgeTo = new int[g.V()];
        dfsStack = new Stack<>();
        hasCycle = false;

        for (int i = 0; i < g.V(); i++) {
            dfs(g, i);
        }
    }

    /**
     * Unit tests the {@code DirectedCycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In("simple_directed_graph.txt");
        Digraph G = new Digraph(in);

        DirectedCycleStack finder = new DirectedCycleStack(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }

    private void dfs(Digraph g, int v) {
        if (hasCycle) {
            return; //如果有cycle则立即返回，保持dfsStack状态
        }
        marked[v] = true;
        onStack[v] = true;
        dfsStack.push(v);
        for (Integer w : g.adj(v)) {
            //如果有cycle则立即返回，保持dfsStack状态
            if (hasCycle) {
                return;
            } else if (!marked[w]) {
                dfs(g, w);
                return;
            }
            //已经marked，且onStack则说明有环
            //光return还不足以让整个dfs停下，需要有个标志判断让整个dfs停下
            else if (onStack[w]) {
                dfsStack.push(w); //最后一次push，也是stack里面的第二个w
                hasCycle = true;
                return;
            }
        }
        //dfs退出时pop节点
        dfsStack.pop();
        onStack[v] = false;
    }

    public Iterable<Integer> cycle() {
        if (!hasCycle) {
            return null;
        }

        //如果有环，则stack的顶部就是环的起点和终点
        //且还包含一个从起点开始的路径
        // 就像一个气球，我们把这个线剪掉即可
        Stack<Integer> path = new Stack<>();
        int end = dfsStack.pop();
        path.push(end);
        while (dfsStack.peek() != end) {
            path.push(dfsStack.pop());
        }
        path.push(dfsStack.pop());

        return path;
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
