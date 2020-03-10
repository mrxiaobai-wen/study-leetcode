package daily_question;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree_543 {
    public int diameterOfBinaryTree(TreeNode root) {
        return fun(root)[1];
    }

    /**
     * @param root
     * @return 0:能到的最大深度   1：当前节点下的最大直径
     */
    private int[] fun(TreeNode root) {
        int[] result = new int[2];
        if (root == null) {
            return result;
        }
        int[] left = fun(root.left);
        int[] right = fun(root.right);
        int maxDepth = Math.max(left[0], right[0]) + 1;
        int maxDiameter = Math.max(left[0] + right[0], Math.max(left[1], right[1]));
        result[0] = maxDepth;
        result[1] = maxDiameter;
        return result;
    }

}
