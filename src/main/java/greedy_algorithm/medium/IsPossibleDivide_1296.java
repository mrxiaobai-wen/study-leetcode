package greedy_algorithm.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1296. 划分数组为连续数字的集合
 * 给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。
 * 如果可以，请返回 True；否则，返回 False。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,3,4,4,5,6], k = 4
 * 输出：true
 * 解释：数组可以分成 [1,2,3,4] 和 [3,4,5,6]。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * 输出：true
 * 解释：数组可以分成 [1,2,3] , [2,3,4] , [3,4,5] 和 [9,10,11]。
 * 示例 3：
 * <p>
 * 输入：nums = [3,3,2,2,1,1], k = 3
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：nums = [1,2,3,4], k = 3
 * 输出：false
 * 解释：数组不能分成几个大小为 3 的子数组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */
public class IsPossibleDivide_1296 {
    @Test
    public void test() {
        isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4);
    }

    /**
     * 题目意思：是一个分组的k个数是要连续的
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        return fun(nums, k);
    }

    private boolean fun(int[] nums, int k) {
        if (nums == null || nums.length % k != 0) {
            return false;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < len / k; i++) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int start = entry.getKey();
                entry.setValue(entry.getValue() - 1);
                if (entry.getValue() <= 0) {
                    map.remove(entry.getKey());
                }
                for (int j = 1; j < k; j++) {
                    if (!map.containsKey(start + j) || map.get(start + j) <= 0) {
                        return false;
                    } else {
                        int val = map.get(start + j) - 1;
                        if (val > 0) {
                            map.put(start + j, val);
                        } else {
                            map.remove(start + j);
                        }
                    }
                }
                break;
            }
        }
        return true;
    }
}
