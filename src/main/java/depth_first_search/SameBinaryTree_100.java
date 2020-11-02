package depth_first_search;

import java.util.Stack;

//100 相同的树

/**
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   3     2   3
 * <p>
 * [1,2,3],   [1,2,3]
 * <p>
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:      1          1
 * /           \
 * 2             2
 * <p>
 * [1,2],     [1,null,2]
 * <p>
 * 输出: false
 * 示例 3:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * 输出: false
 */
public class SameBinaryTree_100 {
    /**
     * 递归深度优先搜索
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        if (isSameTree1(p.left, q.left)) {
            return isSameTree1(p.right, q.right);
        }

        return false;
    }

    /**
     * 非递归深度优先
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        if (p != null) {
            stackP.add(p);
        }
        if (q != null) {
            stackQ.add(q);
        }
        while (!stackP.empty() && !stackQ.empty()) {
            TreeNode nodeA = stackP.pop();
            TreeNode nodeB = stackQ.pop();
            if (nodeA.val != nodeB.val) {
                return false;
            }
            if (nodeA.right != null && nodeB.right != null) {
                stackP.add(nodeA.right);
                stackQ.add(nodeB.right);
            } else if ((nodeA.right == null && nodeB.right != null)
                    || (nodeA.right != null && nodeB.right == null)) {
                return false;
            }
            if (nodeA.left != null && nodeB.left != null) {
                stackP.add(nodeA.left);
                stackQ.add(nodeB.left);
            } else if ((nodeA.left == null && nodeB.left != null)
                    || (nodeA.left != null && nodeB.left == null)) {
                return false;
            }
        }
        if (!stackP.empty() || !stackQ.empty()) {
            return false;
        }
        return true;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }
}
