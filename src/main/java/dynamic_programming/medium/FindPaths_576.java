package dynamic_programming.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 576. 出界的路径数
 * <p>
 * 给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过网格边界。
 * 但是，你最多可以移动 N 次。找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 109 + 7 的值。
 * <p>
 * 示例 1：
 * <p>
 * 输入: m = 2, n = 2, N = 2, i = 0, j = 0
 * 输出: 6
 * 解释:
 * <p>
 * 示例 2：
 * <p>
 * 输入: m = 1, n = 3, N = 3, i = 0, j = 1
 * 输出: 12
 * 解释:
 * <p>
 * 说明:
 * <p>
 * 球一旦出界，就不能再被移动回网格内。
 * 网格的长度和高度在 [1,50] 的范围内。
 * N 在 [0,50] 的范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/out-of-boundary-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindPaths_576 {

    @Test
    public void test() {
        Assert.assertEquals(6, findPaths(2, 2, 2, 0, 0));
        Assert.assertEquals(12, findPaths(1, 3, 3, 0, 1));
    }


    private int[] offesets = new int[]{0, 1, 0, -1, 0};
    private Integer[][][] memo;
    private int mod = (int) (1e9 + 7);

    /**
     * todo 较难理解
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        memo = new Integer[m][n][N + 1];
        return fun(m, n, i, j, N);
    }

    private int fun(int m, int n, int i, int j, int N) {
        if (N < 0) {
            return 0;
        }
        if (i < 0 || i == m || j < 0 || j == n) {
            return 1;
        }
        if (memo[i][j][N] != null) {
            return memo[i][j][N];
        }
        int result = 0;
        for (int k = 0; k < 4; k++) {
            result = (int) (((long) result + fun(m, n, i + offesets[k], j + offesets[k + 1], N - 1)) % mod);
        }
        return memo[i][j][N] = result;
    }
}
