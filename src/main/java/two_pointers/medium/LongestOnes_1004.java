package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1004. 最大连续1的个数 III
 * <p>
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * <p>
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * <p>
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestOnes_1004 {

    @Test
    public void test() {
        Assert.assertEquals(6, longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        Assert.assertEquals(10, longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    public int longestOnes(int[] A, int K) {
        return fun(A, K);
    }

    private int fun(int[] a, int k) {
        int oneNum = 0, zeroNum = 0;
        int maxResult = 0;
        for (int left = 0, right = 0; right < a.length; right++) {
            if (a[right] == 1) {
                oneNum++;
            } else {
                zeroNum++;
            }
            if (zeroNum > k) {
                if (a[left] == 0) {
                    zeroNum--;
                } else {
                    oneNum--;
                }
                left++;
            } else {
                maxResult = Math.max(maxResult, right - left + 1);
            }
        }
        return maxResult;
    }
}
