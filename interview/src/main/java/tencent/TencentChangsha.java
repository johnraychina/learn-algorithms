package tencent;

/**
 * @author Zhang Yi
 */
public class TencentChangsha {

    private static final int MAX = 10000;

    public static void main(String[] args) {

        //int pos =  (int) (20 * 0.9999999999);
        int[][] map = generateMap(4, 5, 5);
        System.out.println("....");
    }

    /**
     * 扫雷游戏，在m*n的矩阵里，生成k个雷的随机地图 1 有地雷 0 无地雷
     *
     * @param m m
     * @param n n
     * @param k k
     * @return m*n矩阵
     */
    public static int[][] generateMap(int m, int n, int k) {

        //参数校验
        if (m <= 0 || m > MAX) {
            throw new IllegalArgumentException("m must between 0 and" + MAX);
        }
        if (n <= 0 || n > MAX) {
            throw new IllegalArgumentException("n must between 0 and" + MAX);
        }
        if (k <= 0 || k >= m * n) {
            throw new IllegalArgumentException("K must between 0 and" + m * n);
        }

        int[][] map = new int[m][n];

        //避免溢出
        int randMax = m * n;

        //for (int i = 0; i < m; i++) {
        //    for (int j = 0; j < n; j++) {
        //        map[i][j] = 0;
        //    }
        //}

        //Random random = new Random(0);
        for (int x = 0; x < k; x++) {
            //随机数坐标： [0.0~ 1.0) * randMax20
            int pos = getRandPos(randMax);
            int row = (pos / n);
            int col = pos % n;
            while (map[row][col] == 1) {
                pos = getRandPos(randMax);
                row = (pos / n);
                col = pos % n;
            }
            map[row][col] = 1;
        }

        return map;
    }

    private static int getRandPos(int randMax) {
        double random = Math.random();
        //假设randMax=4*5=20, random永远到不了1，[0, 1.0) * 20 = [0, 20)
        // 加0.1得到 [0.1, 20.1)
        // 转int得到 [0, 20]
        return (int)(random * randMax + 0.1);
    }

}
