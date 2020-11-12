import algs4.In;
import algs4.StdOut;

/**
 * <pre>
 *
 * 问题：判断两个点之间是否联通？是否存在孤岛节点？有多少个互不相连的节点集合（连通分量）？
 * 问题领域包括：
 * 图像处理
 * 网络连接问题
 * 社交网络
 * 芯片半导体设计
 * 数学集合问题
 *
 * union find 并查集
 * 通过将节点连通成树形结构，解决连通性问题。
 *
 * Connected Components 连通分量
 *
 * 对"连接"建模：
 * 我们假设"连接"是对等的，那么有以下性质：
 * - 自反reflective
 * - 对称symmetric
 * - 传播transitive
 *
 * 数据结构：
 * parent[] 数组，对于节点i，parent[i]指向parent节点（初始化为i）
 *
 * 方案一：QuickFindUF 平铺模式，指向同一个parent的节点表示连通，查询快，合并慢
 * union(a,b)合并两个连通分量时，需要for循环将parent = parent[a]的，全部指向parent[b]，时间复杂度O(N)
 * root(i)直接取parent[i]，时间复杂度O(1)
 * connected(a,b)判断两个节点是否连通时，判断parent[a]==parent[b]即可，时间复杂度O(1)
 *
 * 方案二：QuickUnionUF 树形结构，指向同一个root的节点表示连通，查询慢，合并快
 * union(a,b)操作非常简单：只需要将两个树合并即可: parent[rootA] = b，合并本身时间复杂度O(1)，但是要找到rootA就要O(N)
 * root(i) 取root，while循环不断取parent，直到没有parent，时间复杂度O(N)
 * connected(a,b)判断两个节点是否连通时，判断root是不是同一个即可
 *
 * 观察：树形结构退化为链表，如何优化呢？
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class QuickUnionUF {

    int[] parent;
    int count;

    QuickUnionUF(int n) {
        parent = new int[n];
        count = n;
        //初始化为i
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public static void main(String[] args) {
        In in = new In("largeUF.txt");
        int n = in.readInt();
        QuickUnionUF uf = new QuickUnionUF(n);
        while (!in.isEmpty()) {

            int p = in.readInt();
            int q = in.readInt();
            if (uf.root(p) == uf.root(q)) { continue; }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

    void union(int a, int b) {
        //合并的时候一定要通过根节点合并，否则会破坏原来a, b所在的2个子树形结构
        //parent[a] = b; wrong!!
        int rootA = root(a);
        int rootB = root(b);
        if (rootA == rootB) { return; }
        parent[rootA] = rootB;//原本a的根节点的parent改为指向b的根节点
        count--;
    }

    boolean connected(int a, int b) {
        return root(a) == root(b);
    }

    /**
     * @param a
     * @return component identifier for a (0~N-1)
     */
    int root(int a) {
        //find root: parent[...parent[a]]
        while (parent[a] != a) {
            a = parent[a];
        }
        return a;
    }

    /**
     * @return Number of components
     */
    int count() {
        return count;
    }

}
