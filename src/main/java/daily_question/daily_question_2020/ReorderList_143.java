package daily_question.daily_question_2020;

import common.ListNode;
import org.junit.Test;

import java.util.Stack;

/**
 * 143. 重排链表
 * <p>
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReorderList_143 {

    @Test
    public void test() {
        ListNode root1 = ListNode.createList(1, 4);
        reorderList(root1);
        ListNode root2 = ListNode.createList(1, 5);
        reorderList(root2);
    }

    public void reorderList(ListNode head) {
        fun(head);
    }

    private void fun(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head, fast = head;
        ListNode slowPre = null;
        while (fast != null && fast.next != null) {
            slowPre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slowPre != null) {
            slowPre.next = null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (slow != null) {
            ListNode temp = slow;
            slow = slow.next;
            temp.next = null;
            stack.add(temp);
        }
        ListNode newHead = null, newRear = null;
        while (head != null) {
            ListNode tempHead = head;
            head = head.next;
            tempHead.next = null;
            if (newHead == null) {
                newHead = tempHead;
                newRear = tempHead;
            } else {
                newRear.next = tempHead;
                newRear = newRear.next;
            }
            ListNode tempRear = stack.pop();
            newRear.next = tempRear;
            newRear = newRear.next;
        }
        while (!stack.isEmpty()) {
            ListNode temp = stack.pop();
            newRear.next = temp;
            newRear = newRear.next;
        }
    }
}
