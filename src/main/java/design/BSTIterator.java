package design;

import common.TreeNode;

import java.util.Stack;


/**
 * 173. 二叉搜索树迭代器
 * <p>
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * <p>
 * 示例：
 * <p>
 * BSTIterator iterator = new BSTIterator(root); iterator.next();    // 返回 3 iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true iterator.next();    // 返回 9 iterator.hasNext(); // 返回 true iterator.next();    // 返回
 * 15 iterator.hasNext(); // 返回 true iterator.next();    // 返回 20 iterator.hasNext(); // 返回 false
 * <p>
 * 提示：
 * <p>
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class BSTIterator {

    private Stack<TreeNode> stack;

    /**
     * 方法一：二叉搜索树中序遍历结果就是有序，但是不符合空间复杂度
     * <p>
     * 方法二：用栈模拟递归 官方题解：受控递归 https://leetcode-cn.com/problems/binary-search-tree-iterator/solution/er-cha-sou-suo-shu-die-dai-qi-by-leetcode/
     */
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        leftPush(root);
    }

    private void leftPush(TreeNode root) {
        while (root != null) {
            stack.add(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode curNode = stack.pop();
        if (curNode.right != null) {
            leftPush(curNode.right);
        }
        return curNode.val;
    }

    public boolean hasNext() {
        return stack.size() > 0;
    }
}
