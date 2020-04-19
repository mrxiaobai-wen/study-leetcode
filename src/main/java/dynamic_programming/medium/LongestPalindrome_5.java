package dynamic_programming.medium;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestPalindrome_5 {
    public String longestPalindrome(String s) {
        // 中心扩散法
        //return fun1(s);
        // 动态规划
        return fun2(s);
    }

    /**
     * 中心扩散法
     *
     * @return
     */
    private String fun1(String s) {
        if (s == null || s.length() <= 0) {
            return "";
        }
        int maxLen = 0;
        int maxStart = 0;
        int strLen = s.length();
        for (int i = 0; i < strLen; i++) {
            int len = 1;
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                len++;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                right++;
                len++;
            }
            while (left >= 0 && right < strLen && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                len += 2;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
        }
        return s.substring(maxStart + 1, maxStart + 1 + maxLen);
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    private String fun2(String s) {
        if (s == null || s.length() <= 0) {
            return "";
        }
        int strLen = s.length();
        boolean[][] dp = new boolean[strLen][strLen];
        int maxStart = 0;
        int maxEnd = 0;
        int maxLen = 1;
        for (int right = 1; right < strLen; right++) {
            for (int left = 0; left < right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        maxStart = left;
                        maxEnd = right;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }
}
