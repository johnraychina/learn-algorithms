package 每日一题;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * <p>
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1,col1) ，右下角为 (row2,col2) 。
 * 上图子矩阵左上角(row1, col1) = (2, 1)，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二维区域和检索304_二维前缀和 {

    private int[][] sum;

    public 二维区域和检索304_二维前缀和(int[][] matrix) {

        // 暴力求解
        // 根据提示，若干多次调用，可以优化思路：
        // 复用（记忆）计算结果，范围相减，类似于1D的 sum[i:j] = sum[0:j] - sum[0:i-1]
        // sum[r1:r2][c1:c2] = sum[0:r2][0:c2] - sum[0:r1][0:c2] - sum[0:r2][0:c1] + sum[0:r1][0:c1]
        // 考虑边界条件，为了避免递归时出现-1下标，统一+1，s 表示坐标[r][c]左上角的和，不含r行、c列。
        int rows = matrix.length;
        int cols = matrix[0].length;
        sum = new int[rows + 1][cols + 1];
        //sum[0][0] = matrix[0][0];
        sum[0][0] = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
            }
        }
    }


    public int sumRegion(int row1, int col1, int row2, int col2) {
//        int a = sum[row1-1][col2];
//        int b = sum[row2][col1-1];
//        int c = sum[row1-1][col1-1];
//        return sum[row2][col2] - a - +c;
        int a = sum[row1][col2 + 1];
        int b = sum[row2 + 1][col1];
        int c = sum[row1][col1];
        return sum[row2 + 1][col2 + 1] - a - b + c;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        二维区域和检索304_二维前缀和 s = new 二维区域和检索304_二维前缀和(matrix);
//        System.out.println(s.sumRegion(0, 0, 1, 1));
//        System.out.println(s.sumRegion(0, 0, 2, 2));
        System.out.println(s.sumRegion(2, 1, 4, 3));
        System.out.println(s.sumRegion(1, 1, 2, 2));
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
