package design.easy;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 706. 设计哈希映射
 *
 * 不使用任何内建的哈希表库设计一个哈希映射
 *
 * 具体地说，你的设计应该包含以下的功能
 *
 * put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
 * get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
 * remove(key)：如果映射中存在这个键，删除这个数值对。
 *
 * 示例：
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);          
 * hashMap.put(2, 2);        
 * hashMap.get(1);            // 返回 1
 * hashMap.get(3);            // 返回 -1 (未找到)
 * hashMap.put(2, 1);         // 更新已有的值
 * hashMap.get(2);            // 返回 1
 * hashMap.remove(2);         // 删除键为2的数据
 * hashMap.get(2);            // 返回 -1 (未找到)
 *
 * 注意：
 *
 * 所有的值都在 [0, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希库。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashmap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyHashMap {

    @Test
    public void test() {
        MyHashMap map = new MyHashMap();
        map.put(2,1);
        map.get(2);
        map.remove(2);
        map.get(2);
    }

    public class Entry {

        public int key;
        public int val;

        Entry(int key,int val) {
            this.key = key;
            this.val = val;
        }
    }

    private class Bucket {

        public List<Entry> list;

        Bucket() {
            list = new LinkedList<>();
        }

        public void update(int key,int val) {
            boolean fund = false;
            for (Entry entry : list) {
                if (key == entry.key) {
                    entry.val = val;
                    fund = true;
                    break;
                }
            }
            if (!fund) {
                list.add(new Entry(key,val));
            }
        }

        public int get(int key) {
            for (Entry entry : list) {
                if (entry.key == key) {
                    return entry.val;
                }
            }
            return -1;
        }

        public void remove(int key) {
            Entry entry = null;
            for (Entry cur : list) {
                if (cur.key == key) {
                    entry = cur;
                    break;
                }
            }
            if (entry != null) {
                list.remove(entry);
            }
        }
    }

    private Bucket[] data;
    private final int SIZE = 2049;
    /**
     * 参考官方题解：数组长度取一个大素数数组 2049
     */
    public MyHashMap() {
        data = new Bucket[SIZE];
        for (int i = 0;i < SIZE;i++) {
            data[i] = new Bucket();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hashKey = hashKey(key);
        data[hashKey].update(key,value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hashKey = hashKey(key);
        return data[hashKey].get(key);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hashKey = hashKey(key);
        data[hashKey].remove(key);
    }

    private int hashKey(int key) {
        return key % SIZE;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */