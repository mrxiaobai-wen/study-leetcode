package sort.medium;

import org.junit.Test;

import java.util.Arrays;

/**
 * 面试题 16.21. 交换和
 *
 * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 *
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 *
 * 示例:
 *
 * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
 * 输出: [1, 3]
 * 示例:
 *
 * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
 * 输出: []
 * 提示：
 *
 * 1 <= array1.length, array2.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-swap-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindSwapValues {

    @Test
    public void test() {
        int[] resutl = findSwapValues(new int[]{4, 1, 2, 1, 1, 2},new int[]{3, 6, 3, 3});
    }

    public int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = 0;
        for (int cur : array1) {
            sum1 += cur;
        }
        int sum2 = 0;
        for (int cur : array2) {
            sum2 += cur;
        }
        Arrays.sort(array1);
        Arrays.sort(array2);
        int differ = sum1 - sum2;
        if (differ % 2 != 0) {
            return new int[0];
        }
        differ /= 2;
        for (int i = 0;i < array1.length;i++) {
            int index = Arrays.binarySearch(array2,array1[i] - differ);
            if (index >= 0) {
                return new int[]{array1[i],array2[index]};
            }
        }
        return new int[0];
    }
}
