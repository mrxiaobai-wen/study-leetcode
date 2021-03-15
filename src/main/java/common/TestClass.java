package common;

import daily_question.daily_question_2020.LRUCache;
import dynamic_programming.medium.NumMatrix;
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
}
