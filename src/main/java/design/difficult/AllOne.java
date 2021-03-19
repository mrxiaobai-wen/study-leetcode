package design.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 432. 全 O(1) 的数据结构
 *
 * 请你实现一个数据结构支持以下操作：
 *
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。
 * 如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 *
 * 挑战：
 *
 * 你能够以 O(1) 的时间复杂度实现所有操作吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class AllOne {

    private Node head,rear;
    Map<String, List<Node>> map;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (map.containsKey(key)) {
            List<Node> list = map.get(key);
            Node curNode = list.get(0);
            list.remove(curNode);
            if (list.size() == 0) {
                map.remove(key);
            }
            curNode.val = (Integer.valueOf(curNode.val) + 1) + "";
            List<Node> newList = map.getOrDefault(curNode.val + "",new ArrayList<>());
            newList.add(curNode);
            updateNode(curNode);
        } else {
            Node newNode = new Node(key);
            List<Node> list = map.getOrDefault(key,new ArrayList<>());
            list.add(newNode);
            map.put(key,list);
            insertNewNode(newNode);
        }
    }

    private void insertNewNode(Node newNode) {
        if (head == null) {
            head = newNode;
            rear = newNode;
        } else if (newNode.val.compareTo(head.val) <= 0) {
            newNode.next = head;
            head.pre = newNode;
            head = newNode;
        } else if (newNode.val.compareTo(rear.val) >= 0) {
            rear.next = newNode;
            newNode.pre = rear;
            rear = newNode;
        }
    }

    private void updateNode(Node curNode) {
        delNode(curNode);
        insertNewNode(curNode);
    }

    private void delNode(Node curNode) {
        if (curNode == head) {
            head = head.next;
            if (head == null) {
                rear = null;
            } else {
                head.pre = null;
            }
            curNode.next = null;
        } else if (curNode == rear) {
            rear = rear.pre;
            if (rear == null) {
                head = null;
            } else {
                rear.next = null;
            }
            curNode.pre = null;
        } else {
            curNode.pre.next = curNode.next;
            curNode.next.pre = curNode.pre;
        }
        curNode.pre = null;
        curNode.next = null;
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if ("1".equals(key)) {
            List<Node> list = map.get(key);
            if (list == null) {
                return;
            }
            Node cur = list.get(0);
            list.remove(cur);
            if (list.size() == 0) {
                map.remove(key);
            }
            delNode(cur);
        } else if (map.containsKey(key)) {
            List<Node> list = map.get(key);
            Node cur = list.get(0);
            list.remove(cur);
            if (list.size() == 0) {
                map.remove(key);
            }
            cur.val = (Integer.valueOf(cur.val) - 1) + "";
            List<Node> newList = map.getOrDefault(cur.val + "",new ArrayList<>());
            newList.add(cur);
            map.put(cur.val + "",newList);
            updateNode(cur);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (rear != null) {
            return rear.val + "";
        }
        return "";
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head != null) {
            return head.val + "";
        }
        return "";
    }

    private class Node{

        public String val;
        public Node pre;
        public Node next;

        public Node(String val) {
            this.val = val;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
