package week_competition.single_week.week_178;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1365. 有多少小于当前数字的数字
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * <p>
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class SmallerNumbersThanCurrent_1365 {
    public static void main(String[] args) {
        SmallerNumbersThanCurrent_1365 ceshi = new SmallerNumbersThanCurrent_1365();
        System.out.println(ceshi.smallerNumbersThanCurrent(new int[]{6, 5, 4, 8}).toString());
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        return fun(nums);
        //return fun1(nums);
    }

    private int[] fun(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        for (int i = 0;i < len;i++) {
            int count = 0;
            for (int j = 0;j < len;j++) {
                if (i != j && nums[j] < nums[i]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    private int[] fun1(int[] nums) {
        int[] result = new int[nums.length];
        TreeMap<Integer, Integer> tempMap = new TreeMap<>();
        for (int temp : nums) {
            if (tempMap.containsKey(temp)) {
                tempMap.put(temp, tempMap.get(temp) + 1);
            } else {
                tempMap.put(temp, 1);
            }
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
            countMap.put(entry.getKey(), sum);
            sum += entry.getValue();
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = countMap.get(nums[i]);
        }
        return result;
    }
}
