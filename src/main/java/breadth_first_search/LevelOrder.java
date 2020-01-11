package breadth_first_search;

//102 二叉树层序遍历

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LevelOrder {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        return fun(root);
    }

    public List<List<Integer>> fun(TreeNode root) {
        //List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<List<Integer>> resultList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedBlockingQueue();
        if (root == null) {
            return resultList;
        }
        queue.add(root);
        while(!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> temp = new ArrayList<>();
            while(n > 0) {
                n--;
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            //resultList.add(temp);
            resultList.addFirst(temp);
        }
        //因为要求按照从下到上输出，所以这里做一下翻转
        //Collections.reverse(resultList);

        return resultList;
    }
}
