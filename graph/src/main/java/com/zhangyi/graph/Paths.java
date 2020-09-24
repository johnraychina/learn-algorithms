package com.zhangyi.graph;

/**
 * <pre>
 * 为什么要有这个类？将图的操作 与 图的数据类型解耦
 * 对图执行一个操作（比如查询s到t可达路径）会有一个中间结果，我们需要一个数据结构来承载，便于后续对结果进行遍历。
 * 所以Paths = 路径操作+路径结果
 *
 * Design Pattern. Decouple graph data type from graph processing.
 * - create a graph object.
 * - pass the graph to a graph-processing routine.
 * - query the graph-processing routine for information.
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public abstract class Paths {

    //find paths in G from source s
    public Paths(Graph G, int s) {

    }

    //is there a path from s to v?
    public abstract boolean path(int v);

    //path from s to v; null if no such path
    public abstract Iterable<Integer> pathTo(int v);
}
