package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 面试题 16.06. 最小差
 * <p>
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 * <p>
 * 示例：
 * <p>
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出： 3，即数值对(11, 8)
 * 提示：
 * <p>
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间[-2147483648, 2147483647]内
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-difference-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestDifference {

    @Test
    public void test() {
        Assert.assertEquals(3, smallestDifference(new int[]{1, 3, 15, 11, 2}, new int[]{23, 127, 235, 19, 8}));
    }

    public int smallestDifference(int[] a, int[] b) {
        //return fun(a,b);
        return fun2(a, b);
    }

    private int fun2(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long result = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < a.length && j < b.length; ) {
            result = Math.min(result, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return (int) result;
    }

    /**
     * 暴力-超时
     */
    private int fun(int[] a, int[] b) {
        long result = Long.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            int cur = a[i];
            for (int j = 0; j < b.length; j++) {
                result = Math.min(Math.abs((long) cur - (long) b[j]), result);
            }
        }
        return (int) result;
    }
}
