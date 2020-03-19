package greedy_algorithm.medium;

import org.junit.Test;

/**
 * 861. 翻转矩阵后的得分
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * <p>
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * <p>
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * <p>
 * 返回尽可能高的分数。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 */
public class ScoreAfterFlippingMatrix_861 {
    @Test
    public void test() {
        //matrixScore(new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}});
        matrixScore(new int[][]{{0, 1}, {1, 1}});
    }

    public int matrixScore(int[][] A) {
        return fun(A);
    }

    /**
     * 思路：最高位最重要，后续位尽可能为1；
     * 所以：先行转换，将所有的最高位都变为1；
     * 然后从第二列开始进行列转换，让每列的1多余0
     *
     * @param arr
     * @return
     */
    private int fun(int[][] arr) {
        // 行转换，确保没行最高位都为1
        int row = arr.length, col = arr[0].length;
        for (int i = 0; i < row; i++) {
            if (arr[i][0] == 0) {
                rowConvert(arr, i);
            }
        }
        for (int i = 1; i < col; i++) {
            int num1 = 0, num0 = 0;
            for (int x = 0; x < row; x++) {
                if (arr[x][i] == 1) {
                    num1++;
                } else {
                    num0++;
                }
            }
            if (num1 < num0) {
                colConert(arr, i);
            }
        }
        int result = 0;
        for (int i = 0; i < row; i++) {
            int tempResult = 0;
            for (int j = 0; j < col; j++) {
                tempResult += arr[i][j] * Math.pow(2, col - j - 1);
            }
            result += tempResult;
        }
        return result;
    }

    private void rowConvert(int[][] arr, int row) {
        for (int i = 0; i < arr[row].length; i++) {
            if (arr[row][i] == 0) {
                arr[row][i] = 1;
            } else {
                arr[row][i] = 0;
            }
        }
    }

    private void colConert(int[][] arr, int col) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][col] == 0) {
                arr[i][col] = 1;
            } else {
                arr[i][col] = 0;
            }
        }
    }
}
