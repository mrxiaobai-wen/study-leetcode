package daily_question.daily_question_2020;

import common.ListNode;

/**
 * 328. 奇偶链表
 * <p>
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL 输出: 1->3->5->2->4->NULL 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL 输出: 2->3->6->7->1->5->4->NULL 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/odd-even-linked-list 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OddEvenList_328 {

    public ListNode oddEvenList(ListNode head) {
        return fun(head);
    }

    private ListNode fun(ListNode head) {
        ListNode first = null, firstCur = null;
        ListNode second = null, secondCur = null;
        while (head != null) {
            if (firstCur != null) {
                firstCur.next = head;
                firstCur = firstCur.next;
            } else {
                first = head;
                firstCur = head;
            }
            head = head.next;
            firstCur.next = null;
            if (head != null) {
                if (secondCur != null) {
                    secondCur.next = head;
                    secondCur = secondCur.next;
                } else {
                    second = head;
                    secondCur = head;
                }
                head = head.next;
                secondCur.next = null;
            }
        }
        if (first == null) {
            return null;
        }
        firstCur.next = second;
        return first;
    }
}
