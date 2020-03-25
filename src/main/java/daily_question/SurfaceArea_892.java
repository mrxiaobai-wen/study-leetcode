package daily_question;

import org.junit.Test;

/**
 * 892. 三维形体的表面积
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 * <p>
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * <p>
 * 请你返回最终形体的表面积。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[2]]
 * 输出：10
 * 示例 2：
 * <p>
 * 输入：[[1,2],[3,4]]
 * 输出：34
 * 示例 3：
 * <p>
 * 输入：[[1,0],[0,2]]
 * 输出：16
 * 示例 4：
 * <p>
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 示例 5：
 * <p>
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 50
 * 0 <= grid[i][j] <= 50
 */
public class SurfaceArea_892 {
    @Test
    public void test() {
        System.out.println(surfaceArea(new int[][]{{2}}));
        System.out.println(surfaceArea(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
    }

    public int surfaceArea(int[][] grid) {
        return fun(grid);
    }

    private int fun(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // 没有立方体的不算面积
                    //result++;
                } else {
                    result += 2;
                    // 考虑四个方向
                    if (i - 1 >= 0) {
                        // 左
                        result += Math.max(grid[i][j] - grid[i - 1][j], 0);
                    } else {
                        result += grid[i][j];
                    }
                    if (i + 1 < m) {
                        // 右
                        result += Math.max(grid[i][j] - grid[i + 1][j], 0);
                    } else {
                        result += grid[i][j];
                    }
                    if (j - 1 >= 0) {
                        // 前
                        result += Math.max(grid[i][j] - grid[i][j - 1], 0);
                    } else {
                        result += grid[i][j];
                    }
                    if (j + 1 < n) {
                        // 后
                        result += Math.max(grid[i][j] - grid[i][j + 1], 0);
                    } else {
                        result += grid[i][j];
                    }
                }
            }
        }
        return result;
    }
}
