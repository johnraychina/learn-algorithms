package 剑指offer;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 *
 * @author Zhang Yi
 */
public class 二维数组中的查找 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        //根据输入矩阵的排序特性，可以知道，左上角matrix[0][0]最小，右下角matrix[n-1][m-1]最大
        //问题是一个search问题，怎么样找到最优搜索方法呢

        //将搜索问题转换为排序问题
        //从左上角开始扫描到右下角，重新得到一个一维有序数组
        //对这个序列做二分搜索即可
        // 时间复杂度为O(logN)，空间复杂度O(N),其中N=m*n
        int rows = matrix.length;
        int cols = matrix[0].length;
        int N = rows * cols;
        int[] a = new int[N];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            //for (int j = 0; j < cols; j++) {
            //    a[k++] = matrix[i][j];
            //}
            System.arraycopy(matrix[i], 0, a, i * cols, cols);
        }

        Arrays.sort(a);
        return Arrays.binarySearch(a, target) >= 0;
    }

}
