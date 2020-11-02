package two_pointers.difficult;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. K 个不同整数的子数组
 * <p>
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * <p>
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * <p>
 * 返回 A 中好子数组的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * <p>
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubarraysWithKDistinct_992 {

    @Test
    public void test() {
        Assert.assertEquals(7, subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        return fun(A, K);
    }

    private int fun(int[] a, int k) {
        if (a == null || a.length < k) {
            return 0;
        }
        int result = 0;
        Window window1 = new Window();
        Window window2 = new Window();
        int left1 = 0, left2 = 0;
        for (int right = 0; right < a.length; right++) {
            int x = a[right];
            window1.add(x);
            window2.add(x);
            while (window1.different() > k) {
                window1.remove(a[left1++]);
            }
            while (window2.different() >= k) {
                window2.remove(a[left2++]);
            }
            // 重点！！！
            // 如果left2 == left1 不影响结果
            // 如果left2 > left1 那么left1 和 left2之间就是符合条件的一个子数组
            result += (left2 - left1);
        }
        return result;
    }

    private class Window {
        private Map<Integer, Integer> map = new HashMap<>();

        Window() {
        }

        void add(int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        void remove(int x) {
            map.put(x, map.getOrDefault(x, 0) - 1);
            if (map.getOrDefault(x, 0) <= 0) {
                map.remove(x);
            }
        }

        int different() {
            return map.size();
        }
    }
}
