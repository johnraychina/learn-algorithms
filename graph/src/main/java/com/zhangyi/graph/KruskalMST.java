package com.zhangyi.graph;

import java.util.Iterator;

import algs4.Edge;
import algs4.EdgeWeightedGraph;
import algs4.In;
import algs4.MinPQ;
import algs4.Queue;
import algs4.QuickUnionUF;
import algs4.StdOut;

/**
 * <pr>
 * 最小生成树的特性：（如果每条边的权重各不相同）最小生成树是唯一的。
 *
 * Kruskal最小生成树的精髓：按边排序 + 检测环
 * 排序利用了MinPQ堆高效的排序，检测环利用了高效的union find连通组件算法

 * Kruskal's minimum spanning tree:
 * <p>
 * order by edges， add the minimum edge to the tree if not created a cycle.
 *
 * Proposition: Kruskal's algorithm computes MST in time propositionalto ElogE (worst case)
 * operation    frequency   time per operation
 * build pq     1           ElogE
 * delete min   E           logE
 * union        V           logV
 * connected    E           logV
 *
 *
 *
 * </pr>
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class KruskalMST {

    private Queue<Edge> mstEdges = new Queue<>();

    public void KruskalMST(EdgeWeightedGraph G) {
        //we need a minimum priority queue to get minimum edge each time.
        //initialize MinPQ with all edges of the edge weighted graph G.
        MinPQ<Edge> pq = new MinPQ<>(G.E());
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        //quick union to check if the edge creates a cycle or not
        QuickUnionUF uf = new QuickUnionUF(G.V());

        // iterate to get the min edge left in the pq
        // and check if it  creates a cycle: if no cycle then add it to the mst edges.
        // AS the MST.numberOfVertices === G.V()
        // and the MST.numberOfEdges === G.V() - 1
        // we can stop early by checking that.
        while (!pq.isEmpty() && mstEdges.size() < (G.V() - 1)) {
            Edge minEdge = pq.delMin();

            // if both vertices of the edges is in the current connected component
            // then a cycle will be created, or else no cycle.
            int v = minEdge.either();
            int w = minEdge.other(v);
            if (uf.find(v) != uf.find(w)) {
                mstEdges.enqueue(minEdge);
                uf.union(v, w);
            }
        }

    }

    public Iterator<Edge> edges() {
        return mstEdges.iterator();
    }

    /**
     * Unit tests the {@code KruskalMST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        algs4.KruskalMST mst = new algs4.KruskalMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }

}
