package daily_question.daily_question_2020;

/**
 * 327. 区间和的个数
 * <p>
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * <p>
 * 说明: 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2, 输出: 3 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-of-range-sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountRangeSum_327 {

    public int countRangeSum(int[] nums, int lower, int upper) {
        return fun(nums, lower, upper);
    }

    private int fun(int[] nums, int lower, int upper) {
        int len = nums.length;
        int result = 0;
        for (int i = 0; i < len; i++) {
            long sum = 0;
            for (int j = i; j < len; j++) {
                if (i == j) {
                    sum = nums[j];
                } else {
                    sum += nums[j];
                }
                if (lower <= sum && sum <= upper) {
                    result++;
                }
            }
        }
        return result;
    }
}
