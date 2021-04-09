package linked_list.difficult;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *  
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeKLists_23 {

    public ListNode mergeKLists(ListNode[] lists) {
        return fun1(lists);
    }

    private ListNode fun1(ListNode[] lists) {
        return merge(lists,0,lists.length - 1);
    }

    /**
     * 分治法
     */
    private ListNode merge(ListNode[] lists,int left,int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return lists[left];
        }
        int mid = (left + right) / 2;
        return mergeTwoList(merge(lists,left,mid),merge(lists,mid + 1,right));
    }
    
    private ListNode mergeTwoList(ListNode list1,ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        ListNode resultHead = null,resultRear = null;
        while (list1 != null && list2 != null) {
            ListNode maxNode = null;
            if (list1.val < list2.val) {
                maxNode = list1;
                list1 = list1.next;
            } else {
                maxNode = list2;
                list2 = list2.next;
            }
            maxNode.next = null;
            if (resultHead == null) {
                resultHead = resultRear = maxNode;
            } else {
                resultRear.next = maxNode;
                resultRear = maxNode;
            }
        }
        if (list1 != null) {
            resultRear.next = list1;
        } else {
            resultRear.next = list2;
        }
        return resultHead;
    }

    /**
     * 优先队列
     */
    private ListNode fun2(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode cur : lists) {
            if (cur != null) {
                queue.add(cur);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            tail.next = cur;
            tail = cur;
            cur = cur.next;
            tail.next = null;
            if (cur != null) {
                queue.add(cur);
            }
        }
        return head.next;
    }

    public ListNode fun(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>((x,y)->x.val-y.val);
        for(ListNode node : lists){
            if(node!=null){
                q.add(node);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(!q.isEmpty()){
            tail.next = q.poll();
            tail = tail.next;
            if (tail.next != null){
                q.add(tail.next);
            }
        }
        return head.next;
    }
}
