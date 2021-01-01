package daily_question.daily_question_2020;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 */
public class MaxAreaOfIsland_695 {
    public int maxAreaOfIsland(int[][] grid) {
        return fun(grid);
    }

    /**
     * 深度优先遍历
     *
     * @param grid
     * @return
     */
    private int fun(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                queue.add(code(i, j));
                grid[i][j] = 0;
                int sum = 0;
                while (!queue.isEmpty()) {
                    int[] temp = decode(queue.poll());
                    int x = temp[0];
                    int y = temp[1];
                    sum++;
                    // 上下左右
                    if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                        queue.add(code(x, y - 1));
                        grid[x][y - 1] = 0;
                    }
                    if (y + 1 < col && grid[x][y + 1] == 1) {
                        queue.add(code(x, y + 1));
                        grid[x][y + 1] = 0;
                    }
                    if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                        queue.add(code(x - 1, y));
                        grid[x - 1][y] = 0;
                    }
                    if (x + 1 < row && grid[x + 1][y] == 1) {
                        queue.add(code(x + 1, y));
                        grid[x + 1][y] = 0;
                    }
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    private int code(int x, int y) {
        return x * 100 + y;
    }

    private int[] decode(int num) {
        int x = num / 100;
        int y = num % 100;
        int[] result = new int[2];
        result[0] = x;
        result[1] = y;
        return result;
    }
}
