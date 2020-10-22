package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 845. 数组中的最长山脉
 *
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 *
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 *
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 *
 * 如果不含有 “山脉” 则返回 0。
 *
 * 示例 1：
 *
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 *
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 *
 * 提示：
 *
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestMountain_845 {

    @Test
    public void test() {
        Assert.assertEquals(4,longestMountain(new int[]{875,884,239,731,723,685}));
        Assert.assertEquals(5,longestMountain(new int[]{2,1,4,7,3,2,5}));
        Assert.assertEquals(0,longestMountain(new int[]{2,2,2}));
    }

    public int longestMountain(int[] A) {
        return fun(A);
    }

    private int fun(int[] a) {
        int len = a.length;
        int maxLength = 0;
        for (int i = 0;i < a.length - 2;) {
            int left = i;
            while (left < len - 1 && a[left] < a[left + 1]) {
                left++;
            }
            if (left == i) {
                i++;
                continue;
            }
            int right = left;
            while (right < len - 1 && a[right] > a[right + 1]) {
                right++;
            }
            if (right == left) {
                i = right + 1;
                continue;
            }
            if (i < left && left < right) {
                maxLength = Math.max(maxLength,right - i + 1);
            }
            //i = right + 1;
            // 这里只能是 i = right
            i = right;
        }
        return maxLength;
    }
}
