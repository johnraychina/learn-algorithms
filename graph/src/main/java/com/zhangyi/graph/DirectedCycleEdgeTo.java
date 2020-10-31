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
public class DirectedCycleEdgeTo {

    private boolean[] marked; //控制重复遍历，避免死循环

    /**
     * <pre>
     * 需要注意的是光有marked还不能得到环：
     * 1、dfs返回时，不会清空已经marked的节点，一个节点是可以有多个节点指向它的 所以不能光凭adj(v)被marked就推断它有环，而是要按dfs栈判断。
     * 2、因为栈退出时pop节点，如果在栈中，说明在一条路径中出现了2次，肯定就有环。
     * </pre>
     */
    private boolean[] onStack; //onStack[v]节点v是否在当前dfs栈上?
    //实现path的关键：edgeTo保留path的从后向前指针
    //为什么不能从从前向后呢？试试看吧
    private int[] edgeTo; //维护dfs的遍历树
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    public DirectedCycleEdgeTo(Digraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];

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

        DirectedCycleEdgeTo finder = new DirectedCycleEdgeTo(G);
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

        marked[v] = true;
        onStack[v] = true;
        for (Integer w : g.adj(v)) {
            //如果有cycle则立即返回，保持onStack状态
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v; //维护dfs的遍历树
                dfs(g, w);
            }
            //已经marked，且onStack则说明有环，初始化cycle记录环中节点，后续的遍历因此停止
            else if (onStack[w]) {
                cycle = new Stack<>();
                for (int i = v; i != w; i = edgeTo[i]) { //收尾都是w相连形成一个环
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
                return;
            }
        }
        onStack[v] = false;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }
}
