package com.zhangyi.graph;

import algs4.Edge;
import algs4.EdgeWeightedGraph;
import algs4.MinPQ;
import algs4.Queue;

/**
 * KruskalMST从边的角度来思考生成过程 PrimMST从顶点的角度来思考生成过程
 * <pre>
 * 从顶点0开始，生成树T <br>
 * 从只有一个点再T中的边中,选出最小的一条边，添加到T中<br>
 *   一直重复直到添加了V-1条边。<br>
 * <p>
 * 挑战：找到只有一个点再T中的边中，选出最小的边。
 * <p>
 * Prim's MST algorithm: Start with vertex 0 and greedily grow tree T.
 * Add to T the min weight edge with exactly one
 * endpoint in T.
 * ・Repeat until V - 1 edges.
 * <p>
 * Challenge: Find the min weight edge with exactly one endpoint in T.
 * <p>
 * Lazy solution.
 * Maintain a PQ of edges with (at least) one endpoint in T.
 * ・Key = edge;priority = weight of edge.
 * ・Delete-min to determine next edge e = v–w to add to T.
 * ・Disregard if both endpoints v and w are marked (both in T).
 * ・Otherwise, let w be the unmarked vertex (not in T ):
 *      – add to PQ any edge incident to w (assuming other endpoint not in T) – add e to T and mark w
 * <pre>
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class PrimMST {

    private Queue<Edge> mst;
    private MinPQ<Edge> pq;
    private boolean[] marked;

    public PrimMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        visit(G, 0);

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge min = pq.delMin();
            int v = min.either();
            int w = min.other(v);

            //当前最小边，如果有一个顶点不在mst中，则将其纳入mst中，且将改顶点邻边加入优先级队列备用
            if (!marked[v]) {
                mst.enqueue(min);
                visit(G, w);
            } else if ((!marked[w])) {
                mst.enqueue(min);
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> mst() {
        return mst;
    }

}
