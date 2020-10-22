/**
 * @author Zhang Yi
 */
public class ShortestPath {
    public static void main(String[] args) {

        int S = 1;
        int V = 9;
        int N = 10;
        // 节点编号从1~N，起始点编号S，终点编号为V
        // edgeTo[j][i] 代表从 j->i 路径长度（权重）, 自己到自己长度为0
        // 这里故意将i,j顺序掉了个头，以便让数组edgeTo[i]代表到i的所有边，其中edgeTo[i][j] 为j->i的长度
        int[][] edgeTo = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) { edgeTo[i][j] = 0; } else {
                    edgeTo[i][j] = Integer.MAX_VALUE; //代表不可达
                }

            }
        }
        edgeTo[2][1] = 3;
        edgeTo[3][1] = 1;
        edgeTo[2][3] = 1;
        edgeTo[9][3] = 2;
        edgeTo[9][2] = 1;

        //初始化：shortestPath[i] = MAX_VALUE 正好代表不可达，还可以被min条件拒绝
        //shortestPath[v] 代表从节点s到节点v的最短路径
        //自己到自己长度为0
        int[] shortestPath = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i == S) { shortestPath[i] = 0; } else { shortestPath[i] = Integer.MAX_VALUE; }
        }

        // shortest path: delta(S, v)
        // solution 1: guess the first edgeTo:
        // shortestPath(S, v) = min{ edgeTo(S, u) + shortestPath(u, v) | (S, u) in edges}
        for (int v = 1; v <= N; v++) {
            int[] edgeToV = edgeTo[v]; //如果改成链表，edgeToV数目不是，而是inDegree(v)
            //子问题求解
            for (int u = 1; u <= N; u++) {
                if (shortestPath[u] != Integer.MAX_VALUE && edgeToV[u] != Integer.MAX_VALUE) {
                    shortestPath[v] = Math.min(shortestPath[u] + edgeToV[u], shortestPath[v]);
                }
            }
        }

        System.out.printf("1:ShortestPath(%d,%d)=%d \n", S, V, shortestPath[V]);

        // solution 2: guess the last edgeTo:
        // shortestPath(S, v) = min{ shortestPath(S, u) + edgeTo(u, v) | (u,v) in edges}
        //对节点v进行遍历
        //对每条u -> v边进行遍历遍历
        for (int v = N; v <= 1; v++) {
            int[] edgeToV = edgeTo[v]; //如果改成链表，edgeToV数目不是，而是inDegree(v)
            //子问题求解
            for (int u = N; u <= 1; u++) {
                if (shortestPath[u] != Integer.MAX_VALUE && edgeToV[u] != Integer.MAX_VALUE) {
                    shortestPath[v] = Math.min(shortestPath[u] + edgeToV[u], shortestPath[v]);
                }
            }
        }

        System.out.printf("2:ShortestPath(%d,%d)=%d \n", S, V, shortestPath[V]);
    }
}
