package heap.medium;

/**
 * 面试题 17.09. 第 k 个数
 * <p>
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。 例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 5
 * <p>
 * 输出: 9
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/get-kth-magic-number-lcci 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GetKthMagicNumber {

    public int getKthMagicNumber(int k) {
        return fun(k);
    }

    /**
     * 使用动态规划
     */
    private int fun(int k) {
        int[] dp = new int[k];
        dp[0] = 1;
        int p3 = 0, p5 = 0, p7 = 0;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(Math.min(3 * dp[p3], 5 * dp[p5]), 7 * dp[p7]);
            if (3 * dp[p3] == dp[i]) {
                p3++;
            }
            if (5 * dp[p5] == dp[i]) {
                p5++;
            }
            if (7 * dp[p7] == dp[i]) {
                p7++;
            }
        }
        return dp[k - 1];
    }
}
