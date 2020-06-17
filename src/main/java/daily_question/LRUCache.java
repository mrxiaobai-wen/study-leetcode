package daily_question;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU缓存机制
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 示例:
 * LRUCache cache = new LRUCache( 2 );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */
public class LRUCache {

    private int capacity;
    private Node header;
    private Node rear;
    private Map<Integer,Node> content;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        content = new HashMap<>();
    }

    public int get(int key) {
        if (content.containsKey(key)) {
            int result = content.get(key).value;
            updateKey(key,result);
            return result;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (content.containsKey(key)) {
            // 已经存入过一次
            updateKey(key,value);
        } else {
            if (content.size() >= capacity) {
                removeLastKey();
            }
            addKey(key,value);
        }
    }

    private void addKey(int key,int value) {
        Node newNode = new Node(key,value);
        newNode.pre = rear;
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (header == null) {
            header = newNode;
        }
        content.put(key,newNode);
    }

    private void removeLastKey() {
        if (header == null) {
            return;
        }
        Node needRemove = header;
        if (header.next != null) {
            header.next.pre = null;
        }
        header = header.next;
        needRemove.next = null;
        content.remove(needRemove.key);
    }

    private void updateKey(int key,int value) {
        Node cur = content.get(key);
        cur.value = value;
        if (header == cur) {
            if (cur.next != null) {
                cur.next.pre = null;
            }
            header = cur.next;
            cur.next = null;
        } else if (rear == cur) {
            if (cur.pre != null) {
                cur.pre.next = null;
            }
            rear = cur.pre;
            cur.pre = null;
        } else {
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            cur.pre = null;
            cur.next = null;
        }
        if (rear != null) {
            rear.next = cur;
            cur.pre = rear;
            rear = cur;
        }
        if (header == null) {
            header = cur;
        }
    }

    private class Node {
        Node(int key,int value) {
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