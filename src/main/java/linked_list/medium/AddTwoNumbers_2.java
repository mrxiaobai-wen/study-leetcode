package linked_list.medium;

import common.ListNode;
import org.junit.Test;

/**
 * 2. 两数相加
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例 1：
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 *
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumbers_2 {

    @Test
    public void test() {
        ListNode node1 = ListNode.createList(new int[]{2,4,3});
        ListNode node2 = ListNode.createList(new int[]{5,6,4});
        addTwoNumbers(node1,node2);
    }

    /**
     * 按位相加，将低位溢出数据移到高位
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode cur = result;
        while (l1 != null && l2 != null) {
            int temp = l1.val + l2.val;
            l1 = l1.next;
            l2 = l2.next;
            ListNode node = new ListNode(temp);
            if (result == null) {
                result = node;
                cur = node;
            } else {
                cur.next = node;
                cur = node;
            }
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        cur = result;
        while (cur.next != null) {
            if (cur.val >= 10) {
                cur.next.val = cur.next.val + cur.val / 10;
                cur.val = cur.val % 10;
            }
            cur = cur.next;
        }
        while (cur != null && cur.val >= 10) {
            ListNode newNode = new ListNode(cur.val / 10);
            cur.val = cur.val % 10;
            cur.next = newNode;
            cur = newNode;
        }
        return result;
    }
}
