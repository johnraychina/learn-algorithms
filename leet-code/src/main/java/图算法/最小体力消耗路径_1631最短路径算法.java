package 图算法;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最短路径
 * <pre>
 *
 * 思路与算法
 * 「最短路径」使得我们很容易想到求解最短路径的 \texttt{Dijkstra}Dijkstra 算法，然而本题中对于「最短路径」的定义不是其经过的所有边权的和，而是其经过的所有边权的最大值，那么我们还可以用 \texttt{Dijkstra}Dijkstra 算法进行求解吗？
 * 答案是可以的。\texttt{Dijkstra}Dijkstra 算法本质上是一种启发式搜索算法，它是 \texttt{A*}A* 算法在启发函数 h \equiv 0h≡0 时的特殊情况。读者可以参考 A* search algorithm，Consistent heuristic，Admissible heuristic 深入了解 \texttt{Dijkstra}Dijkstra 算法的本质。
 * 下面给出 \texttt{Dijkstra}Dijkstra 算法的可行性证明，需要读者对 \texttt{A*}A* 算法以及其可行性条件有一定的掌握。
 * 证明
 * 定义加法运算 a \oplus b = \max (a,b)a⊕b=max(a,b)，显然 \oplus⊕ 满足交换律和结合律。那么如果一条路径上的边权分别为 e_0, e_1, \cdots, e_ke
 * 0
 * ​
 *  ,e
 * 1
 * ​
 *  ,⋯,e
 * k
 * ​
 *  ，那么 e_0 \oplus e_1 \oplus \cdots \oplus e_ke
 * 0
 * ​
 *  ⊕e
 * 1
 * ​
 *  ⊕⋯⊕e
 * k
 * ​
 *   即为这条路径的长度。
 *
 * 在 \texttt{Dijkstra}Dijkstra 算法中 h \equiv 0h≡0，对于图中任意的无向边 x \leftrightarrow yx↔y，由于 e_{x, y} \geq 0e
 * x,y
 * ​
 *  ≥0，那么 h(x)=0\leq e_{x,y} \oplus h(y)h(x)=0≤e
 * x,y
 * ​
 *  ⊕h(y) 恒成立，其中 e_{x, y}e
 * x,y
 * ​
 *   表示边权。因此启发函数 hh 和加法运算 \oplus⊕ 满足三角不等式，是 consistent heuristic 的，可以使用 \texttt{Dijkstra}Dijkstra 算法求出最短路径。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class 最小体力消耗路径_1631最短路径算法 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge[2]));
        pq.offer(new int[] {0, 0, 0});

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (seen[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            seen[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]))
                    < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[] {nx, ny, dist[nx * n + ny]});
                }
            }
        }

        return dist[m * n - 1];
    }

}
