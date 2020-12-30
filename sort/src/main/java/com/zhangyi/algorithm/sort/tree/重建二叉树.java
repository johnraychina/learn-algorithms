package com.zhangyi.algorithm.sort.tree;

import java.util.Stack;

/**
 * <pre>
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如，给出
 *
 * 前序遍历 preorder =[3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 重建二叉树 {
    public static void main(String[] args) {
        //int[] preorder = {3, 9, 20, 15, 7};
        //int[] inorder = {9, 3, 15, 20, 7};
        //int[] preorder = {1,2};
        //int[] inorder = {1,2};
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {2, 1, 4, 3, 5};

        //TreeNode root = buildTree(preorder, inorder);
        TreeNode root = buildTreeWithStack(preorder, inorder);
        System.out.println(root);
    }

    //0 <= 节点个数 <= 5000
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) { return null; }
        return buildTree(preorder, 0, inorder, 0, inorder.length - 1);
    }

    /**
     * <pre>
     * 使用栈解决
     * 如果使用栈来解决首先要搞懂一个知识点，就是preorder前序遍历挨着的两个值比如m和n，他们会有下面两种情况之一的关系。
     *
     * 1，n是m左子树节点的值。
     *
     * 2，n是m右
     *
     *
     * 对于第一个知识点我们很容易理解，如果m的左子树不为空，那么n就是m左子树节点的值。
     *
     * 对于第二个问题，如果一个结点没有左子树只有右子树，那么n就是m右子树节点的值，
     * 如果一个结点既没有左子树也没有右子树，那么n就是m某个祖先节点的右节点，我们只要找到这个祖先节点就好办了。
     *
     * 作者：sdwwld
     * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/4chong-jie-fa-di-gui-zhan-dui-lie-by-sdwwld/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * </pre>
     */
    public static TreeNode buildTreeWithStack(int[] preorder, int[] inorder) {
        if (preorder.length == 0) { return null; }
        //stack保存 preorder 当前节点到root之间的链路
        Stack<TreeNode> s = new Stack<>();
        //前序的第一个其实就是根节点
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode cur = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            //第一种情况：如果parent != inorder当前值，说明左子树不为空，则preorder中i为左子树parent
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                s.push(cur);
                cur = cur.left;
            } else {
                //第二种情况：左子树为空，当前节点右子树
                j++;
                //如果一个结点既没有左子树也没有右子树，那么n就是m某个祖先节点的右节点，我们只要找到这个祖先节点就好办了。
                //找到合适的cur，然后确定他的右节点
                while (!s.empty() && s.peek().val == inorder[j]) {
                    cur = s.pop();
                    j++;
                }
                //给cur添加右节点
                cur = cur.right = new TreeNode(preorder[i]);
            }
        }
        return root;
    }

    /**
     * 使用递归切分的办法
     *
     * @param preorder
     * @param parent
     * @param inorder
     * @param leftIdx  inorder左边界
     * @param rightIdx inorder右边界
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int parent, int[] inorder, int leftIdx, int rightIdx) {
        //preorder: 父节点 | 左子树 | 右子树
        //inorder: 左子树 | 父节点 | 右子树
        //利用preorder第一个值，可以将 inorder 切分为两半[left tree, parent, right tree]
        //再利用 inorder，切分preorder: inorder 左/右子树count == preorder 左/右边子树count，可以得到左/右边子树parent

        int parentVal = preorder[parent];

        //找到父节点在inorder中的位置，从将inorder切分[leftIdx, midIdx, rightIdx]
        int midIdx = find(inorder, parentVal);

        //mid中间节点就是父节点
        TreeNode parentNode = new TreeNode(inorder[midIdx]);

        //inorder的左半边父节点在 preorder中的位置 =parent+1
        if (leftIdx < midIdx) {
            parentNode.left = buildTree(preorder, parent + 1, inorder, leftIdx, midIdx - 1);
        }
        //inorder的右半边父节点在 preorder中的位置 =parent+1+leftCount
        int leftCount = midIdx - leftIdx;
        if (rightIdx > midIdx) {
            parentNode.right = buildTree(preorder, parent + 1 + leftCount, inorder, midIdx + 1, rightIdx);
        }

        return parentNode;
    }

    //todo 待优化
    private static int find(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) { return i; }
        }
        return -1;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
