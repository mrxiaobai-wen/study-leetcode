package heap.medium;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 面试题 17.14. 最小K个数
 * <p>
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4 输出： [1,2,3,4] 提示：
 * <p>
 * 0 <= len(arr) <= 100000 0 <= k <= min(100000, len(arr))
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/smallest-k-lcci 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestK {

    @Test
    public void test() {
        int[] result = smallestK(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4);
    }

    public int[] smallestK(int[] arr, int k) {
        return fun1(arr, k);

    }

    public int[] fun1(int[] arr, int k) {
        int len = arr.length;
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            makeMinHeap(arr, len--);
            result[i] = arr[0];
            arr[0] = arr[len];
        }
        return result;
    }

    private void makeMinHeap(int[] arr, int len) {
        for (int i = len / 2 - 1; i >= 0; i--) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            if (right < len && arr[left] > arr[right]) {
                left = right;
            }
            if (arr[i] > arr[left]) {
                int temp = arr[i];
                arr[i] = arr[left];
                arr[left] = temp;
            }
        }
    }

    /**
     * queue add和offer方法，在容量满了的情况下，add抛出异常，offer返回false; remove和poll方法，在队列空的情况下，remove抛出异常，poll返回null;
     */
    private int[] fun2(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0) {
            return result;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 倒序排序
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }
}
