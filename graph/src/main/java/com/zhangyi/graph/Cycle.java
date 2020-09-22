package com.zhangyi.graph;

import algs4.Graph;
import algs4.Stack;

/**
 * <pre>
 * 无环图和树等价，dfs往下遍历子节点会构成一棵树，所有节点应该都只被遍历一次，
 * 若是发现有子节点已经被标记（遍历）过，就说明构成环。
 * </pre>
 *
 * @author 张义 johnraychina@163.com
 */
public class Cycle {

    private boolean[] marked;
    private Stack<Integer> cycle;
    private boolean hasCycle;

    public Cycle(Graph g, int s) {
        //initialize data structures
        marked = new boolean[g.V()];
        hasCycle = false;

        dfs(g, s, s);
    }

    public static void main(String[] args) {

    }

    /**
     * adj(u) = { v, v1, v2 ... } adj(v) = { w==u, w1, w2, w3 ... }
     *
     * @param G graph
     * @param v current node
     * @param u parent node
     */
    private void dfs(Graph G, int v, int u) {
        mark(v);

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            } else if (w != u) { //dfs v的邻接点（排除上级u）被标记过，说明有环，
                hasCycle = true;
            }
        }
    }

    private void mark(int v) {
        marked[v] = true;
        System.out.println(v);
    }
}
