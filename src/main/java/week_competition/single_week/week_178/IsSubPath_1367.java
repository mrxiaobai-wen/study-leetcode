package week_competition.single_week.week_178;

/**
 * 1367. 二叉树中的列表
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * <p>
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * <p>
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：false
 * 解释：二叉树中不存在一一对应链表的路径。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 二叉树和链表中的每个节点的值都满足 1 <= node.val <= 100 。
 * 链表包含的节点数目在 1 到 100 之间。
 * 二叉树包含的节点数目在 1 到 2500 之间。
 */
public class IsSubPath_1367 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        // fun执行结果超时
        //return fun(head,head,root);

        return dfs(head, head, root);
    }

    private boolean fun(ListNode head, ListNode curNode, TreeNode root) {
        if (curNode == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        boolean subResult = false;
        if (curNode.val == root.val) {
            subResult = fun(head, curNode.next, root.left) || fun(head, curNode.next, root.right);
        }
        if (subResult) {
            return true;
        }
        if (head.val == root.val) {
            return fun(head, head.next, root.left) || fun(head, head.next, root.right);
        } else {
            // 超时原因：应当只是在当前链表节点是头节点的时候，子树不匹配，节点才往下沉
            // 不然每个链表节点上都可以将头节点往下沉，将会有大量的重复
            return fun(head, head, root.left) || fun(head, head, root.right);
        }
    }

    private boolean dfs(ListNode head, ListNode curNode, TreeNode root) {
        if (curNode == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        boolean subResult = false;
        if (curNode.val == root.val) {
            subResult = dfs(head, curNode.next, root.left) || dfs(head, curNode.next, root.right);
        }
        if (subResult) {
            return true;
        }
        if (head == curNode) {
            // 将链表头节点往下沉
            return dfs(head, head, root.left) || dfs(head, head, root.right);
        }
        return false;
    }
}
