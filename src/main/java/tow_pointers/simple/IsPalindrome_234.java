package tow_pointers.simple;

import org.junit.Test;

import java.util.Stack;

/**
 * 234. 回文链表
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome_234 {

    @Test
    public void test() {
        ListNode pre = null;
        int[] list = new int[]{1,2,2,1};
        ListNode head = null;
        for (int temp : list) {
            if (pre == null) {
                pre = new ListNode(temp);
                head = pre;
            } else {
                ListNode newNode = new ListNode(temp);
                pre.next = newNode;
                pre = newNode;
            }
        }
        System.out.println(isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        return fun(head);
    }

    private boolean fun(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head,slow = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next != null) {
            fast = fast.next;
        }
        slow = slow.next;
        Stack<ListNode> stack = new Stack<>();
        while(slow != null) {
            stack.add(slow);
            slow = slow.next;
        }
        ListNode cur = head;
        while(!stack.isEmpty()) {
            ListNode temp = stack.pop();
            if (cur.val != temp.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}