package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 713. 乘积小于K的子数组
 * <p>
 * 给定一个正整数数组 nums。
 * <p>
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 说明:
 * <p>
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumSubarrayProductLessThanK_713 {

    @Test
    public void test() {
        Assert.assertEquals(8, numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        return fun(nums, k);
    }

    private int fun(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int left = 0, right = 0;
        int prod = 1;
        int result = 0;
        for (; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            // todo 重点！！！！！
            result += right - left + 1;
        }
        return result;
    }
}
