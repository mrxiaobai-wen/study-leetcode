package dynamic_programming.medium;

import org.junit.Test;

/**
 * 152. 乘积最大子数组
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumProductSubarray_152 {

    @Test
    public void test1() {
        System.out.println(maxProduct(new int[]{-2}));
    }

    public int maxProduct(int[] nums) {
        return fun(nums);
    }

    /**
     * 思路：
     * 每个零点就是一个分隔点，在被0分隔的一段内：
     * 若负数为偶数个，那么最大值为全部数字乘积；
     * 若负数为奇数个，那么最大值为从左到右的乘积和从右到左的乘积的最大值（相乘不包含最后一个负数）；
     * 通过上面两个步骤，得出最大的乘积。
     */
    private int fun(int[] nums) {
        int maxResult = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                maxResult = Math.max(maxResult, 0);
                continue;
            } else {
                int j = i;
                int negative = 0;
                for (; j < len; j++) {
                    if (nums[j] < 0) {
                        negative++;
                    } else if (nums[j] == 0) {
                        maxResult = Math.max(maxResult, 0);
                        break;
                    }
                }
                int tempResult = calculate(nums, i, j - 1, negative);
                maxResult = Math.max(maxResult, tempResult);
                i = j;
            }
        }

        return maxResult;
    }

    private int calculate(int[] nums, int start, int end, int negative) {
        if (start == end) {
            return nums[start];
        }
        negative = negative / 2 * 2;
        int leftNegative = negative;
        int rightNegative = negative;
        int leftResult = 0;
        int rightResult = 0;
        for (int i = 0; i <= end - start; i++) {
            if (leftNegative >= 0) {
                if (nums[start + i] >= 0) {
                    if (leftResult == 0) {
                        leftResult = 1;
                    }
                    leftResult *= nums[start + i];
                } else if (nums[start + i] < 0 && leftNegative > 0) {
                    if (leftResult == 0) {
                        leftResult = 1;
                    }
                    leftResult *= nums[start + i];
                    leftNegative--;
                } else {
                    leftNegative--;
                }
            }
            if (rightNegative >= 0) {
                if (nums[end - i] >= 0) {
                    if (rightResult == 0) {
                        rightResult = 1;
                    }
                    rightResult *= nums[end - i];
                } else if (nums[end - i] < 0 && rightNegative > 0) {
                    if (rightResult == 0) {
                        rightResult = 1;
                    }
                    rightResult *= nums[end - i];
                    rightNegative--;
                } else {
                    rightNegative--;
                }
            }
        }
        return Math.max(leftResult, rightResult);
    }
}
