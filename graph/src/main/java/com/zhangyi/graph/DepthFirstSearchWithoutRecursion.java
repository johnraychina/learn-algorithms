package com.zhangyi.graph;

import algs4.Graph;
import algs4.In;
import algs4.Stack;
import algs4.StdOut;

/**
 * @author 张义 johnraychina@163.com
 */
public class DepthFirstSearchWithoutRecursion {
    private boolean[] marked;
    private int[] edgeTo;
    private int s; //start vertex

    public DepthFirstSearchWithoutRecursion(Graph g, int s) {
        //initialize data structures
        marked = new boolean[g.V()];
        edgeTo = new int[g.E()];
        this.s = s;

        dsfWithoutRecursion(g, s);
    }

    private void dsfWithoutRecursion(Graph g, int v) {

        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()) {
            //iterate down to the tree
            //if not marked then push
            //if marked then check next
            for (int w : g.adj(stack.peek())) {
                if (!marked[w]) {
                    stack.push(w);
                    mark(w);
                }
            }

            //if reach the bottom of a tree, backward
            Integer top = stack.pop();
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
        DepthFirstSearchWithoutRecursion search = new DepthFirstSearchWithoutRecursion(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
    }
}
