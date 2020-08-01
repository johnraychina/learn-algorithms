package com.zhangyi.graph;

import algs4.Graph;
import algs4.Queue;

/**
 * @author 张义 johnraychina@163.com
 */
public class BreadthFirstSearch {

    private boolean[] mark;
    private int[] edgeTo;

    /**
     * @param graph graph
     * @param v     the index of source vertex
     */
    public BreadthFirstSearch(Graph graph, int v) {
        mark = new boolean[graph.V()];
        edgeTo = new int[graph.E()];
        bfs(graph, v);
    }

    /**
     * breadth first graph search
     *
     * @param graph graph
     * @param s     the index of starting vertex
     */
    private void bfs(Graph graph, int s) {

        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        mark[s] = true;

        while (!q.isEmpty()) {

            //deque first vertex and enqueue the adjacent vertices
            Integer v = q.dequeue();
            for (int w : graph.adj(v)) {
                q.enqueue(w);
                mark[w] = true;
                edgeTo[w] = v;
            }
        }
    }

    public static void main(String[] args) {

    }
}
