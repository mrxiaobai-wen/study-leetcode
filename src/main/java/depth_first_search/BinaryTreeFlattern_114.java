package depth_first_search;

/**
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeFlattern_114 {
    /**
     * 原地展开，不能开辟新的空间！！！
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        fun1(root);
    }

    private TreeNode tail = null;

    private void fun1(TreeNode curNode) {
        if (curNode == null) {
            return;
        }
        if (tail == null) {
            tail = curNode;
        } else {
            tail.right = curNode;
            tail.left = null;
            tail = curNode;
        }
        TreeNode rightNode = curNode.right;
        if (curNode.left != null) {
            TreeNode leftNode = curNode.left;
            curNode.right = leftNode;
            fun1(leftNode);
        }
        if (rightNode != null) {
            fun1(rightNode);
        }
    }
}
