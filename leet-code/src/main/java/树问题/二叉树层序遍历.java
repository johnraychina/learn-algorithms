package 树问题;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树层序遍历 {

    public List<TreeNode> traverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> res = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            // queue最多放两层数据，用size分隔两部分数据
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                res.add(node);
                System.out.printf("节点 %s 在第 %s 层", node, depth);

                if (node.left != null)  { queue.offer(node.left); }
                if (node.right != null)  { queue.offer(node.right); }
            }
            depth++;
        }

        return res;
    }
}
