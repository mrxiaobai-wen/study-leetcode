package design.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 16.25. LRU 缓存
 *
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。
 * 当缓存被填满时，它应该删除最近最少使用的项目。
 *
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 //缓存容量 );
    *
    * cache.put(1,1);
    * cache.put(2,2);
    * cache.get(1);       // 返回  1
    * cache.put(3,3);    // 该操作会使得密钥 2 作废
    * cache.get(2);       // 返回 -1 (未找到)
    * cache.put(4,4);    // 该操作会使得密钥 1 作废
    *cache.get(1);       // 返回 -1 (未找到)
    *cache.get(3);       // 返回  3
    *cache.get(4);       // 返回  4
    *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LRUCache {

    private int capacity;
    private Map<Integer,Node> content;
    private Node header,rear;

    /**
     * 每次新元素往尾部添加，超出容量则过期掉头节点
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        content = new HashMap<>();
    }

    public int get(int key) {
        Node result = content.get(key);
        if (result == null) {
            return -1;
        }
        update(result.key,result.value);
        return result.value;
    }

    public void put(int key, int value) {
        if (content.containsKey(key)) {
            update(key,value);
        } else {
            add(key,value);
        }
    }

    private void add(int key,int value) {
        if (content.size() >= capacity) {
            removeLast();
        }
        Node cur = new Node(key,value);
        if (rear != null) {
            rear.next = cur;
            cur.pre = rear;
        }
        rear = cur;
        if (header == null) {
            header = cur;
        }
        content.put(key,cur);
    }

    private void update(int key,int value) {
        Node cur = content.get(key);
        if (cur == header) {
            header = cur.next;
            cur.next = null;
        } else if (cur == rear) {
            rear = cur.pre;
            cur.pre = null;
        } else {
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            cur.pre = null;
            cur.next = null;
        }
        cur.value = value;
        if (rear != null) {
            rear.next = cur;
            cur.pre = rear;
        }
        rear = cur;
        if (header == null) {
            header = cur;
        }
    }

    private void removeLast() {
        Node cur = header;
        if (header != null) {
            header = cur.next;
            if (cur.next != null) {
                cur.next.pre = null;
            }
            cur.next = null;
        }
        if (header == null) {
            rear = null;
        }
        content.remove(cur.key);
    }

    private class Node {
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        int key;
        int value;
        Node pre;
        Node next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
