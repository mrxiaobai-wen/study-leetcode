package greedy_algorithm.medium;

/**
 * 870. 优势洗牌
 * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
 * <p>
 * 返回 A 的任意排列，使其相对于 B 的优势最大化。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [2,7,11,15], B = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 示例 2：
 * <p>
 * 输入：A = [12,24,8,32], B = [13,25,32,11]
 * 输出：[24,32,8,12]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 */
public class AdvantageShuffle_870 {
    public int[] advantageCount(int[] A, int[] B) {
        return fun(A, B);
    }

    private int[] fun(int[] A, int[] B) {
        for (int i = 0; i < B.length; i++) {
            int cur = i;
            int min = i;
            for (int j = i + 1; j < B.length; j++) {
                if (A[j] > B[i]) {
                    if (A[cur] <= B[i]) {
                        cur = j;
                    } else if (A[cur] > A[j]) {
                        cur = j;
                    }
                }
                if (A[j] < A[min]) {
                    min = j;
                }
            }
            if (cur != i) {
                int temp = A[i];
                A[i] = A[cur];
                A[cur] = temp;
            } else if (A[i] <= B[i]) {
                int temp = A[min];
                A[min] = A[i];
                A[i] = temp;
            }
        }
        return A;
    }
}
