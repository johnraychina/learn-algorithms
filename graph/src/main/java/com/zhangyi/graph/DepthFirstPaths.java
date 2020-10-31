package com.zhangyi.graph;

import algs4.Graph;
import algs4.In;
import algs4.Stack;

/**
 * 深度优先搜索路径，是一个对无向图遍历的数据结构，代表从一个初始节点s到其他节点的遍历路径。
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class DepthFirstPaths {

    private final int s; // source vertex

    //这个数组是得到遍历路径的关键，dfs遍历是从s出发，到达的点是多个，必定是多个path构成的树
    // 怎么样有效地表达s到v_i路径呢，如果用一个二维数组 edgeTo[v_i][s~...]就太浪费了
    // 反过来想：既然路径起始点都是s，那么唯一识别路径就是靠v_i，如果edgeTo[v_i]=x只记录从s到v_i的上个节点x，不就串起来了吗，空间小了很多
    // 这个方法的本质：只记录最少信息（剪枝），通过最少信息可以关联得出整条s-v路径
    private int[] edgeTo; //  edgeTo[v] = last edge on s-v path
    private boolean[] marked; // marked[v] = is there a s-v path?

    public DepthFirstPaths(Graph g, int source) {
        this.s = source;
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];

        dfs(g, s);
    }

    public static void main(String[] args) {
        In in = new In("undirected_graph.txt");
        Graph g = new Graph(in);
        DepthFirstPaths search = new DepthFirstPaths(g, 0);

        System.out.println(search.hasPathTo(3));
        System.out.println(search.hasPathTo(5));

        for (Integer v : search.pathTo(5)) {
            System.out.print(" " + v);
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
        }
    }

    /**
     * Is there a path from s to v ?
     *
     * @param v destination vertex
     * @return {@code true} if there is a path, {@code false} otherwise.
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) { return null; }

        //通过edgeTo可以得到从v到s的路径，但是要得到从s到v的路径，需要借助stack反转
        Stack<Integer> stack = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length) { throw new IllegalArgumentException("Illegal vertex " + v); }
    }
}
