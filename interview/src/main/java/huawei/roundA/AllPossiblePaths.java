package huawei.roundA;

/**
 * 计算所有可能路径
 *
 * @author Zhang Yi
 */
public class AllPossiblePaths {
    public static int allPaths(int m, int n) {
        //由于只能向右移动 或者向下移动，所以到达坐标(m,n)的可能路径：
        //pathCount[m][n] = sum(pathCount[m-1][n], pathCount[m][n-1])
        //初始条件: pathCount[1][1] = 1;
        //边界条件:  1 <= i <=m,  1<= j <= n
        int[][] pathCount = new int[m + 1][n + 1];
        pathCount[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (i - 1 >= 1) {
                    pathCount[i][j] += pathCount[i - 1][j];
                }
                if (j - 1 >= 1) {
                    pathCount[i][j] += pathCount[i][j - 1];
                }
            }
        }

        return pathCount[m][n];
    }

    public static void main(String[] args) {
        System.out.println(allPaths(7, 3));
    }
}
