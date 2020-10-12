package two_pointers.medium;

import common.ListNode;
import org.junit.Test;

/**
 * 61. 旋转链表
 *
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RotateRight_61 {

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
        ListNode node = rotateRight(node1,2);
    }

    public ListNode rotateRight(ListNode head, int k) {
        return fun(head,k);
    }

    private ListNode fun(ListNode head,int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode rear = head;
        int n = 1;
        while (rear.next != null) {
            rear = rear.next;
            n++;
        }
        rear.next = head;
        for (int i = 1;i < n - (k % n);i++) {
            head = head.next;
        }
        ListNode temp = head.next;
        head.next = null;
        return temp;
    }
}
