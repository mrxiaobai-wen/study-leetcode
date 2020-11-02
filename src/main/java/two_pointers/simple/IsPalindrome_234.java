package two_pointers.simple;

import org.junit.Test;

import java.util.Stack;

/**
 * 234. 回文链表
 * <p>
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome_234 {

    @Test
    public void test() {
        ListNode pre = null;
        int[] list = new int[]{1, 2, 2, 1};
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
        //return fun(head);
        return fun2(head);
    }

    /**
     * O（n）时间复杂度，O（1）的空间复杂度
     */
    private boolean fun2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next != null) {
            fast = fast.next;
        }
        // 切割前后两个链表
        ListNode slowPre = slow;
        slow = slow.next;
        slowPre.next = null;
        // 翻转后半链表
        ListNode second = null;
        while (slow != null) {
            ListNode temp = slow;
            slow = slow.next;
            temp.next = second;
            second = temp;
        }
        while (head != null && second != null) {
            if (head.val != second.val) {
                return false;
            }
            head = head.next;
            second = second.next;
        }
        return true;
    }

    private boolean fun(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next != null) {
            fast = fast.next;
        }
        slow = slow.next;
        Stack<ListNode> stack = new Stack<>();
        while (slow != null) {
            stack.add(slow);
            slow = slow.next;
        }
        ListNode cur = head;
        while (!stack.isEmpty()) {
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
