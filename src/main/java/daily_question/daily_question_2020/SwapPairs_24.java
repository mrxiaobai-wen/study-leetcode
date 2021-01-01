package daily_question.daily_question_2020;

import common.ListNode;
import org.junit.Test;

/**
 * 24. 两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwapPairs_24 {

    @Test
    public void test() {
        ListNode node1 = ListNode.createList(1, 4);
        ListNode result1 = swapPairs(node1);

        ListNode node2 = ListNode.createList(1, 5);
        ListNode result2 = swapPairs(node2);

    }

    public ListNode swapPairs(ListNode head) {
        return fun(head);
    }

    private ListNode fun(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = null;
        ListNode current = null;
        ListNode left = head, right = head.next;
        while (left != null && right != null) {
            ListNode next = right.next;
            if (result == null) {
                result = right;
            }
            if (current != null) {
                current.next = right;
            }
            right.next = left;
            left.next = next;
            current = left;
            left = current.next;
            if (current.next != null) {
                right = current.next.next;
            } else {
                right = null;
            }
        }
        return result;
    }
}
