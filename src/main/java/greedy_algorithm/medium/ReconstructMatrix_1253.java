package greedy_algorithm.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1253. 重构 2 行二进制矩阵
 * 给你一个 2 行 n 列的二进制数组：
 * <p>
 * 矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是 0 就是 1。
 * 第 0 行的元素之和为 upper。
 * 第 1 行的元素之和为 lower。
 * 第 i 列（从 0 开始编号）的元素之和为 colsum[i]，colsum 是一个长度为 n 的整数数组。
 * 你需要利用 upper，lower 和 colsum 来重构这个矩阵，并以二维整数数组的形式返回它。
 * <p>
 * 如果有多个不同的答案，那么任意一个都可以通过本题。
 * <p>
 * 如果不存在符合要求的答案，就请返回一个空的二维数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：upper = 2, lower = 1, colsum = [1,1,1]
 * 输出：[[1,1,0],[0,0,1]]
 * 解释：[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
 * 示例 2：
 * <p>
 * 输入：upper = 2, lower = 3, colsum = [2,2,1,1]
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 */
public class ReconstructMatrix_1253 {
    @Test
    public void test() {
        System.out.println(reconstructMatrix(2, 1, new int[]{1, 1, 1}));
    }

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        /*List<List<Integer>> tempList = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0;i < colsum.length;i++) {
            list1.add(0);
            list2.add(0);
        }
        tempList.add(list1);
        tempList.add(list2);
        List<List<Integer>> result = fun(upper,lower,colsum,0,tempList);
        if (result != null) {
            return result;
        }
        return new ArrayList<>();*/

        return fun(upper, lower, colsum);
    }

    /**
     * 执行超时
     *
     * @param upper
     * @param lower
     * @param colsum
     * @param colIndex
     * @param result
     * @return
     */
    private List<List<Integer>> fun(int upper, int lower, int[] colsum, int colIndex, List<List<Integer>> result) {
        if (upper == 0 && lower == 0 && colIndex >= colsum.length) {
            return result;
        } else if (colIndex >= colsum.length) {
            return null;
        } else if (upper < 0 || upper < 0) {
            return null;
        }
        List<Integer> list1 = result.get(0);
        List<Integer> list2 = result.get(1);
        for (int i = 0; i <= upper && i <= colsum[colIndex]; i++) {
            list1.set(colIndex, i);
            int j = colsum[colIndex] - i;
            if (j > lower) {
                continue;
            }
            list2.set(colIndex, j);
            List<List<Integer>> tempResult = fun(upper - i, lower - j, colsum, colIndex + 1, result);
            if (tempResult != null) {
                return tempResult;
            }
        }
        return null;
    }

    /**
     * 参考代码
     *
     * @param upper
     * @param lower
     * @param colsum
     * @return
     */
    private List<List<Integer>> fun(int upper, int lower, int[] colsum) {
        int len = colsum.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += colsum[i];
        }
        List<List<Integer>> result = new ArrayList<>();
        if (upper + lower != sum) {
            return result;
        }
        List<Integer> up = new ArrayList<>();
        List<Integer> lo = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (colsum[i] == 2) {
                up.add(1);
                upper--;
                lo.add(1);
                lower--;
            } else if (colsum[i] == 0) {
                up.add(0);
                lo.add(0);
            } else {
                if (upper > lower) {
                    up.add(1);
                    upper--;
                    lo.add(0);
                } else {
                    up.add(0);
                    lower--;
                    lo.add(1);
                }
            }
            if (upper < 0 || lower < 0) {
                return result;
            }
        }
        result.add(up);
        result.add(lo);
        return result;
    }
}
