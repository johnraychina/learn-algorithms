package 树问题;

/**
 * <pre> 654. 最大二叉树
 * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 *
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 最大二叉树_654中等 {
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private static int indexOfMax(int[] nums, int left, int right) {
        int indexOfMax = left;
        int max = nums[indexOfMax];
        for (int i = left; i <= right; i++) {
            if (nums[i] > max) { //不含重复元素，所以我们可以这么判断
                indexOfMax = i;
                max = nums[indexOfMax];
            }
        }

        return indexOfMax;
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return new TreeNode(nums[left], null, null);
        }

        int indexOfMax = indexOfMax(nums, left, right); //indexOfMax: left==right, left==(right+1), left==(right+x)
        TreeNode leftTree = constructMaximumBinaryTree(nums, left, indexOfMax - 1);
        TreeNode rightTree = constructMaximumBinaryTree(nums, indexOfMax + 1, right);
        TreeNode root = new TreeNode(nums[indexOfMax], null, null);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};
        System.out.println(constructMaximumBinaryTree(nums));
    }
}
