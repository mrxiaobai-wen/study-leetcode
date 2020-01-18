package depth_first_search;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsValidBST_98 {

    /**
     * 思路：
     * 对于当前节点cur,要求其左子树上的所有值小于当前值，且大于由父节点传下来的最小值；
     * 对于当前节点cur，要求其右子树上的所有值大于当前值，且小于由父节点传下来的最大值；
     * 对于初始节点，最大值最小值为null；
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return fun1(root,null,null);
    }

    Stack<TreeNode> nodeStack = new Stack<>();
    Stack<Integer> lowerStack = new Stack<>();
    Stack<Integer> upperStack = new Stack<>();

    /**
     * 非递归实现：前序遍历
     * @return
     */
    boolean fun2(TreeNode root) {
        update2(root,null,null);

        while(!nodeStack.isEmpty()) {
            TreeNode cur = nodeStack.pop();
            Integer lower = lowerStack.pop();
            Integer upper = upperStack.pop();
            if (cur == null) continue;
            if (lower != null && cur.val)
        }



    }

    private void update2(TreeNode cur, Integer lower, Integer upper) {
        nodeStack.add(cur);
        lowerStack.add(lower);
        upperStack.add(upper);
    }

    /**
     * 递归实现：
     * @return
     */
    boolean fun1(TreeNode cur,Integer lower,Integer uper) {
        if (cur == null) return true;
        if (lower != null && lower >= cur.val) return false;
        if (uper != null && uper <= cur.val) return false;

        // 当前值为右子树上的最小值
        if (!fun1(cur.right,cur.val,uper)) return false;

        // 当前值为左子树上的最大值
        if (!fun1(cur.left,lower,cur.val)) return false;

        return true;
    }



    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
