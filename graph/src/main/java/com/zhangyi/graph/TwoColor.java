package com.zhangyi.graph;

import algs4.Graph;
import algs4.Stack;

/**
 * <pre>
 * 给图的所有顶点涂色，两个直连的顶点颜色必须不同，能否只用2个颜色？
 * 无环图一定可以实现2个颜色着色，
 * 只要判断有环，且 已标记的顶点颜色 == 当前顶点颜色，则无法满足着色条件。
 * </pre>
 * @author 张义 johnraychina@163.com
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor;

    public TwoColor(Graph g, int s) {
        //initialize data structures
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        isTwoColor = true;

        dfs(g, s, s);
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
            } else if (w != u) {
                isTwoColor = !(color[w] == color[v]);
            }
        }
    }

    private void mark(int v) {
        marked[v] = true;
        System.out.println(v);
    }

}
