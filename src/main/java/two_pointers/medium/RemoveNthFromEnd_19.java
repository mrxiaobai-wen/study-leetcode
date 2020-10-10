package two_pointers.medium;

import common.ListNode;
import org.junit.Test;

/**
 * 19. 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveNthFromEnd_19 {

    @Test
    public void ceshi() {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        node4.next = node5;
        ListNode node3 = new ListNode(3);
        node3.next = node4;
        ListNode node2 = new ListNode(2);
        node2.next = node3;
        ListNode node1 = new ListNode(1);
        node1.next = node2;
        removeNthFromEnd(node1,2);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return fun(head,n);
    }

    private ListNode fun(ListNode head,int n) {
        if (head == null) {
            return null;
        }
        ListNode first = head;
        ListNode target = head;
        int i = 0;
        for (;i < n && first.next != null;i++) {
            first = first.next;
        }
        if (i != n) {
            return head.next;
        } else {
            while(first.next != null) {
                first = first.next;
                target = target.next;
            }
        }
        target.next = target.next.next;
        return head;
    }
}
