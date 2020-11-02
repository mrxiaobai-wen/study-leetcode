package dynamic_programming.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 740. 删除与获得点数
 * <p>
 * 给定一个整数数组 nums ，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [3, 4, 2]
 * 输出: 6
 * 解释:
 * 删除 4 来获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
 * 示例 2:
 * <p>
 * 输入: nums = [2, 2, 3, 3, 3, 4]
 * 输出: 9
 * 解释:
 * 删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * 注意:
 * <p>
 * nums的长度最大为20000。
 * 每个整数nums[i]的大小都在[1, 10000]范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteAndEarn_740 {

    @Test
    public void ceshi() {
        System.out.println(deleteAndEarn(new int[]{3, 4, 2}));
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
        System.out.println(deleteAndEarn(new int[]{8, 10, 4, 9, 1, 3, 5, 9, 4, 10}));
    }

    public int deleteAndEarn(int[] nums) {
        // 读题错误！！！
        // return fun1(nums);

        //return fun2(nums);

        return fun3(nums);
    }

    private int fun3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int lastResult = 0;
        int[] first = handle(nums, 0);
        int preResult = first[0];
        int preIndex = first[1];
        int preNum = first[2];
        for (int i = preIndex + 1; i < nums.length; ) {
            int[] temp = handle(nums, i);
            int tempResult = temp[0];
            int tempIndex = temp[1];
            int tempNum = temp[2];
            if (Math.abs(tempNum - preNum) != 1) {
                int result = preResult + tempResult;
                lastResult = preResult;
                preResult = result;
            } else {
                int result = Math.max(preResult, lastResult + tempResult);
                lastResult = preResult;
                preResult = result;
            }
            preNum = tempNum;
            i = tempIndex + 1;
        }
        return Math.max(lastResult, preResult);
    }

    private int[] handle(int[] nums, int start) {
        int[] arr = new int[3];
        int num = nums[start];
        int sum = nums[start];
        int i = start + 1;
        for (; i < nums.length; i++) {
            if (num == nums[i]) {
                sum += nums[i];
            } else {
                break;
            }
        }
        arr[0] = sum;
        arr[1] = i - 1;
        arr[2] = num;
        return arr;
    }

    private int fun2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        // 上上一个结果
        int lastResult = 0;
        // 上一个结果
        int preResult = 0;

        int lastIndex = 0;
        int lastNum = nums[0];

        int curResult = nums[0];
        for (int i = 1; i <= nums.length - 1; i++) {
            int cur = nums[i];
            if (cur == lastNum) {
                curResult += cur;
            } else {
                if (Math.abs(nums[i - 1] - nums[lastIndex]) != 1) {
                    int result = preResult + curResult;
                    lastResult = preResult;
                    preResult = result;
                } else {
                    int result = Math.max(lastResult + curResult, preResult);
                    lastResult = preResult;
                    preResult = result;
                }
                curResult = nums[i];
                lastNum = nums[i - 1];
                lastIndex = i - 1;
            }
        }
        if (Math.abs(nums[nums.length - 1] - nums[lastIndex]) != 1) {
            int result = preResult + curResult;
            lastResult = preResult;
            preResult = result;
        } else {
            int result = Math.max(lastResult + curResult, preResult);
            lastResult = preResult;
            preResult = result;
        }
        return Math.max(lastResult, preResult);
    }

    /**
     * 题目理解错误！！！
     */
    private int fun1(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        map = ((TreeMap) map).descendingMap();
        int resut = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() <= 0) {
                continue;
            }
            if (map.containsKey(entry.getKey() - 1)) {
                map.put(entry.getKey() - 1, map.get(entry.getKey() - 1) - entry.getValue());
            }
            resut += entry.getKey() * entry.getValue();
        }
        return resut;
    }
}
