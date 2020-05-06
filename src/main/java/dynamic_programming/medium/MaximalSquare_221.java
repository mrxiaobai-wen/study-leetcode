package dynamic_programming.medium;

import org.junit.Test;

/**
 * 221. 最大正方形
 * <p>
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximalSquare_221 {

    @Test
    public void test() {
        System.out.println(maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}}));
    }

    public int maximalSquare(char[][] matrix) {
        return fun(matrix);
        // todo 动态规划优化没看懂
        // https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/
    }

    private int fun(char[][] matrix) {
        int row = matrix.length;
        int col = row > 0 ? matrix[0].length : 0;
        int[][] dp = new int[row + 1][col + 1];
        int tempResult = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    tempResult = Math.max(tempResult, dp[i][j]);
                }
            }
        }
        return tempResult * tempResult;
    }
}
