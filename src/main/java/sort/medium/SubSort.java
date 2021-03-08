package sort.medium;

import org.junit.Test;

/**
 * 面试题 16.16. 部分排序
 *
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，
 * 也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * 示例：
 *
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 * 提示：
 *
 * 0 <= len(array) <= 1000000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sub-sort-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubSort {

    @Test
    public void test() {
        int[] result = subSort(new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19});
    }

    public int[] subSort(int[] array) {
        return fun2(array);
    }

    /**
     * todo
     * 参考思路：
     * 默认升序（降序也只是改一点代码，不影响）
     *
     * 原理：如果左侧最大值大于中间的最小值，则一定会被中间序列包括；同理，如果右侧最小值大于中间的最大值，则一定会被中间序列包括。
     *
     * 一遍遍历 + 两个指针（两次扫描可一次遍历完成）
     *
     * 1、从前向后扫描数组，判断当前array[i]是否比max小，是则将last置为当前array下标i，否则更新max;
     *
     * 2、从后向前扫描数组，判断当前array[len - 1 - i]是否比min大，是则将first置位当前下标len - 1 - i，否则更新min;
     *
     * 3、返回{first， last}
     */
    public int[] fun2(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{-1,-1};
        }
        int last = -1,first = -1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0;i < array.length;i++) {
            if (array[i] < max) {
                last = i;
            } else {
                max = Math.max(array[i],max);
            }
            if (array[array.length - 1 - i] > min) {
                first = array.length - 1 - i;
            } else {
                min = Math.min(min,array[array.length - 1 - i]);
            }
        }
        return new int[]{first,last};
    }

    /**
     * 想当然了
     */
    public int[] fun(int[] array) {
        int left = -1;
        int right = -1;
        for (int i = 0;i < array.length - 1;i++) {
            if (array[i] > array[i + 1] && left == -1) {
                left = i;
            }
            if (array[array.length - i - 1] < array[array.length - i - 2] && right == -1) {
                right = array.length - i;
            }
        }
        if (left < right) {
            return new int[]{left,right};
        }
        return new int[]{-1,-1};
    }
}
