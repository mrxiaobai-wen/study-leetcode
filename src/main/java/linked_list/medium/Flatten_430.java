package linked_list.medium;

/**
 * 430. 扁平化多级双向链表
 * <p>
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * <p>
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12] 输出：[1,2,3,7,8,11,12,9,10,4,5,6] 解释：
 * <p>
 * 输入的多级列表如下图所示：
 * <p>
 * 扁平化后的链表如下图：
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,2,null,3] 输出：[1,3,2] 解释：
 * <p>
 * 输入的多级列表如下图所示：
 * <p>
 * 1---2---NULL | 3---NULL 示例 3：
 * <p>
 * 输入：head = [] 输出：[]
 * <p>
 * 如何表示测试用例中的多级链表？
 * <p>
 * 以 示例 1 为例：
 * <p>
 * 1---2---3---4---5---6--NULL | 7---8---9---10--NULL | 11--12--NULL 序列化其中的每一级之后：
 * <p>
 * [1,2,3,4,5,6,null] [7,8,9,10,null] [11,12,null] 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
 * <p>
 * [1,2,3,4,5,6,null] [null,null,7,8,9,10,null] [null,11,12,null] 合并所有序列化结果，并去除末尾的 null 。
 * <p>
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * <p>
 * 提示：
 * <p>
 * 节点数目不超过 1000 1 <= Node.val <= 10^5
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Flatten_430 {

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        return fun(head);
    }

    private Node fun(Node head) {
        Node result = head;
        Node next = head.next;
        head.next = null;
        Node child = head.child;
        head.child = null;
        if (child != null) {
            child.prev = null;
            Node childHead = fun(child);
            head.next = childHead;
            childHead.prev = head;
            while (childHead.next != null) {
                childHead = childHead.next;
            }
            head = childHead;
        }
        if (next != null) {
            next.prev = null;
            Node nextHead = fun(next);
            head.next = nextHead;
            nextHead.prev = head;
        }
        return result;
    }

    // Definition for a Node.
    class Node {

        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
