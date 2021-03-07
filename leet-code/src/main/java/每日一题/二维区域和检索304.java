package 每日一题;

/**
 * <pre>
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
class NumMatrix {

    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        int ROW = matrix.length;
        if (ROW > 0) {
            int COL = matrix[0].length;

            //sum[i][j]表示从(0,0)~(i,j)的和
            //初始化首行和首列
            sum = new int[ROW + 1][COL + 1];
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    //两个区域叠加后减去重复的部分，加上右下角的一个值
                    sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = new int[][] {
        //    {3, 0, 1, 4, 2},
        //    {5, 6, 3, 2, 1},
        //    {1, 2, 0, 1, 5},
        //    {4, 1, 0, 1, 7},
        //    {1, 0, 3, 0, 5}
        //};
        //NumMatrix numMatrix = new NumMatrix(matrix);
        //System.out.println(numMatrix.sumRegion(2, 1, 4, 3) == 8);
        //System.out.println(numMatrix.sumRegion(1, 1, 2, 2) == 11);
        //System.out.println(numMatrix.sumRegion(1, 2, 2, 4) == 12);

        int[][] matrix = new int[][] {{}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0) == 0);
    }

    //参考303，区域做减法
    public int sumRegion(int row1, int col1, int row2, int col2) {
        //从左上角开始，顺时针，4个点，从(0,0)到四个点围成4个区域
        //region1: (row1 - 1, col1 - 1)
        //region2: (row1 - 1, col2)
        //region3: (row2, col1 - 1)
        //region4: (row2, col2)
        //区域做减法可以得到 area = area4 - area3 - area2 + area1
        //统一+1解决边界判断问题
        int region1 = sum[row1][col1];
        int region2 = sum[row1][col2 + 1];
        int region3 = sum[row2 + 1][col1];
        int region4 = sum[row2 + 1][col2 + 1];
        return region4 - region3 - region2 + region1;
    }
}
