package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThreeSumClosest_16 {

    @Test
    public void test() {
        Assert.assertEquals(2, threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        Assert.assertEquals(-2, threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1));
    }

    public int threeSumClosest(int[] nums, int target) {
        //return fun(nums,target);

        return fun2(nums, target);
    }

    /**
     * 遍历优化
     */
    private int fun2(int[] nums, int target) {
        Arrays.sort(nums);
        // 要考虑target为负数的情况
        //int best = Integer.MAX_VALUE;
        int best = 10000000;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = len - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    int tempK = k - 1;
                    while (j < tempK && nums[tempK] == nums[k]) tempK--;
                    k = tempK;
                } else {
                    int tmepJ = j + 1;
                    while (tmepJ < k && nums[tmepJ] == nums[tmepJ - 1]) tmepJ++;
                    j = tmepJ;
                }
            }
        }
        return best;
    }

    private int fun(int[] nums, int target) {
        int result = 0;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int tempResult = nums[i] + nums[j] + nums[k];
                    int tempDistance = Math.abs(target - tempResult);
                    if (tempDistance < distance) {
                        distance = tempDistance;
                        result = tempResult;
                    }
                }
            }
        }
        return result;
    }
}
