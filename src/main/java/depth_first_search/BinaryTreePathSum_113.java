package depth_first_search;

import depth_first_search.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreePathSum_113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        fun1(root, sum, 0, resultList, tempList);
        return resultList;
    }

    private void fun1(TreeNode curNode, int targetSum, int curSum,
                      final List<List<Integer>> resultList, final List<Integer> tempList) {
        if (curNode == null) {
            return;
        }
        // curNode的值还有可能是负数，同时targetSum也可以是负数！！！
        if (curSum + curNode.val == targetSum) {
            // 要求是到叶子节点
            if (curNode.left == null && curNode.right == null) {
                List<Integer> tempResultList = new ArrayList<>();
                tempResultList.addAll(tempList);
                tempResultList.add(curNode.val);
                resultList.add(tempResultList);
                return;
            }
        }
        if (curNode.left == null && curNode.right == null) {
            return;
        }
        tempList.add(curNode.val);
        if (curNode.left != null) {
            fun1(curNode.left, targetSum, curSum + curNode.val, resultList, tempList);
        }
        if (curNode.right != null) {
            fun1(curNode.right, targetSum, curSum + curNode.val, resultList, tempList);
        }
        tempList.remove(tempList.size() - 1);
    }
}
