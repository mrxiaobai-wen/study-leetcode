package depth_first_search;

//104 二叉树的最大深度

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeMaxDepth_104 {
    public int maxDepth(TreeNode root) {
        //递归实现
        return fun1(root);
    }

    /**
     * 方法一：采用递归实现
     * 思路：以当前节点出发，选择左右子树中最大深度值加上当前节点，即是以该节点为跟节点的最大深度
     * @param root
     * @return
     */
    private int fun1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Integer.max(fun1(root.left),fun1(root.right)) + 1;
    }

    /**
     * 方法二：采用非递归实现
     * 思路：一层一层往下淘汰，直到最后就是最大层级
     * @param root
     * @return
     */
    private int fun2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> currentList = new ArrayList<>();
        List<TreeNode> nextList = new ArrayList<>();
        currentList.add(root);
        int level = 0;
        while(true) {
            if (currentList.isEmpty())
                break;
            level++;
            while(!currentList.isEmpty()) {
                TreeNode node = currentList.remove(0);
                if (node.left != null) {
                    nextList.add(node.left);
                }
                if (node.right != null) {
                    nextList.add(node.right);
                }
            }
            currentList.addAll(nextList);
            nextList.clear();
        }
        return level;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
