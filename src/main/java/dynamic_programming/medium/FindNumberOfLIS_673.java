package dynamic_programming.medium;

import org.junit.Test;

/**
 * 673. 最长递增子序列的个数
 *
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindNumberOfLIS_673 {

    @Test
    public void test() {
        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(findNumberOfLIS(new int[]{2,2,2,2,2}));
        assert 3 == findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2});
        assert 1 == findNumberOfLIS(new int[]{100,90,80,70,60,50,60,70,80,90,100});
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int[] path = new int[nums.length];
        dp[0] = 1;
        path[0] = 1;
        for (int i = 1;i < nums.length;i++) {
            dp[i] = 1;
            path[i] = 1;
            for (int j = 0;j < i;j++) {
                if (nums[j] < nums[i]) {
                    int temp = dp[j] + 1;
                    if (dp[i] < temp) {
                        dp[i] = temp;
                        path[i] = path[j];
                    } else if (dp[i] == temp) {
                        path[i] += path[j];
                    }
                }
            }
        }
        int result = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0;i < nums.length;i++) {
            if (dp[i] > max) {
                max = dp[i];
                result = path[i];
            } else if (dp[i] == max){
                result += path[i];
            }
        }
        return result;
    }
}
