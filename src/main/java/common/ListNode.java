package common;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode createList(int start,int end) {
        ListNode result = null;
        for (int i = end;i >= start;i--) {
            ListNode cur = new ListNode(i);
            cur.next = result;
            result = cur;
        }
        return result;
    }
}
