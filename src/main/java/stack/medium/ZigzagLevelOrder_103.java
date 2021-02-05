package stack.medium;

import common.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如： 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3 / \ 9  20 /  \ 15   7 返回锯齿形层序遍历如下：
 * <p>
 * [ [3], [20,9], [15,7] ]
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZigzagLevelOrder_103 {

    /**
     * 节点层序遍历，但是结果按照层次判断正序存储还是反序存储
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int level = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> resultList = new ArrayList<>();
            LinkedList<TreeNode> tempQueue = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (level % 2 != 0) {
                    resultList.add(curNode.val);
                } else {
                    resultList.add(0, curNode.val);
                }
                if (curNode.left != null) {
                    tempQueue.add(curNode.left);
                }
                if (curNode.right != null) {
                    tempQueue.add(curNode.right);
                }
            }
            level++;
            queue = tempQueue;
            result.add(resultList);
        }

        return result;
    }

}
