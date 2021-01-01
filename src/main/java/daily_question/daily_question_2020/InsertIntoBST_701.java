package daily_question.daily_question_2020;

import common.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作
 * <p>
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
 * 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 * <p>
 * 例如, 
 * <p>
 * 给定二叉搜索树:
 * <p>
 * 4
 * / \
 * 2   7
 * / \
 * 1   3
 * <p>
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   /
 * 1   3 5
 * 或者这个树也是有效的:
 * <p>
 * 5
 * /   \
 * 2     7
 * / \
 * 1   3
 * \
 * 4
 *  
 * 提示：
 * <p>
 * 给定的树上的节点数介于 0 和 10^4 之间
 * 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 * -10^8 <= val <= 10^8
 * 新值和原始二叉搜索树中的任意节点值都不同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InsertIntoBST_701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        return fun(root, val);
    }

    private TreeNode fun(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode();
            node.val = val;
            return node;
        }
        if (val >= root.val) {
            if (root.right == null) {
                root.right = fun(root.right, val);
            } else {
                fun(root.right, val);
            }
        } else {
            if (root.left == null) {
                root.left = fun(root.left, val);
            } else {
                fun(root.left, val);
            }
        }
        return root;
    }
}
