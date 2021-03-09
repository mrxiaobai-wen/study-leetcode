package sort.medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1305. 两棵二叉搜索树中的所有元素
 *
 * 给你 root1 和 root2 这两棵二叉搜索树。
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 *
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 *
 * 示例 2：
 * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * 输出：[-10,0,0,1,2,5,7,10]
 *
 * 示例 3：
 * 输入：root1 = [], root2 = [5,1,7,0,2]
 * 输出：[0,1,2,5,7]
 *
 * 示例 4：
 * 输入：root1 = [0,-10,10], root2 = []
 * 输出：[-10,0,10]
 *
 * 示例 5：
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 *
 * 提示：
 *
 * 每棵树最多有 5000 个节点。
 * 每个节点的值在 [-10^5, 10^5] 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GetAllElements_1305 {

    /**
     * 2 1 4
     * 1 0 3
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        return fun(root1,root2);
    }

    /**
     * 考虑使用两个迭代器遍历两颗树
     */
    private List<Integer> fun(TreeNode root1,TreeNode root2) {
        SortTreeIterator it1 = new SortTreeIterator(root1);
        SortTreeIterator it2 = new SortTreeIterator(root2);
        List<Integer> list = new ArrayList<>();
        while (it1.hashNext() && it2.hashNext()) {
            if (it1.peek() <= it2.peek()) {
                list.add(it1.next());
            } else {
                list.add(it2.next());
            }
        }
        while (it1.hashNext()) {
            list.add(it1.next());
        }
        while (it2.hashNext()) {
            list.add(it2.next());
        }
        return list;
    }

    class SortTreeIterator {

        Stack<TreeNode> stack = new Stack<>();

        SortTreeIterator(TreeNode root) {
            stackPush(root);
        }

        private void stackPush(TreeNode root) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
        }

        public boolean hashNext() {
            return !stack.isEmpty();
        }

        public Integer peek() {
            return stack.peek().val;
        }

        public Integer next() {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                stackPush(cur.right);
            }
            return cur.val;
        }
    }
}
