import algs4.In;
import algs4.StdOut;

/**
 * <pre>
 * 相对于 {@link QuickUnionUF}
 * 1、使用了辅助数组size优化了union，避免树退化为链表。
 *
 * 2、root(a)查找做了折半处理，将a指向grandparent，对路径进一步压缩。
 *
 * Weighted quick-union. Rather than arbitrarily connecting the second tree to the first for union()
 * in the quick-union algorithm, we keep track of the size of each tree and always connect the smaller
 * tree to the larger. Program WeightedQuickUnionUF.java implements this approach.
 *
 * Weighted quick union overview
 * Weighted quick-union with path compression. There are a number of easy ways to
 * improve the weighted quick-union algorithm further.
 * Ideally, we would like every node to link directly to the root of its tree,
 * but we do not want to pay the price of changing a large number of links.
 * We can approach the ideal simply by making all the nodes that we do examine directly link to the root.
 * </pre>
 *
 * @author Zhang Yi
 */
public class WeightedQuickUF {

    int count;
    int[] parent;
    int[] size; //每个节点大小（节点数）

    WeightedQuickUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];

        //初始化为i
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public static void main(String[] args) {
        In in = new In("largeUF.txt");
        int n = in.readInt();

        WeightedQuickUF uf = new WeightedQuickUF(n);
        //QuickUnionUF uf = new QuickUnionUF(n);

        long start = System.currentTimeMillis();
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.root(p) == uf.root(q)) { continue; }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        long cost = System.currentTimeMillis() - start;
        StdOut.println(uf.count() + " components, cost(ms):" + cost);
    }

    void union(int a, int b) {
        //合并的时候一定要通过根节点合并，否则会破坏原来a, b所在的2个子树形结构
        //parent[a] = b; wrong!!
        int rootA = root(a);
        int rootB = root(b);
        if (rootA == rootB) { return; }

        // todo 判断节点大小，小树挂在大树下，解决树太高的root查询低效的问题
        //Proposition. Depth of any node x is at most lg N.
        //Pf. When does depth of of x increase?
        //Increase by 1 when tree T1 >
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

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
            //查询root(a)时，将a的parent指向grandparent，以此将路径长度折半
            // todo path compression: Make every other node in path point to its grandparent (thereby
            // halving path length).
            parent[a] = parent[parent[a]];
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
