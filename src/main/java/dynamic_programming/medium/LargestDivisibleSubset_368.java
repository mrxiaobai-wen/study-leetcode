package dynamic_programming.medium;

import org.junit.Test;

import java.util.*;

/**
 * 368. 最大整除子集
 *
 * 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
 *
 * 如果有多个目标子集，返回其中任何一个均可。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2] (当然, [1,3] 也正确)
 * 示例 2:
 *
 * 输入: [1,2,4,8]
 * 输出: [1,2,4,8]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestDivisibleSubset_368 {

    @Test
    public void test() {
        System.out.println(fun1(new int[]{1,2,3}));
        System.out.println(fun1(new int[]{1,2,4,8}));
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        return fun1(nums);
    }

    private List<Integer> fun1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        Map<Integer,List<Integer>> containerMap = new HashMap<>();
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0;i < len;i++) {
            dp[i] = 1;
            containerMap.put(nums[i],new ArrayList<>());
        }
        int resultIndex = 0;
        for (int i = 0;i < len;i++) {
            List<Integer> preList = null;
            for (int j = 0;j < i;j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    preList = containerMap.get(nums[j]);
                }
            }
            if (dp[resultIndex] < dp[i]) {
                resultIndex = i;
            }
            if (preList != null) {
                containerMap.get(nums[i]).addAll(preList);
            }
            containerMap.get(nums[i]).add(nums[i]);
        }
        return containerMap.get(nums[resultIndex]);
    }
}
