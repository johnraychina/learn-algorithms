package com.zhangyi.graph;

import algs4.Bag;

/**
 * 用数据结构复刻真实世界的图
 * 支持图的读（遍历）写（添加）操作
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class Graph {

    private final int V;

    //In practice. Use adjacency-lists representation.
    //Algorithms based on iterating over vertices adjacent to v.
    //・Real-world graphs tend to be sparse.
    //数据结构：复刻 图抽象顶点连接关系
    // 可以用2维数组，2层嵌套list，
    // 但真实世界的图往往是稀疏的，内层不划算
    // 所以用邻接点链表来表示更加合适（这里的bag类似linked list)
    private Bag<Integer> adj[];

    public Graph(int v) {
        V = v;

        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    // 添加无向边，对称结构
    // 由于是Bag，所以同样逻辑能支持自循环的边
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // 按顶点遍历邻接点
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

}
