package 图算法;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 最小体力消耗路径_1631并查集 {

    public static void main(String[] args) throws InterruptedException, IOException {
        int[][] heights1 = {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};
        int[][] heights2 = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};

        //用 Arthas profiler 分析性能
        //https://arthas.aliyun.com/doc/profiler.html
        System.err.println("按任意键开始...");
        System.in.read(); //先启动应用，在这里等待用户输入

        for (int i = 0; i < 10000; i++) {
            System.out.println(minimumEffortPath(heights1));
            System.out.println(minimumEffortPath(heights2));
        }
        System.err.println("按任意键结束...");
        System.in.read(); //在这里等待用户输入，先profiler stop生成火焰图，然后这里再继续走
    }

    /**
     * 将m * n个节点加入并查集，实时维护连通性。 由于我们需要找到从左上角到右下角的最小体力消耗路径（路径高度差最小），我们可以按照权重将所有的边按从小到大排序，
     * 然后把边逐个加入并查集，假设当前加入的边权重为w，如果加入这条边正好从左上角到右下角变为连通状态，那么w即为答案。
     * <p>
     * 复杂度分析： 空间复杂度：边数和并查集所需空间O(mn) 时间复杂度：边排序O(mnlog(mn)), 并查集合并+连通性判断O(mn)
     *
     * @param heights
     * @return
     */
    public static int minimumEffortPath(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        //将顶点(i, j)的二维坐标变为一维坐标: v = i * n + j;
        //起点：(0, 0) ==> 0
        //终点：(m - 1, n - 1) ==> m*n -1

        //提取边
        int E = (m - 1) * n + m * (n - 1);
        List<int[]> edges = new ArrayList<>(E);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = i * n + j; // 2D coordinate to 1D vertex index
                if (i > 0) {
                    int h = Math.abs(heights[i][j] - heights[i - 1][j]);
                    edges.add(new int[] {v - n, v, h});
                }
                if (j > 0) {
                    int h = Math.abs(heights[i][j] - heights[i][j - 1]);
                    edges.add(new int[] {v - 1, v, h});
                }
            }
        }

        //按边的权重排序
        edges.sort(Comparator.comparingInt(o -> o[2]));

        //将边对应的顶点，使用并查集判断起点0与终点(m*n -1) 的连通性
        QuickUnionUF uf = new QuickUnionUF(m * n);
        int minEffort = 0;
        for (int i = 0; i < E; i++) {
            int[] edge = edges.get(i);
            uf.union(edge[0], edge[1]);
            if (uf.connected(0, m * n - 1)) {
                minEffort = edge[2];
                break;
            }
        }

        return minEffort;
    }

    //并查集数据结构，QuickFind查询快合并慢，QuickUnion合并快查询慢
    //并查集数据结构，QuickFind查询快合并慢，QuickUnion合并快查询慢
    static class QuickUnionUF {
        int[] id;
        int[] size;
        int count; //connected component count

        public QuickUnionUF(int n) {
            id = new int[n];
            size = new int[n];
            count = n;
            //init
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        public int root(int v) {
            while (id[v] != v) {
                v = id[v];
            }
            return v;
        }

        public void union(int x, int y) {
            int xRoot = root(x);
            int yRoot = root(y);
            if (xRoot == yRoot) { return; }
            id[xRoot] = yRoot;
            count--;
        }

        public boolean connected(int x, int y) {
            return root(x) == root(y);
        }
    }

    static class QuickFindUF {
        int[] id;
        int[] size;
        int count; //connected component count

        public QuickFindUF(int n) {
            id = new int[n];
            size = new int[n];
            count = n;
            //init
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        public int root(int v) {
            return id[v];
        }

        public void union(int x, int y) {
            int xRoot = root(x);
            int yRoot = root(y);
            if (xRoot == yRoot) { return; }

            for (int i = 0; i < id.length; i++) {
                if (id[i] == xRoot) { id[i] = yRoot; }
            }
            count--;
        }

        public boolean connected(int x, int y) {
            return root(x) == root(y);
        }
    }

}
