package recursion.simple;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 783. 二叉搜索树节点最小距离
 *
 * 给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
 *
 * 示例：
 *
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树节点对象(TreeNode object)，而不是数组。
 *
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 *
 * 注意：
 *
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 * 本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinDiffInBST_783 {

    public int minDiffInBST(TreeNode root) {
        list = new ArrayList<>();
        fun(root);
        Collections.sort(list);
        Integer result = Integer.MAX_VALUE;
        for (int i = 0;i < list.size() - 1;i++) {
            result = Math.min(result,Math.abs(list.get(i) - list.get(i + 1)));
        }
        return result;
    }

    List<Integer> list = null;

    /**
     * 递归
     */
    private void fun(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left != null) {
            fun(root.left);
        }
        if (root.right != null) {
            fun(root.right);
        }
    }
}
