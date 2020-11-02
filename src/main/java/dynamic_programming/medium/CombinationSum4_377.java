package dynamic_programming.medium;

import org.junit.Test;

/**
 * 377. 组合总和 Ⅳ
 * <p>
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * <p>
 * 示例:
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * 请注意，顺序不同的序列被视作不同的组合。
 * <p>
 * 因此输出为 7。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum4_377 {

    @Test
    public void test() {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
        System.out.println(fun2(new int[]{1, 2, 3}, 4));
    }

    public int combinationSum4(int[] nums, int target) {
        // 递归,执行超时
        //return fun1(nums,target);
        // 动态规划
        return fun2(nums, target);
    }

    private int fun1(int[] nums, int target) {
        if (target == 0) {
            return 1;
        } else if (target < 0) {
            return 0;
        }
        int len = nums.length;
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += fun1(nums, target - nums[i]);
        }
        return result;
    }

    private int fun2(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
