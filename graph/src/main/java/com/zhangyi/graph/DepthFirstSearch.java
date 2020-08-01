package com.zhangyi.graph;

import algs4.Graph;
import algs4.In;
import algs4.Stack;
import algs4.StdOut;

/**
 * @author 张义 johnraychina@163.com
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private int s; //start vertex

    public DepthFirstSearch(Graph g, int s) {
        //initialize data structures
        marked = new boolean[g.V()];
        edgeTo = new int[g.E()];
        this.s = s;

        dfs(g, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        mark(v);

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    private void mark(int v) {
        marked[v] = true;
        System.out.println(v);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * Unit tests the {@code DepthFirstSearch} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
    }
}
