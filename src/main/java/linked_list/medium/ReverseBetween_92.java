package linked_list.medium;

import common.ListNode;
import org.junit.Test;

import java.util.List;

/**
 * 92. 反转链表 II
 *
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * 提示：
 *
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * 进阶： 你可以使用一趟扫描完成反转吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseBetween_92 {

    @Test
    public void test1() {
        ListNode node = ListNode.createList(new int[]{1,2,3,4,5});
        ListNode result = reverseBetween(node,3,4);
    }

    @Test
    public void test2() {
        ListNode node = ListNode.createList(new int[]{3,5});
        ListNode result = reverseBetween(node,1,2);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        if (right <= left || right <= 0) {
            return head;
        }
        ListNode leftPre = null,rightNext = null;
        ListNode leftHead = head,rightHead = head;
        for (int i = 1;i < left;i++) {
            leftPre = leftHead;
            leftHead = leftHead.next;
        }
        for (int i = 1;i < right;i++) {
            rightHead = rightHead.next;
        }
        rightNext = rightHead.next;
        rightHead.next = null;
        if (leftPre != null) {
            leftPre.next = null;
        }
        ListNode tempHead = null,tempRear = null;
        while (leftHead != null) {
            ListNode cur = leftHead;
            leftHead = leftHead.next;
            if (tempRear == null) {
                tempHead = cur;
                tempRear = cur;
                cur.next = null;
            } else {
                cur.next = tempHead;
                tempHead = cur;
            }
        }
        if (leftPre != null) {
            leftPre.next = tempHead;
        }
        tempRear.next = rightNext;
        if (leftPre == null) {
            return tempHead;
        } else {
            return head;
        }
    }
}
