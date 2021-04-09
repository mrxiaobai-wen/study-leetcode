package linked_list.medium;

import common.ListNode;

/**
 * 面试题 02.04. 分割链表
 *
 * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。
 * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 *
 * 示例:
 *
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Partition {

    public ListNode partition(ListNode head, int x) {
        ListNode leftHead = null,leftRear = null;
        ListNode rightHead = null,rightRear = null;
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            cur.next = null;
            if (cur.val < x) {
                if (leftRear == null) {
                    leftHead = leftRear = cur;
                } else {
                    leftRear.next = cur;
                    leftRear = cur;
                }
            } else {
                if (rightRear == null) {
                    rightHead = rightRear = cur;
                } else {
                    rightRear.next = cur;
                    rightRear = cur;
                }
            }
        }
        if (leftRear != null) {
            leftRear.next = rightHead;
            return leftHead;
        } else {
            return rightHead;
        }
    }
}
