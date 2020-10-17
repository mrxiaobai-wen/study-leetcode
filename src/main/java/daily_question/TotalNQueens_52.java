package daily_question;

/**
 * 52. N皇后 II
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 * 提示：
 *
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TotalNQueens_52 {

    int result = 0;
    public int totalNQueens(int n) {
        int[][] arr = new int[n][n];
        fun(0,arr);
        return result;
    }

    private void fun(int row,int[][] arr) {
        int n = arr.length;
        if (row >= n) {
            // 可行方法之一，结果加一
            result++;
            return;
        }
        for (int i = 0;i < n;i++) {
            // 占林该位
            arr[row][i] = 1;
            if (judge(arr,row,i)) {
                // 判断该位合法，继续下一行
                fun(row + 1,arr);
            }
            // 移除该位
            arr[row][i] = 0;
        }
    }

    private boolean judge(int[][] arr,int row,int col) {
        int n = arr.length;
        // 判断正上方
        for (int i = row - 1;i >= 0;i--) {
            if (arr[i][col] == 1) {
                return false;
            }
        }
        // 判断左上方
        for (int i = row - 1,j = col - 1;i >= 0 && j >= 0;i--,j--) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        // 判断右上方
        for (int i = row - 1,j = col + 1;i >= 0 && j < n;i--,j++) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        return true;
    }
}
