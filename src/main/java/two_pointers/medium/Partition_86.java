package two_pointers.medium;

import common.ListNode;
import org.junit.Test;

import java.util.List;

/**
 * 86. 分隔链表
 *
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Partition_86 {

    @Test
    public void test() {
        //ListNode node = ListNode.createList(new int[]{1,4,3,2,5,2});
        //ListNode result = partition(node,3);

        ListNode node = ListNode.createList(new int[]{2,1});
        ListNode result = partition(node,3); // 输出 2,1

        //ListNode node3 = ListNode.createList(new int[]{3,1});
        //ListNode result3 = partition(node,0); // 输出 2,1
    }

    public ListNode partition(ListNode head, int x) {
        return fun(head,x);
    }

    /**
     * todo 官方题解
     */
    private ListNode fun2(ListNode head,int x) {
        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            // If the original list node is lesser than the given x,
            // assign it to the before list.
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }

            // move ahead in the original list
            head = head.next;
        }

        // Last node of "after" list would also be ending node of the reformed list
        after.next = null;

        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = after_head.next;

        return before_head.next;
    }

    /**
     * 理解错误！！！
     */
    private ListNode fun(ListNode head,int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode firstHead = null,firstRear = null;
        ListNode secondHead = null;
        while (head != null && head.val != x) {
            ListNode tempNode = head;
            head = head.next;
            tempNode.next = null;
            if (tempNode.val < x) {
                // 排序
                ListNode[] temp = insertNode(firstHead,firstRear,tempNode);
                firstHead = temp[0];
                firstRear = temp[1];
            } else {
                if (firstHead == null) {
                    firstHead = tempNode;
                    firstRear = tempNode;
                } else {
                    firstRear.next = tempNode;
                    firstRear = firstRear.next;
                }
            }
        }
        secondHead = head;
        ListNode curIndex = secondHead;
        while (curIndex != null && curIndex.next != null) {
            if (curIndex.next.val < x) {
                ListNode temp = curIndex.next;
                curIndex.next = curIndex.next.next;
                temp.next = null;
                ListNode[] inserResult = insertNode(firstHead,firstRear,temp);
                firstHead = inserResult[0];
                firstRear = inserResult[1];
            } else {
                curIndex = curIndex.next;
            }
            head = head.next;
        }
        if (firstRear == null) {
            firstHead = secondHead;
        } else {
            firstRear.next = secondHead;
        }
        return firstHead;
    }

    private ListNode[] insertNode(ListNode head,ListNode rear,ListNode target) {
        if (head == null) {
            return new ListNode[]{target,target};
        }
        ListNode slow = null,cur = head;
        while (cur != null && cur.val <= target.val) {
            slow = cur;
            cur = cur.next;
        }
        target.next = null;
        if (slow == null) {
            target.next = head;
            head = target;
        } else if (cur == null) {
            rear.next = target;
            rear = target;
        } else {
            slow.next = target;
            target.next = cur;
        }
        return new ListNode[]{head,rear};
    }
}
