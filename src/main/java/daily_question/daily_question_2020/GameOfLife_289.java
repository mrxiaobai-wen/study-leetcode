package daily_question.daily_question_2020;

/**
 * 289. 生命游戏
 * <p>
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *  
 * 进阶：
 * <p>
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GameOfLife_289 {
    public void gameOfLife(int[][] board) {
        fun1(board);
    }

    private void fun1(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = isLive(board, i, j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = copy[i][j];
            }
        }
    }

    private int isLive(int[][] board, int x, int y) {
        int m = board.length;
        int n = board[0].length;
        // 上下左右
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        // 四个斜方向
        int[] ox = new int[]{-1, -1, 1, 1};
        int[] oy = new int[]{-1, 1, -1, 1};
        int surroundLive = 0;
        for (int i = 0; i < 4; i++) {
            int newX1 = x + dx[i];
            int newY1 = y + dy[i];
            if (newX1 >= 0 && newX1 < m && newY1 >= 0 && newY1 < n && board[newX1][newY1] == 1) {
                surroundLive++;
            }
            int newX2 = x + ox[i];
            int newY2 = y + oy[i];
            if (newX2 >= 0 && newX2 < m && newY2 >= 0 && newY2 < n && board[newX2][newY2] == 1) {
                surroundLive++;
            }

        }
        if (board[x][y] == 1) {
            if (surroundLive >= 2 && surroundLive <= 3) {
                return 1;
            }
        } else {
            // 死细胞
            if (surroundLive == 3) {
                return 1;
            }
        }
        return 0;
    }
}
