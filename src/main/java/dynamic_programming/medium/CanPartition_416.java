package dynamic_programming.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 416. 分割等和子集
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanPartition_416 {

    @Test
    public void test() {
        //System.out.println(fun1(new int[]{1, 5, 11, 5}));
        //System.out.println(fun1(new int[]{1, 2, 3, 5}));
        //System.out.println(fun1(new int[]{1, 2, 5}));
        System.out.println(fun1(new int[]{3,3,3,4,5}));
    }

    public boolean canPartition(int[] nums) {
        return fun1(nums);
    }

    /**
     * 377. 组合总和 Ⅳ,这的元素不能重复使用
     * @param nums
     * @return
     */
    public boolean fun1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        Set<Integer> tempSet = new HashSet<>();
        tempSet.add(0);
        tempSet.add(nums[0]);
        for (int i = 1;i < nums.length;i++) {
            int cur = nums[i];
            List<Integer> tempList = new ArrayList<>();
            for (Integer temp : tempSet) {
                if (temp + cur == target) {
                    return true;
                } else if (temp + cur < target) {
                    tempList.add(temp + cur);
                }
            }
            tempSet.addAll(tempList);
        }
        return false;
    }
}
