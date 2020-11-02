package depth_first_search;

import common.TreeNode;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * /  \
 * -3   9
 * /   /
 * -10 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedListToBST_109 {
    public TreeNode sortedListToBST(ListNode head) {
        // 参考点一：快慢指针
        return fun1(head);
        // 参考上面，将链表转成数组，使用二分查找。空间换时间
        // .........................

    }


    /**
     * 递归实现：使用快慢指针
     *
     * @param sourceList
     * @return
     */
    private TreeNode fun1(ListNode sourceList) {
        if (sourceList == null) {
            return null;
        }
        ListNode midNode = mideList(sourceList);
        if (midNode == null) {
            return null;
        }
        TreeNode resultNode = new TreeNode(midNode.val);
        if (sourceList == midNode) {
            return resultNode;
        }

        resultNode.left = fun1(sourceList);
        resultNode.right = fun1(midNode.next);

        return resultNode;
    }

    /**
     * 使用快慢指针寻找中间节点
     *
     * @param sourceList
     * @return
     */
    private ListNode mideList(ListNode sourceList) {
        ListNode pre, fastPtr, lowPtr;
        pre = fastPtr = lowPtr = sourceList;
        while (fastPtr != null && fastPtr.next != null) {
            pre = lowPtr;
            lowPtr = lowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        return lowPtr;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
