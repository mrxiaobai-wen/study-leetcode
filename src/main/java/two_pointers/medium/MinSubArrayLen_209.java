package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 209. 长度最小的子数组
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 * <p>
 * 示例：
 * <p>
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *  
 * 进阶：
 * <p>
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinSubArrayLen_209 {

    @Test
    public void test() {
        Assert.assertEquals(2, minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(0, minSubArrayLen(100, new int[]{2, 3, 1, 2, 4, 3}));
        Assert.assertEquals(1, minSubArrayLen(1, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int s, int[] nums) {
        return fun(s, nums);
    }

    /**
     * 滑动窗口
     */
    private int fun(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (s <= 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        int slow = 0, fast = 0;
        int len = nums.length;
        int sum = nums[0];
        while (slow < len && fast < len) {
            if (sum >= s) {
                if (slow == fast) {
                    return 1;
                }
                result = Integer.min(result, fast - slow + 1);
            }
            if (sum < s) {
                if (fast == len - 1) {
                    // 已经到头了
                    break;
                }
                fast++;
                sum += nums[fast];
            } else if (sum >= s) {
                result = Integer.min(result, fast - slow + 1);
                sum -= nums[slow];
                slow++;
                if (fast < slow) {
                    fast = slow;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
