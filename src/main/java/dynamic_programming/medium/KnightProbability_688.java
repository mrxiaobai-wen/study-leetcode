package dynamic_programming.medium;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 688. “马”在棋盘上的概率
 * <p>
 * 已知一个 NxN 的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为 (0, 0)，最右下角的记为 (N-1, N-1)。 
 * 现有一个 “马”（也译作 “骑士”）位于 (r, c) ，并打算进行 K 次移动。 
 * 如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。
 * <p>
 * 现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了 K 次或跳到了棋盘外面。
 * <p>
 * 求移动结束后，“马” 仍留在棋盘上的概率。
 * <p>
 * 示例：
 * <p>
 * 输入: 3, 2, 0, 0
 * 输出: 0.0625
 * 解释:
 * 输入的数据依次为 N, K, r, c
 * 第 1 步时，有且只有 2 种走法令 “马” 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。
 * 所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
 *  
 * 注意：
 * <p>
 * N 的取值范围为 [1, 25]
 * K 的取值范围为 [0, 100]
 * 开始时，“马” 总是位于棋盘上
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KnightProbability_688 {

    @Test
    public void test() {
        System.out.println(knightProbability(3, 2, 0, 0));
        assert 0.0625 == knightProbability(3, 2, 0, 0);
    }

    public double knightProbability(int N, int K, int r, int c) {
        // 执行超时
        //return fun1(N,K,r,c);

        return fun2(N, K, r, c);
    }

    /**
     * 动态规划
     * todo 较难
     */
    public double fun2(int N, int K, int sr, int sc) {
        int[][] dir = new int[][]{{-2, 1}, {-2, -1}, {2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        double[][] dp = new double[N][N];
        dp[sr][sc] = 1;
        for (; K > 0; K--) {
            double[][] dp2 = new double[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 0; k < 8; k++) {
                        int dx = dir[k][0];
                        int dy = dir[k][1];
                        int newX = r + dx;
                        int newY = c + dy;
                        if (newX >= 0 && newX < N && newY >= 0 && newY < N) {
                            dp2[newX][newY] += dp[r][c] / 8.0;
                        }
                    }
                }
            }
            dp = dp2;
        }
        double result = 0;
        for (double[] r : dp) {
            for (double num : r) {
                result += num;
            }
        }
        return result;
    }

    /**
     * 广度优先
     */
    public double fun1(int N, int K, int r, int c) {
        int[][] dir = new int[][]{{-2, 1}, {-2, -1}, {2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        double outNum = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        for (int i = 0; i < K && queue.size() > 0; i++) {
            Queue<int[]> newQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] location = queue.poll();
                for (int j = 0; j < 8; j++) {
                    int dX = dir[j][0];
                    int dY = dir[j][1];
                    int newX = location[0] + dX;
                    int newY = location[1] + dY;
                    if (newX >= 0 && newX < N && newY >= 0 && newY < N) {
                        newQueue.add(new int[]{newX, newY});
                    } else {
                        outNum++;
                    }
                }
            }
            queue = newQueue;
        }
        //return (double) queue.size() / (queue.size() + outNum);
        // .....不是上面那种概率计算方式
        return queue.size() / Math.pow(8, K);
    }
}
