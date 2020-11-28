package recursion.medium;

import common.TreeNode;

/**
 * 687. 最长同值路径
 * <p>
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 5 / \ 4   5 / \   \ 1   1   5 输出:
 * <p>
 * 2 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1 / \ 4   5 / \   \ 4   4   5 输出:
 * <p>
 * 2 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-univalue-path 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestUnivaluePath_687 {

    public int longestUnivaluePath(TreeNode root) {
        result = 0;
        arrowLength(root);
        return result;
    }

    int result = 0;

    /**
     * 参考思路： 我们可以将任何路径（具有相同值的节点）看作是最多两个从其根延伸出的箭头。
     * <p>
     * 具体地说，路径的根将是唯一节点，因此该节点的父节点不会出现在该路径中，而箭头将是根在该路径中只有一个子节点的路径。
     * <p>
     * 然后，对于每个节点，我们想知道向左延伸的最长箭头和向右延伸的最长箭头是什么？我们可以用递归来解决这个问题。
     */
    public int arrowLength(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0;
        int arrowRiht = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft = left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRiht = right + 1;
        }
        result = Math.max(result, arrowLeft + arrowRiht);
        return Math.max(arrowLeft, arrowRiht);
    }
}
