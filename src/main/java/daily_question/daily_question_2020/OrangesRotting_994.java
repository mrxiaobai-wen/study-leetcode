package daily_question.daily_question_2020;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * <p>
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 * <p>
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotting-oranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OrangesRotting_994 {
    public static void main(String[] args) {
        OrangesRotting_994 ceshi = new OrangesRotting_994();
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(ceshi.orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {
        return fun1(grid);
    }

    final int broken = 2;
    final int fresh = 1;

    /**
     * 思路：
     * 使用队列，将数据分段处理未坏掉的橘子
     * 将横纵坐标转换为数字，横坐标左移三位加上纵坐标即可。（因为横纵坐标限定最大为10）
     *
     * @param grid
     */
    private int fun1(int[][] grid) {
        Queue<Integer> brokenQueue = new LinkedList<>();
        int xLen = grid.length;
        int yLen = grid[0].length;
        int orangeNum = 0;
        int brokenNum = 0;
        for (int x = 0; x < xLen; x++) {
            for (int y = 0; y < yLen; y++) {
                if (grid[x][y] == broken) {
                    brokenQueue.add(coding(x, y));
                }
                if (grid[x][y] != 0) {
                    orangeNum++;
                }
            }
        }
        int result = 0;
        while (!brokenQueue.isEmpty()) {
            int curSize = brokenQueue.size();
            brokenNum += brokenQueue.size();
            boolean flag = false;
            while (curSize > 0) {
                curSize--;
                int[] xy = decoding(brokenQueue.poll());
                int x = xy[0];
                int y = xy[1];
                // 上下左右
                if (y + 1 < yLen && grid[x][y + 1] == fresh) {
                    grid[x][y + 1] = broken;
                    flag = true;
                    brokenQueue.add(coding(x, y + 1));
                }
                if (y - 1 >= 0 && grid[x][y - 1] == fresh) {
                    grid[x][y - 1] = broken;
                    flag = true;
                    brokenQueue.add(coding(x, y - 1));
                }
                if (x - 1 >= 0 && grid[x - 1][y] == fresh) {
                    grid[x - 1][y] = broken;
                    flag = true;
                    brokenQueue.add(coding(x - 1, y));
                }
                if (x + 1 < xLen && grid[x + 1][y] == fresh) {
                    grid[x + 1][y] = broken;
                    flag = true;
                    brokenQueue.add(coding(x + 1, y));
                }
            }
            if (flag) {
                result++;
            } else {
                break;
            }
        }
        // 考虑有不能感染的情况
        if (orangeNum == brokenNum) {
            return result;
        }
        return -1;
    }

    private int coding(int x, int y) {
        return (x * 1000) + y;
    }

    private int[] decoding(int num) {
        int[] result = new int[2];
        result[1] = num % 1000;
        result[0] = num / 1000;
        return result;
    }
}
