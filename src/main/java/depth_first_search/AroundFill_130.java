package depth_first_search;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AroundFill_130 {

    public void solve(char[][] board) {
        fun1(board);
    }

    char charX = 'X';
    char charO = 'O';
    char charTemp = '*';

    private void fun1(char[][] board) {
        // ["X","X","X","X"]
        // ["X","O","O","X"]
        // ["X","X","O","X"]
        // ["X","O","X","X"]
        int hight = board.length;
        int width = hight > 0 ? board[0].length : 0;
        for (int i = 0; i < width; i++) {
            // 遍历上下两个边界为 O 的点
            if (board[0][i] == charO) {
                fill(0, i, board, width, hight);
            }
            if (board[hight - 1][i] == charO) {
                fill(hight - 1, i, board, width, hight);
            }
        }
        for (int i = 1; i < hight - 1; i++) {
            // 遍历左右两个边界为 O 的点
            if (board[i][0] == charO) {
                fill(i, 0, board, width, hight);
            }
            if (board[i][width - 1] == charO) {
                fill(i, width - 1, board, width, hight);
            }
        }
        // 恢复被替换为 * 的内容,并填充其余的O为X
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == charTemp) {
                    board[i][j] = charO;
                } else if (board[i][j] == charO) {
                    board[i][j] = charX;
                }
            }
        }
    }

    /**
     * 填充当前节点以及上下左右四个方向的节点
     *
     * @param currentX 当前X坐标
     * @param currentY 当前Y坐标
     * @param board    二维
     * @param width    二维宽
     * @param hight    二维高
     */
    void fill(int currentY, int currentX, char[][] board, int width, int hight) {
        if (currentX < 0 || currentX >= width || currentY < 0 || currentY >= hight) {
            // 超出范围
            return;
        }
        if (board[currentY][currentX] != charO) {
            return;
        }
        board[currentY][currentX] = charTemp;
        // 上
        fill(currentY + 1, currentX, board, width, hight);
        // 下
        fill(currentY - 1, currentX, board, width, hight);
        // 左
        fill(currentY, currentX - 1, board, width, hight);
        // 右
        fill(currentY, currentX + 1, board, width, hight);
    }
}
