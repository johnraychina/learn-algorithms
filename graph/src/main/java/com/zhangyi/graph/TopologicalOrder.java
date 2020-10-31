package com.zhangyi.graph;

import java.util.Iterator;

import algs4.Digraph;
import algs4.In;
import algs4.Queue;
import algs4.Stack;
import algs4.StdOut;

/**
 * <pre>
 * 拓扑排序：将有向图按照节点的依赖顺序排序
 *
 * 思考：
 * 可能有些节点 没有被其他节点依赖(inDegree==0)，此类节点可能又多个。
 * 可能有些节点 没有依赖其他节点(outDegree==0)，此类节点可能又多个。
 * 节点依赖顺序还可能是"并行的"（两个节点依赖相同的节点，也被某个相同节点依赖）。
 * 如果没有其他约束条件，那么就存在多种拓扑顺序。
 *
 * 依赖顺序可以通过dfs+marked来得到，分三种遍历：pre, post, postReverse
 * 逆后序postReverse是正解
 *
 * 注意：不能有环
 * </pre>
 *
 * @author Zhang Yi
 */
public class TopologicalOrder {

    private int V;
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private int[] rank; //节点v在拓扑中的序号
    private Stack<Integer> postReverse;

    public TopologicalOrder(Digraph g) {
        marked = new boolean[g.V()];
        this.V = g.V();
        pre = new Queue<>();
        post = new Queue<>();

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) { dfs(g, i); }
        }

        postReverse = new Stack<>();
        rank = new int[V];
        int irank = V - 1;
        for (Integer i : post) {
            postReverse.push(i);
            rank[i] = irank;
            irank--;
        }
    }

    public static void main(String[] args) {
        In in = new In("directed_graph.txt");
        Digraph g = new Digraph(in);
        TopologicalOrder tp = new TopologicalOrder(g);
        StdOut.print("Reverse postorder: ");
        for (int v : tp.postReverse) {
            StdOut.print(v + " ");
        }
        StdOut.println();
    }

    private void dfs(Digraph g, int i) {
        marked[i] = true;

        pre.enqueue(i); //pre order
        for (int v : g.adj(i)) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
        post.enqueue(i); //post order
    }

    public Iterator<Integer> postReverse() {
        return postReverse.iterator();
    }

    public int rank(int v) {
        return rank[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) { throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1)); }
    }

}
