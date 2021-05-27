package com.zhangyi.string;

import algs4.Bag;

/**
 * 类似于iterator，用于保存对一个图的遍历状态（游标）.
 *
 * @author Zhang Yi
 */
public class DirectedDFS {
    private boolean marked[]; //marked[v]=true if v is reachable from source(s)
    private int count; // number of vertices reachable from source(s)

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.V()];
        validateVertex(s);
        dfs(g, s);
    }

    public DirectedDFS(Digraph g, Bag<Integer> pc) {
        marked = new boolean[g.V()];
        for (int v : pc) {
            if (!marked[v]) { dfs(g, v); }
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int count() {
        return count;
    }

    //深度递归遍历
    private void dfs(Digraph g, int s) {
        marked[s] = true;
        count++;

        for (Integer v : g.adj(s)) {
            if (!marked[v]) { dfs(g, v); }
        }
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) { throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1)); }
    }

}
