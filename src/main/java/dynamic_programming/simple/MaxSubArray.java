package dynamic_programming.simple;

/**
 * 面试题 16.17. 连续数列
 * 给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。
 * <p>
 * 示例：
 * <p>
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶：
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        // 动态规划
        return fun(nums);

        // todo 分治法实现
    }

    /**
     * 动态规划：
     *
     * @param nums
     * @return
     */
    private int fun(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxResult = Integer.MIN_VALUE;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if (pre >= 0) {
                pre = nums[i] + pre;
            } else {
                pre = nums[i];
            }
            if (pre > maxResult) {
                maxResult = pre;
            }
        }
        return maxResult;
    }
}
