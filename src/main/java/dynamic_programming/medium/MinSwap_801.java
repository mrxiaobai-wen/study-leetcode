package dynamic_programming.medium;

import org.junit.Test;

import java.util.List;

/**
 * 801. 使序列递增的最小交换次数
 * <p>
 * 我们有两个长度相等且不为空的整型数组 A 和 B 。
 * <p>
 * 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
 * <p>
 * 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。
 * <p>
 * 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
 * <p>
 * 示例:
 * 输入: A = [1,3,5,4], B = [1,2,3,7]
 * 输出: 1
 * 解释:
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 两个数组均为严格递增的。
 * 注意:
 * <p>
 * A, B 两个数组的长度总是相等的，且长度的范围为 [1, 1000]。
 * A[i], B[i] 均为 [0, 2000]区间内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-swaps-to-make-sequences-increasing
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinSwap_801 {

    @Test
    public void ceshi() {
        //System.out.println(minSwap(new int[]{1,3,5,4},new int[]{1,2,3,7}));
        System.out.println(minSwap(new int[]{0, 3, 5}, new int[]{2, 1, 4}));
    }

    public int minSwap(int[] A, int[] B) {
        return fun1(A, B);
    }

    private int fun1(int[] A, int[] B) {
        int len = A.length;
        // 第一列：不交换 第二列：交换
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                    // 当前不交换，则上一个可交换可不交换
                    dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                    // 当前交换，则上一个可交换可不交换
                    dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
                } else {
                    // 不交换，则上一个位置也不能交换
                    dp[i][0] = dp[i - 1][0];
                    // 交换，则上一个也必须交换
                    dp[i][1] = dp[i - 1][1] + 1;
                }
            } else {
                // 当前不交换，则上一个必须交换
                dp[i][0] = dp[i - 1][1];
                // 当前交换，则上一个不能交换
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[len - 1][0], dp[len - 1][1]);
    }
}
