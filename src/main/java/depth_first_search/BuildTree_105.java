package depth_first_search;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BuildTree_105 {

    /*
    解题思路：从前序遍历出发，遍历某个节点，找出在中序遍历中的位置，从中序位置往左往右分别为该节点的左子树和右子树；
    注意：对于在中序遍历中节点x的左右表示其左子树右子树是有边界限制的。

    因为已经限定了不会出现重复元素，所以降低了难度。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0;i < inorder.length;i++) {
            inOrderIndexMap.put(inorder[i],i);
        }
        return fun1(preorder,inorder,0,preorder.length - 1);
    }

    private int preIndex = -1;
    // key:中序遍历值 value:中序序列
    private Map<Integer,Integer> inOrderIndexMap = new HashMap<>();
    /**
     *
     * @param preorder 前序
     * @param inorder 中序
     * @param left 中序最左范围
     * @param right 中序最右位置
     * @return
     */
    TreeNode fun1(int[] preorder,int[] inorder,int left,int right) {
        if (preIndex >= preorder.length) {
            return null;
        }
        if (left > right) {
            return null;
        }

        preIndex++;

        if (left == right) {
            return new TreeNode(preorder[preIndex]);
        }

        TreeNode curNode = new TreeNode(preorder[preIndex]);
        int inOrderIndex = inOrderIndexMap.get(preorder[preIndex]);
        curNode.left = fun1(preorder,inorder,left,inOrderIndex - 1);
        curNode.right = fun1(preorder,inorder,inOrderIndex + 1,right);

        return curNode;
    }


    class TreeNode {
        int val;
        TreeNode left;TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
