package dynamic_programming.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 516. 最长回文子序列
 * <p>
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * <p>
 * 示例 1:
 * 输入:
 * <p>
 * "bbbab"
 * 输出:
 * <p>
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 * <p>
 * 示例 2:
 * 输入:
 * <p>
 * "cbbd"
 * 输出:
 * <p>
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindromeSubseq_516 {

    @Test
    public void test() {
        Assert.assertEquals(4, dp1("bbbab"));
        Assert.assertEquals(2, dp1("cbbd"));
    }

    public int longestPalindromeSubseq(String s) {
        return dp1(s);
    }

    /**
     * 解题思路：
     * 状态
     * f[i][j] 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少。
     * <p>
     * 转移方程
     * 如果 s 的第 i 个字符和第 j 个字符相同的话
     * <p>
     * f[i][j] = f[i + 1][j - 1] + 2
     * <p>
     * 如果 s 的第 i 个字符和第 j 个字符不同的话
     * <p>
     * f[i][j] = max(f[i + 1][j], f[i][j - 1])
     * <p>
     * 然后注意遍历顺序，i 从最后一个字符开始往前遍历，j 从 i + 1 开始往后遍历，这样可以保证每个子问题都已经算好了。
     * <p>
     * 初始化
     * f[i][i] = 1 单个字符的最长回文序列是 1
     * <p>
     * 结果
     * f[0][n - 1]
     * <p>
     * 作者：a380922457
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/dong-tai-gui-hua-si-yao-su-by-a380922457-3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private int dp1(String s) {
        char[] arr = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        int len = s.length();
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
