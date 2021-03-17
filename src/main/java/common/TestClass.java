package common;

import daily_question.daily_question_2020.LRUCache;
import design.medium.FrontMiddleBackQueue;
import design.medium.MyCircularQueue;
import dynamic_programming.medium.NumMatrix;
import org.junit.Assert;
import org.junit.Test;

public class TestClass {

    @Test
    public void testNumMatrix() {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix test = new NumMatrix(matrix);

        System.out.println(test.sumRegion(2, 1, 4, 3));
        System.out.println(test.sumRegion(1, 1, 2, 2));
        System.out.println(test.sumRegion(1, 2, 2, 4));
    }

    @Test
    public void testLRUCache() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assert cache.get(1) == 1;       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        assert cache.get(2) == -1;       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        assert cache.get(1) == -1;       // 返回 -1 (未找到)
        assert cache.get(3) == 3;       // 返回  3
        assert cache.get(4) == 4;       // 返回  4
    }

    @Test
    public void testLRUCache2() {
        design.medium.LRUCache cache = new design.medium.LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assert cache.get(1) == 1;       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        assert cache.get(2) == -1;       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        assert cache.get(1) == -1;       // 返回 -1 (未找到)
        assert cache.get(3) == 3;       // 返回  3
        assert cache.get(4) == 4;       // 返回  4
    }

    @Test
    public void testFrontMiddleBackQueue() {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]
        assert 1 == q.popFront();     // 返回 1 -> [4, 3, 2]
        assert 3 == q.popMiddle();    // 返回 3 -> [4, 2]
        assert 4 == q.popMiddle();    // 返回 4 -> [2]
        assert 2 == q.popBack();      // 返回 2 -> []
        q.popFront();     // 返回 -1 -> [] （队列为空）
    }

    @Test
    public void testMyCircularQueue() {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        assert true == circularQueue.enQueue(1); // 返回 true
        assert true == circularQueue.enQueue(2); // 返回 true
        assert true == circularQueue.enQueue(3); // 返回 true
        assert false == circularQueue.enQueue(4); // 返回 false，队列已满
        assert 3 == circularQueue.Rear(); // 返回 3
        assert true == circularQueue.isFull(); // 返回 true
        assert true == circularQueue.deQueue(); // 返回 true
        assert true == circularQueue.enQueue(4); // 返回 true
        assert 4 == circularQueue.Rear(); // 返回 4
    }
}
