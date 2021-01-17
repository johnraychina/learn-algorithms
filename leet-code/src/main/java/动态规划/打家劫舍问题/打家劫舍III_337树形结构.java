package 动态规划.打家劫舍问题;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Yi
 */
public class 打家劫舍III_337树形结构 {
    private static Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        //遍历结束条件
        if (root == null) {
            return 0;
        }
        //用备忘录来减少遍历，保证每个节点就遍历一次
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        //树形结构采用dfs递归遍历
        //抢root: 后续只能抢孙子节点
        //不抢root: 后续抢子节点
        int robRoot = root.val
            + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
            + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

        int dontRobRoot = rob(root.left) + rob(root.right);

        //由于两种case遍历的时候，会产生重叠，利用备忘录来减少遍历，保证每个节点就遍历一次
        int res = Math.max(robRoot, dontRobRoot);
        memo.put(root, res);
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

}
