package com.zhangyi.string;

import algs4.Bag;

/**
 * @author Zhang Yi
 */
public class Digraph {
    private int V; //number vertices
    private int E; //number of edges
    private Bag<Integer>[] adj;
    private int[] inDegree; //in-degree of each vertex, handy for topological sort

    public Digraph(int numV) {
        if (numV < 0) { throw new IllegalArgumentException("Illegal number of vertices"); }
        this.V = numV;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V];
        inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>(); //init collection
            inDegree[i] = 0;
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * add edge
     *
     * @param v from
     * @param u to
     */
    public void addEdge(int v, int u) {
        validateVertex(v);
        validateVertex(u);

        adj[v].add(u);
        inDegree[u]++;
        E++;
    }

    public Bag<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int inDegree(int v) {
        validateVertex(v);
        return inDegree[v];
    }

    public int outDegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) { throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1)); }
    }
}
