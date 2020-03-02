package greedy_algorithm.simple;

/**
 * 1005. K 次取反后最大化的数组和
 * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
 * <p>
 * 以这种方式修改数组后，返回数组可能的最大和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [4,2,3], K = 1
 * 输出：5
 * 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
 * 示例 2：
 * <p>
 * 输入：A = [3,-1,0,2], K = 3
 * 输出：6
 * 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
 * 示例 3：
 * <p>
 * 输入：A = [2,-3,-1,5,-4], K = 2
 * 输出：13
 * 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 1 <= K <= 10000
 * -100 <= A[i] <= 100
 */
public class LargestSumAfterKNegations_1005 {
    public int largestSumAfterKNegations(int[] A, int K) {
        return fun1(A, K);
    }

    private int fun1(int[] A, int K) {
        int[] array = new int[201];
        for (Integer temp : A) {
            array[temp + 100] += 1;
        }
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 201; j++) {
                if (array[j] > 0) {
                    array[j]--;
                    array[-1 * (j - 100) + 100]++;
                    break;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 201; i++) {
            result += (array[i] * (i - 100));
        }
        return result;
    }
}
