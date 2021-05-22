package 每日一题;

public class 寻找两个正序数组中的中位数4 {
    public static void main(String[] args) {
    }

    public double findMedianSortedArray(int[] nums1, int[] nums2) {

        //1. 合并后做二分查找

        //2. 分别对两个数组做二分查找:
        // 两个数组里面的元素有以下几种情况：

        // a. num1 与 nums2 没有任何交叉:
        // 令 mid = (数组元素个数)/2，如果较小数组元素个数比较多超过了半数，则取较小数组[mid], 如果较小数组个数少于半数，则取较大数组[mid- num.length]

        // b. num1 与 nums2 存在包含关系:
        // 采用双指针技术，分别做折半查找， mid1, mid2, 再看

        // c. num1 与 nums2 交叉


        //todo
        return 0;
    }
}
