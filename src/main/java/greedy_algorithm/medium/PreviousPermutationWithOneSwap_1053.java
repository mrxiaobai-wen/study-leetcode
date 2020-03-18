package greedy_algorithm.medium;

import org.junit.Test;

/**
 * 1053. 交换一次的先前排列
 * 给你一个正整数的数组 A（其中的元素不一定完全不同），请你返回可在 一次交换（交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。
 * <p>
 * 如果无法这么操作，就请返回原数组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,1]
 * 输出：[3,1,2]
 * 解释：
 * 交换 2 和 1
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：[1,1,5]
 * 输出：[1,1,5]
 * 解释：
 * 这已经是最小排列
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：[1,9,4,6,7]
 * 输出：[1,7,4,6,9]
 * 解释：
 * 交换 9 和 7
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * 输入：[3,1,1,3]
 * 输出：[1,3,1,3]
 * 解释：
 * 交换 1 和 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 1 <= A[i] <= 10000
 */
public class PreviousPermutationWithOneSwap_1053 {
    @Test
    public void test() {
        prevPermOpt1(new int[]{3, 1, 1, 3});
    }

    public int[] prevPermOpt1(int[] A) {
        return fun(A);
    }

    /**
     * 思路：要想交换后的数组按照字典序列小于原数组，那么交换的只能是降序中的两个数字；
     * 要使交换得到的结果是所有结果中最大的一个，那么应该尽可能的交换后面低位部分，且降序中的最大值交换到前面去！
     *
     * @param source
     * @return
     */
    private int[] fun(int[] source) {
        int len = source.length;
        for (int i = len - 2; i >= 0; i--) {
            int curIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (source[i] > source[j]) {
                    if (curIndex == i) {
                        curIndex = j;
                    } else if (source[j] > source[curIndex]) {
                        curIndex = j;
                    }
                }
            }
            if (curIndex != i) {
                int temp = source[i];
                source[i] = source[curIndex];
                source[curIndex] = temp;
                return source;
            }
        }
        return source;
    }
}
