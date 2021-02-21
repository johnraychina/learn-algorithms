package 树问题;

import java.util.HashMap;

/**
 * <pre> 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder =[3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 从前序与中序遍历序列构造二叉树_105中等 {

    public static HashMap<Integer, Integer> inorderValueIndexMap;

    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7});

        System.out.println(treeNode);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;

        //加速查找root index
        inorderValueIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorderValueIndexMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    /**
     * Build tree recursively
     *
     * @param preorder preorder array
     * @param preLeft  preorder left
     * @param preRight preorder right
     * @param inorder  inorder array
     * @param left     inorder left
     * @param right    inorder right
     * @return root
     */
    private static TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int left, int right) {
        //递归结束条件
        if (left > right) {
            return null;
        } else if (left == right) {
            return new TreeNode(inorder[left], null, null);
        }

        int inorderRootIndex = inorderValueIndexMap.get(preorder[preLeft]);

        //前序和中序，子树节点个数是相同的：
        //preorder: [preLeft [..left part..] [...right part...]]
        //inorder: [[...left part...] root [...right part...]]
        int leftCount = inorderRootIndex - left;

        //然后rootIndex将inorder分左右子树，分别递归处理
        TreeNode leftNode = buildTree(preorder, preLeft + 1, preLeft + leftCount, inorder, left, inorderRootIndex - 1);
        TreeNode rightNode = buildTree(preorder, preLeft + leftCount + 1, preRight, inorder, inorderRootIndex + 1,
            right);
        TreeNode rootNode = new TreeNode(inorder[inorderRootIndex], leftNode, rightNode);
        rootNode.left = leftNode;
        rootNode.right = rightNode;
        return rootNode;
    }

}
