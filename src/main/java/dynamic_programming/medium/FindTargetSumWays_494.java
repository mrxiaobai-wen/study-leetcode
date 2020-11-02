package dynamic_programming.medium;

import org.junit.Test;

/**
 * 494. 目标和
 * <p>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，
 * 你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例：
 * <p>
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 *  
 * 提示：
 * <p>
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTargetSumWays_494 {

    @Test
    public void test() {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    @Test
    public void test2() {
        System.out.println(fun2(new int[]{1, 1, 1, 1, 1}, 3));
    }

    @Test
    public void test3() {
        System.out.println(fun3(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public int findTargetSumWays(int[] nums, int S) {
        fun1(0, nums, 0, S);
        return sum;
    }

    /**
     * 动态规划
     */
    private int fun3(int[] nums, int target) {
        // 设dp[i][j]为前i数和为j的所有可能
        // 那么状态转移方程为：dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return target > 1000 ? 0 : dp[nums.length - 1][target + 1000];

        // 空间优化
        /*
        public int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }

作者：LeetCode
链接：https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
    }

    private int sum = 0;

    /**
     * 递归
     */
    private void fun1(int cur, int[] nums, int curResult, int target) {
        if (cur == nums.length && curResult == target) {
            sum++;
            return;
        } else if (cur >= nums.length) {
            return;
        }
        fun1(cur + 1, nums, curResult + nums[cur], target);
        fun1(cur + 1, nums, curResult - nums[cur], target);
    }

    /**
     * 动态规划：
     * 原问题等同于： 找到nums一个正子集和一个负子集，使得总和等于target
     * 我们假设P是正子集，N是负子集 例如： 假设nums = [1, 2, 3, 4, 5]，target = 3，一个可能的解决方案是+1-2+3-4+5 = 3 这里正子集P = [1, 3, 5]和负子集N = [2, 4]
     * 那么让我们看看如何将其转换为子集求和问题：
     * sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     * 2 * sum(P) = target + sum(nums)
     * 因此，原来的问题已转化为一个求子集的和问题： 找到nums的一个子集 P，使得sum(P) = (target + sum(nums)) / 2
     * 请注意，上面的公式已经证明target + sum(nums)必须是偶数，否则输出为0
     */
    private int fun2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || (target + sum) % 2 != 0) {
            return 0;
        }
        int p = (target + sum) / 2;
        int[] dp = new int[p + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = p; i >= num; i--) {
                //dp[i] = Math.max(dp[i],dp[num] + dp[i - num]);
                //dp[i] = dp[num] + dp[i - num];
                // 如下等式原因：i-num 和 num 一起构成i，有多个num，那么dp[i]就应该是dp[i - num]的和
                dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[p];
    }
}
