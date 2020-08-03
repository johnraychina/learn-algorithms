package com.zhangyi.graph;

import algs4.Graph;
import algs4.In;
import algs4.StdOut;

/**
 * @author 张义 johnraychina@163.com
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int componentId[]; //component id of each vertices
    private int count;         // number of connected components(zero based)

    public DepthFirstSearch(Graph g, int s) {
        //initialize data structures
        marked = new boolean[g.V()];
        componentId = new int[g.V()];
        count = 0;
        dfs(g, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        mark(v);

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                count++;
            }
        }
    }

    private void mark(int v) {
        marked[v] = true;
        componentId[v] = count;
        System.out.println(v);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    //count of components
    public int count() {
        return count;
    }

    public boolean connected(int a, int b) {
        validateVertex(a);
        validateVertex(b);

        return componentId[a] == componentId[b];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) { throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1)); }
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
