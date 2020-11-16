package week_competition.single_week.week_215;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1658. 将 x 减到 0 的最小操作数
 * <p>
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。 请注意，需要 修改 数组以供接下来的操作使用。
 * <p>
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,4,2,3], x = 5 输出：2 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。 示例 2：
 * <p>
 * 输入：nums = [5,6,7,8,9], x = 4 输出：-1 示例 3：
 * <p>
 * 输入：nums = [3,2,20,1,1,3], x = 10 输出：5 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105 1 <= nums[i] <= 104 1 <= x <= 109
 */
public class MinOperations_1658 {

    @Test
    public void test() {
        Assert.assertEquals(5, minOperations(new int[]{3, 2, 20, 1, 1, 3}, 10));
    }

    public int minOperations(int[] nums, int x) {
        return fun(nums, x, 0, nums.length - 1, 0);
    }


    /**
     * 参考思路： 滑动时间窗口 找外部最小即找中间最大
     */
    private int fun(int[] nums, int x) {
        // 使用滑动窗口找中间最长的片段使得sum(片段)=sum(nums)-x
        int maxPart = -1;
        int sum = Arrays.stream(nums).sum();
        int currentSum = 0;
        int left = 0;
        int right = 0;
        while (left < nums.length) {
            // 如果右边未到尽头，不断先向右探测片段，如果大于目标sum-x则左边移动直到结束
            if (right < nums.length) {
                currentSum += nums[right++];
            }
            while (currentSum > sum - x && left < nums.length) {
                currentSum -= nums[left++];
            }
            if (currentSum == sum - x) {
                maxPart = Math.max(maxPart, right - left);
            }
            if (right == nums.length) {
                left++;
            }
        }
        return maxPart == -1 ? -1 : nums.length - maxPart;
    }

    /**
     * 前缀和+后缀和
     */
    private int fun3(int[] nums, int x) {
        int len = nums.length;
        //前缀和
        int[] preSum = new int[len + 1];

        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        //后缀和
        Map<Integer, Integer> map = new HashMap<>();

        int[] latterSum = new int[len + 1];
        map.put(0, 0);
        int idx = 1;
        for (int i = len - 1; i >= 0; i--) {
            latterSum[idx] = latterSum[idx - 1] + nums[i];
            map.put(latterSum[idx], idx);
            idx++;
        }
        if (preSum[len] < x || latterSum[len] < x) {
            return -1;//避免多次计算
        }
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < len + 1; i++) {
            int pre = preSum[i];
            if (map.containsKey(x - pre)) {
                res = Math.min(res, map.get(x - pre) + i);
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    Map<String, Integer> MAP = new HashMap<>();

    private int fun(int[] nums, int x, int left, int right, int result) {
        if (left > right) {
            return -1;
        } else if (left == right) {
            if (x == nums[left]) {
                return result + 1;
            } else {
                return -1;
            }
        }
        String key = left + "," + right;
        if (MAP.containsKey(key)) {
            return MAP.get(key);
        }
        int leftResult = -1;
        int rightResult = -1;
        if (nums[left] == x) {
            leftResult = result + 1;
            MAP.put(key, leftResult);
            return leftResult;
        } else if (nums[right] == x) {
            rightResult = result + 1;
            MAP.put(key, rightResult);
            return rightResult;
        } else if (nums[left] + nums[right] == x) {
            MAP.put(key, result + 2);
            return result + 2;
        }

        if (nums[left] < x) {
            leftResult = fun(nums, x - nums[left], left + 1, right, result + 1);
        }
        if (nums[right] < x) {
            rightResult = fun(nums, x - nums[right], left, right - 1, result + 1);
        }
        if (leftResult == -1 && rightResult == -1) {
            MAP.put(key, -1);
            return -1;
        } else if (leftResult == -1) {
            MAP.put(key, rightResult);
            return rightResult;
        } else if (rightResult == -1) {
            MAP.put(key, leftResult);
            return leftResult;
        }
        int tempResult = Math.min(leftResult, rightResult);
        MAP.put(key, tempResult);
        return tempResult;
    }
}
