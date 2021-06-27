package shopee;


import common.TreeNode;

public class 翻转二叉树226 {
}



class Solution {
    public TreeNode invertTree(TreeNode root) {

        if (root == null) return null;

        //dfs
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}