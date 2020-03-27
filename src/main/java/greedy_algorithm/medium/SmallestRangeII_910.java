package greedy_algorithm.medium;

import java.util.Arrays;

/**
 * 910. 最小差值 II
 * 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。
 * <p>
 * 在此过程之后，我们得到一些数组 B。
 * <p>
 * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1], K = 0
 * 输出：0
 * 解释：B = [1]
 * 示例 2：
 * <p>
 * 输入：A = [0,10], K = 2
 * 输出：6
 * 解释：B = [2,8]
 * 示例 3：
 * <p>
 * 输入：A = [1,3,6], K = 3
 * 输出：3
 * 解释：B = [4,6,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 */
public class SmallestRangeII_910 {
    /**
     * 代码和官方给出的基本上没有区别，主要是官方描述的让人摸不着头脑，我在这里重新讲一下。
     * 较小的要加K，较大的要减K，那么就形成了两端递增的序列，两个递增序列的话就有两个极大
     * 值和两个极小值，最大值由两个极大值来争（假如在i处分割，则是A【i】+K和A【length-1】
     * -K），同理最小值由A【0】+K和A【i+1】-K来争，现在就剩一个问题了，即怎么找到那个分割
     * 点，但是分割点没有什么特殊的性质，我们通过遍历所有的可能性最合适的分割点，其实也就是
     * 最大值最小值的差值最小的时候，当然我们最后不是要返回分割点是什么，而是最小差值。
     * <p>
     * 作者：jianbing-5
     * 链接：https://leetcode-cn.com/problems/smallest-range-ii/solution/jiao-xiao-de-yao-jia-kjiao-da-de-yao-jian-kwen-ti-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param A
     * @param K
     * @return
     */
    public int smallestRangeII(int[] A, int K) {
        return fun(A, K);
    }

    /**
     * 参考代码
     *
     * @param A
     * @param k
     * @return
     */
    private int fun(int[] A, int k) {
        int len = A.length;
        Arrays.sort(A);
        int res = A[len - 1] - A[0];
        for (int i = 0; i < len - 1; i++) {
            int max = Math.max(A[i] + k, A[len - 1] - k);
            int min = Math.min(A[0] + k, A[i + 1] - k);
            res = Math.min(res, max - min);
        }
        return res;
    }
}
