package linked_list.medium;

import common.ListNode;

import java.util.Stack;

/**
 * 445. 两数相加 II
 *
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumbers_445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        while (l1 != null) {
            stack1.add(l1.val);
            l1 = l1.next;
        }
        Stack<Integer> stack2 = new Stack<>();
        while (l2 != null) {
            stack2.add(l2.val);
            l2 = l2.next;
        }
        int sum = 0;
        ListNode result = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int num1 = stack1.pop();
            int num2 = stack2.pop();
            sum += num1 + num2;
            ListNode curNode = new ListNode(sum % 10);
            sum /= 10;
            if (result == null) {
                result = curNode;
            } else {
                curNode.next = result;
                result = curNode;
            }
        }
        while (!stack1.isEmpty()) {
            sum += stack1.pop();
            ListNode curNode = new ListNode(sum % 10);
            sum /= 10;
            curNode.next = result;
            result = curNode;
        }
        while (!stack2.isEmpty()) {
            sum += stack2.pop();
            ListNode curNode = new ListNode(sum % 10);
            sum /= 10;
            curNode.next = result;
            result = curNode;
        }
        while (sum > 0) {
            ListNode curNode = new ListNode(sum % 10);
            sum /= 10;
            curNode.next = result;
            result = curNode;
        }
        return result;
    }
}
