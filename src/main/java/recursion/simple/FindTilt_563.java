package recursion.simple;

import common.TreeNode;

/**
 * 563. 二叉树的坡度
 * <p>
 * 给定一个二叉树，计算 整个树 的坡度 。
 * <p>
 * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ； 没有右子树的话也是一样。空结点的坡度是 0 。
 * <p>
 * 整个树 的坡度就是其所有节点的坡度之和。
 * <p>
 * 提示：
 * <p>
 * 树中节点数目的范围在 [0, 104] 内 -1000 <= Node.val <= 1000
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/binary-tree-tilt 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTilt_563 {

    public int findTilt(TreeNode root) {
        fun(root);
        return result;
    }

    int result = 0;

    private int fun(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = fun(root.left);
        int right = fun(root.right);
        result += Math.abs(left - right);
        return left + right + root.val;
    }
}
