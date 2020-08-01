package week2.interview;

/**
 * https://leetcode.com/problems/sort-colors/
 * <p>
 * 75. Sort Colors Given an array with n objects colored red, white or blue, sort them in-place so that objects of the
 * same color are adjacent, with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note: You are not suppose to use the library's sort function for this problem.
 * <p>
 * Example:
 * <p>
 * Input: [2,0,2,1,1,0] Output: [0,0,1,1,2,2] Follow up:
 * <p>
 * A rather straight forward solution is a two-pass algorithm using counting sort. First, iterate the array counting
 * number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's. Could you
 * come up with a one-pass algorithm using only constant space?
 *
 * @author 张义 johnraychina@163.com
 */
public class SortColors {

    // Hint: 3-way partitioning.
    public static void sort(int[] colorArray) {
        int lo = 0;
        int mid = 0;
        int hi = colorArray.length - 1;

        while (mid < hi) {
            switch (colorArray[mid]) {
                case 0:
                    swap(colorArray, mid, lo++);
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(colorArray, mid, hi--);
                    break;
            }
        }
    }

    private static void swap(int[] colorArray, int a, int b) {
        int temp = colorArray[a];
        colorArray[a] = colorArray[b];
        colorArray[b] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 0, 1, 2, 2, 1, 0, 0};
        sort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }
}
