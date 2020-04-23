package dynamic_programming.medium;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class MinPathSum_64 {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] path = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int temp = grid[i][j];
                if (i - 1 >= 0 && j - 1 >= 0) {
                    temp += Math.min(path[i - 1][j], path[i][j - 1]);
                } else if (i - 1 >= 0) {
                    temp += path[i - 1][j];
                } else if (j - 1 >= 0) {
                    temp += path[i][j - 1];
                }
                path[i][j] = temp;
            }
        }
        return path[row - 1][col - 1];
    }
}
