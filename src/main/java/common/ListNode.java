package common;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode createList(int start, int end) {
        ListNode result = null;
        for (int i = end; i >= start; i--) {
            ListNode cur = new ListNode(i);
            cur.next = result;
            result = cur;
        }
        return result;
    }

    public static ListNode createList(int[] arr) {
        ListNode result = null;
        ListNode cur = null;
        for (int i = 0; i < arr.length; i++) {
            ListNode temp = new ListNode(arr[i]);
            if (result == null) {
                result = cur = temp;
            } else {
                cur.next = temp;
                cur = cur.next;
            }
        }
        return result;
    }
}
