package dynamic_programming.medium;

import org.junit.Test;

/**
 * 523. 连续的子数组和
 * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，
 * 即总和为 n*k，其中 n 也是一个整数。
 *
 * 示例 1：
 *
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2：
 *
 * 输入：[23,2,6,4,7], k = 6
 * 输出：True
 * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 *
 * 说明：
 *
 * 数组的长度不会超过 10,000 。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckSubarraySum_523 {

    @Test
    public void test() {
        System.out.println(checkSubarraySum(new int[]{23,2,4,6,7},6));
        System.out.println(checkSubarraySum(new int[]{23,2,6,4,7},6));
        System.out.println(checkSubarraySum(new int[]{23,2,6,4,7},101));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        return fun1(nums,k);
    }

    private boolean fun1(int[] nums,int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int len = nums.length;
        int[] dp = new int[len + 1];
        for (int i = 1;i <= len;i++) {
            int cur = nums[i - 1];
            dp[i] = dp[i - 1] + cur;
            for (int j = 1;j < i;j++) {
                int valueJI = dp[i] - dp[j - 1];
                if (k != 0) {
                    if (valueJI % k == 0) {
                        return true;
                    }
                } else {
                    if (valueJI == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
