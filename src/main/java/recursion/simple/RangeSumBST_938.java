package recursion.simple;

import common.TreeNode;

import java.util.Stack;

/**
 * 938. 二叉搜索树的范围和
 * <p>
 * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
 * <p>
 * 二叉搜索树保证具有唯一的值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 * 示例 2：
 * <p>
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 * <p>
 * 提示：
 * <p>
 * 树中的结点数量最多为 10000 个。
 * 最终的答案保证小于 2^31。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RangeSumBST_938 {

    public int rangeSumBST(TreeNode root, int L, int R) {
        //fun(root,L,R);
        //return result;
        return fun2(root, L, R);
    }

    /**
     * 迭代
     */
    private int fun2(TreeNode root, int l, int r) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        int result = 0;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (l <= cur.val && cur.val <= r) {
                result += cur.val;
            }
            if (l < cur.val && cur.left != null) {
                stack.add(cur.left);
            }
            if (r > cur.val && cur.right != null) {
                stack.add(cur.right);
            }
        }
        return result;
    }

    int result = 0;

    /**
     * 递归
     */
    private void fun(TreeNode root, int L, int R) {
        if (root == null) {
            return;
        }
        if (L <= root.val && root.val <= R) {
            result += root.val;
        }
        if (L < root.val) {
            fun(root.left, L, R);
        }
        if (R > root.val) {
            fun(root.right, L, R);
        }
    }
}
