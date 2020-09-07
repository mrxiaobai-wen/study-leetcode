package dynamic_programming.medium;

import org.junit.Test;

/**
 * 718. 最长重复子数组
 *
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *
 * 提示：
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxLengthRepeatedSubarray_718 {

    @Test
    public void ceshi() {
        System.out.println(findLength(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7}));
    }

    public int findLength(int[] A, int[] B) {
        return fun(A,B);
    }

    private int fun(int[] A,int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = Integer.MIN_VALUE;
        for (int i = m - 1;i >= 0;i--) {
            for (int j = n - 1;j >= 0;j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                res = Math.max(dp[i][j],res);
            }
        }
        return res;
    }
}
