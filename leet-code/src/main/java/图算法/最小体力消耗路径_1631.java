package 图算法;

/**
 * <pre>
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 *
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 *
 *
 *
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 *
 *
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *  
 *
 * 提示：
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 最小体力消耗路径_1631 {

    static int M = 0; // row count
    static int N = 0; // column count
    static int V = 0; // vertices count

    public static int minimumEffortPath_DP(int[][] heights) {

        //问题关键：一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
        //目标：返回左上角走到右下角的最小 体力消耗值 。

        //问题-->图算法：我们将格子作为顶点，相邻格子之间的【高度差绝对值】作为【无向边】的权重
        //一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

        //动态规划，状态转移，
        //从顶点0(0, 0) -> v(m,n): f(m, n)
        //dp(m, n) =
        //max{ dp(m-1, n), 到上边格子的差值 }
        //max{ dp(m+1, n), 到下边格子的差值 }
        //max{ dp(m, n-1), 到左边格子的差值 }
        //max{ dp(m, n+1), 到右边格子的差值 }
        M = heights.length;
        N = heights[0].length;
        V = M * N;

        //我们先构造顶点和对应的边，顶点数=m*n
        //我们用一个数组来表示两个顶点之间的边权重，顶点u和v边的权重w表示为： weight[u][v] = w
        //顶点只有上下左右4个相邻的订单，weight数组太稀疏了，这么做非常浪费空间
        //我们优化为：对于顶点u
        //weight[u][0] = weight[u][u上面的格子]
        //weight[u][1] = weight[u][u下面的格子]
        //weight[u][2] = weight[u][u左边的格子]
        //weight[u][3] = weight[u][u右边的格子]
        int[][] weight = new int[V][4];

        //我们需要一个办法，将m*n中二维坐标u(i, j) 转换为一维数组整形的顶点u：u = i*n + j

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int v = toVertex(i, j);

                //上
                if (i != 0) {
                    weight[v][0] = heights[i][j] - heights[i - 1][j];
                } else {
                    weight[v][0] = Integer.MAX_VALUE;//最大值代表从上面的格子不可达
                }
                //下
                if (i != M - 1) {
                    weight[v][0] = heights[i][j] - heights[i + 1][j];
                } else {
                    weight[v][0] = Integer.MAX_VALUE;//最大值代表从上面的格子不可达
                }

            }
        }

        return 0; //todo
    }

    public static int minimumEffortPath_Graph(int[][] heights) {

        //问题关键：一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
        //目标：返回左上角走到右下角的最小 体力消耗值 。

        //问题-->图算法：我们将格子作为顶点，相邻格子之间的【高度差绝对值】作为【无向边】的权重
        //一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
        M = heights.length;
        N = heights[0].length;
        V = M * N;

        //我们先构造顶点和对应的边，顶点数=m*n
        //我们用一个数组来表示两个顶点之间的边权重，顶点u和v边的权重w表示为： weight[u][v] = w
        //顶点只有上下左右4个相邻的订单，weight数组太稀疏了，这么做非常浪费空间
        //我们优化为：对于顶点u
        //weight[u][0] = weight[u][u上面的格子]
        //weight[u][1] = weight[u][u下面的格子]
        //weight[u][2] = weight[u][u左边的格子]
        //weight[u][3] = weight[u][u右边的格子]
        int[][] weight = new int[V][4];

        //我们需要一个办法，将m*n中二维坐标u(i, j) 转换为一维数组整形的顶点u：u = i*n + j

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int v = toVertex(i, j);

                //上
                if (i != 0) {
                    weight[v][0] = heights[i][j] - heights[i - 1][j];
                } else {
                    weight[v][0] = Integer.MAX_VALUE;//最大值代表从上面的格子不可达
                }
                //下
                if (i != M - 1) {
                    weight[v][0] = heights[i][j] - heights[i + 1][j];
                } else {
                    weight[v][0] = Integer.MAX_VALUE;//最大值代表从上面的格子不可达
                }

            }
        }

        return -1; //todo
    }

    /**
     * Convert vertex (i, j) into one-dimensional array index.
     *
     * @param i row index
     * @param j column index
     * @return vertex index
     */
    public static int toVertex(int i, int j) {
        return i * N + j;
    }

    public static int row(int u) {
        return u / N;
    }

    public static int col(int u) {
        return u % N;
    }

    /**
     * @param u The cell index of vertices array
     * @return The upper cell index of vertices array, -1 for up-flow
     */
    public static int up(int u) {
        if (u > N - 1) { return u - N; } else { return -1; }
    }

    /**
     * @param u The cell index of vertices array
     * @return The down cell index of vertices array, -1 for down-flow
     */
    public static int down(int u) {
        if (u + N < V) { return u + N; } else { return -1; }
    }

    public static int left(int u) {
        int col = u % N;
        if (col > 0) { return u - 1; } else { return -1; }
    }

    public static int right(int u) {
        int col = u % N;
        if (col + 1 < N) { return u + 1; } else { return -1; }
    }
}










