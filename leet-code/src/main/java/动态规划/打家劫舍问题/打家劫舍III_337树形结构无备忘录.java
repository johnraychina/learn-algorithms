package 动态规划.打家劫舍问题;

/**
 * @author Zhang Yi
 */
public class 打家劫舍III_337树形结构无备忘录 {
    public int rob(TreeNode root) {
        int[] robAmount = robDP(root);
        return Math.max(robAmount[0], robAmount[1]);
    }

    //private static Map<TreeNode, Integer> memo = new HashMap<>();

    //返回dp数组，不抢root的总收益，抢root的总收益
    //dp[0]:不抢root的总收益， dp[1]:抢root的总收益
    public int[] robDP(TreeNode root) {
        //遍历结束条件
        if (root == null) {
            return new int[] {0, 0};
        }

        //树形结构采用dfs递归遍历
        int[] left = robDP(root.left);
        int[] right = robDP(root.right);
        //不抢root: 后续可以抢子节点，也可以不抢，取决于收益大小
        int dontRobRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //抢root: 不能抢子节点
        int robRoot = root.val + left[0] + right[0];

        return new int[] {dontRobRoot, robRoot};
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
