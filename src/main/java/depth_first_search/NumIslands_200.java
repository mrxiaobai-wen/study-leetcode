package depth_first_search;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * 输出: 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumIslands_200 {
    public int numIslands(char[][] grid) {
        return fun1(grid);
    }

    char land = '1';

    /**
     * 深度优先
     *
     * @return
     */
    private int fun1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rowNum = grid.length;
        int colNum = grid[0].length;
        int result = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == land) {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }

        return result;
    }

    /**
     * 深度优先遍历
     *
     * @param grid
     * @param row
     * @param col
     */
    private void dfs(char[][] grid, int row, int col) {
        int rowNum = grid.length;
        int colNum = grid[0].length;

        if (row < 0 || row >= rowNum || col < 0 || col >= colNum) {
            // 过界
            return;
        }
        if (grid[row][col] != land) {
            return;
        }
        grid[row][col] = '0';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }
}
