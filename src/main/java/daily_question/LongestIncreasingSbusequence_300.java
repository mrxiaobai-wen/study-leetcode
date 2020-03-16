package daily_question;

import org.junit.Test;

/**
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LongestIncreasingSbusequence_300 {
    @Test
    public void test() {
        lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
    }

    /**
     * 因为是无序的，所以在A之后紧邻的一个大于A的数B，要判断取与不取的差别，比如：
     * [10,9,2,5,3,4]，在2之后是否应该取5
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        return solve(nums);
    }

    /**
     * 执行超时
     *
     * @param nums
     * @return
     */
    private int solve(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, fun(nums, i, null));
        }
        return max;
    }

    private int fun(int[] nums, int curIndex, Integer curMax) {
        if (curIndex >= nums.length) {
            return 0;
        }
        if (curMax == null) {
            return fun(nums, curIndex + 1, nums[curIndex]) + 1;
        }
        if (nums[curIndex] <= curMax) {
            return fun(nums, curIndex + 1, curMax);
        }

        return Math.max(fun(nums, curIndex + 1, nums[curIndex]) + 1,
                fun(nums, curIndex + 1, curMax));
    }


    /**
     * 动态规划：
     * 方法一：动态规划
     * 思路与算法
     * <p>
     * 定义 dp[i]dp[i] 为考虑前 ii 个元素，以第 ii 个数字结尾的最长上升子序列的长度，注意 \textit{nums}[i]nums[i] 必须被选取。
     * <p>
     * 我们从小到大计算 dp[]dp[] 数组的值，在计算 dp[i]dp[i] 之前，我们已经计算出 dp[0 \ldots i-1]dp[0…i−1] 的值，则状态转移方程为：
     * <p>
     * dp[i] = \text{max}(dp[j]) + 1, \text{其中} \, 0 \leq j < i \, \text{且} \, \textit{num}[j]<\textit{num}[i]
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     * <p>
     * 即考虑往 dp[0 \ldots i-1]dp[0…i−1] 中最长的上升子序列后面再加一个 \textit{nums}[i]nums[i]。由于 dp[j]dp[j] 代表 \textit{nums}[0 \ldots j]nums[0…j] 中以 \textit{nums}[j]nums[j] 结尾的最长上升子序列，所以如果能从 dp[j]dp[j] 这个状态转移过来，那么 \textit{nums}[i]nums[i] 必然要大于 \textit{nums}[j]nums[j]，才能将 \textit{nums}[i]nums[i] 放在 \textit{nums}[j]nums[j] 后面以形成更长的上升子序列。
     * <p>
     * 最后，整个数组的最长上升子序列即所有 dp[i]dp[i] 中的最大值。
     * <p>
     * \text{LIS}_{\textit{length}}= \text{max}(dp[i]), \text{其中} \, 0\leq i < n
     * LIS
     * length
     * ​
     * =max(dp[i]),其中0≤i<n
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    private int funDP(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxVal = Math.max(maxVal, dp[j]);
                }
            }
            dp[i] = maxVal + 1;
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
