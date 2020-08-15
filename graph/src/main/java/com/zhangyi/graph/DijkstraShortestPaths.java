package com.zhangyi.graph;

import algs4.DirectedEdge;
import algs4.EdgeWeightedDigraph;
import algs4.IndexMinPQ;
import sun.tools.tree.DoubleExpression;

/**
 * <pre>
 * Goal. Find the shortest path from s to every other vertex.
 * 最短路径算法的目的是：找到从一个节点到所有其他节点的最短路径。
 *
 * Observation. A shortest-paths tree (SPT) solution exists. Why?
 * 这就构成了一个最短路径树，为什么呢？
 * 如果有2条或以上的路径从s到v，必然只有1条是最短的，其他不是最短的路径都要去掉，必然就构成了一颗最短路径树。
 *
 * 数据结构：
 * edgeTo[v]: 代表从节点0到v的最短路径中末  端指向v的那条边。
 * distTo[v]: 从节点0到v的最短路径的长度（权重）。
 *
 * 如果从 v->w 是捷径，则更新distTo[w], edgeTo[w]
 *
 * 最短路径通用的解法：
 * 首先 初始化距离，起始顶点dist[s]=0, 其他顶点distTo[v] = ∞
 * 然后遍历直到满足最优条件
 * - relax更新捷径
 *
 * Relax edge e = v→w.
 * ・ distTo[v] is length of shortest known path from s to v.
 * ・ distTo[w] is length of shortest known path from s to w.
 * ・ edgeTo[w] is last edge on shortest known path from s to w.
 * ・If e = v→w gives shorter path to w through v,
 * update both distTo[w] and edgeTo[w].
 *
 * Generic shortest-paths algorithm:
 * Initialize distTo[s] = 0 and distTo[v] = ∞ for all other vertices.
 * Repeat until optimality conditions are satisfied:
 * - Relax any edge.
 *
 * Dijkstra短路径算法
 * ・Consider vertices in increasing order of distance from s (non-tree vertex with the lowest distTo[] value).
 * ・Add vertex to tree and relax all edges pointing from that vertex.
 * 将所有顶点按照s到他距离从小到大排序
 * 将节点添加到树中，并将该节点指向的边全都relax更新
 *
 *
 * </pre
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class DijkstraShortestPaths {

    private double distTo[];
    private DirectedEdge edgeTo[];
    private IndexMinPQ<Double> pq; //index:vertex index, key: vertex distance from s
    /**
     *
     * @param G edge weighted digraph
     * @param s start point
     */
    DijkstraShortestPaths(EdgeWeightedDigraph G, int s) {

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < G.V(); v++) {
            pq.insert(v, Double.POSITIVE_INFINITY);
        }

        pq.insert(s, 0.0);

        while(!pq.isEmpty()) {
            int min = pq.delMin();
            for (DirectedEdge e : G.adj(min)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();

        //trough v to w (from s) is a shorter path
        double newWeight = distTo[v] + e.weight();
        if (newWeight < distTo[w]) {
            edgeTo[w] = e;
            distTo[w] = newWeight;

            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
}
