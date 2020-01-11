package depth_first_search;



//101 对称二叉树

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return fun1(root);
    }

    public boolean fun1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }

        //return isSymmetric1(root.left,root.right);

        return isSymmetric2(root.left,root.right);
    }

    /**
     * 判断是否镜像对称  使用递归
     * 思路：满足镜像对称，即节点A与B对称，且A在左B在右：
     * 那么A的左孩子节点与B的右孩子节点对称，且A的右孩子节点与B的左孩子节点对称
     * @return
     */
    public boolean isSymmetric1(TreeNode a,TreeNode b) {
        if (a == null && b == null) {
            return true;
        } else if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSymmetric1(a.left,b.right) && isSymmetric1(a.right,b.left);
    }

    /**
     * 使用队列使用迭代实现
     * @param left
     * @param right
     * @return
     */
    public boolean isSymmetric2(TreeNode left,TreeNode right) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();

        queue.add(left);
        queue.add(right);
        while(queue.size() > 0) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();
            if (a == null && b == null) {
                continue;
            } else if (a == null || b == null) {
                return false;
            } else if (a.val != b.val) {
                return false;
            }
            if (a.left == null && b.right == null) {
                continue;
            } else if (a.left == null || b.left == null) {
                return false;
            } else {
                queue.add(a.left);
                queue.add(b.right);
            }
            if (a.right == null && b.left == null) {
                continue;
            } else if (a.right == null || b.left == null) {
                return false;
            } else {
                queue.add(a.right);
                queue.add(b.left);
            }
        }

        if (queue.isEmpty()) {
            return true;
        }
        return false;
    }



    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
