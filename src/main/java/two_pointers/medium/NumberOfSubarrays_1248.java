package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1248. 统计「优美子数组」
 * <p>
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfSubarrays_1248 {

    @Test
    public void test() {
        Assert.assertEquals(2, numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        Assert.assertEquals(0, numberOfSubarrays(new int[]{2, 4, 6}, 1));
        Assert.assertEquals(16, numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }

    public int numberOfSubarrays(int[] nums, int k) {
        return fun(nums, k);
    }

    private int fun(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        // 奇妙思想：前后添加一个类似哨兵的东西，大大降低边界值维护的难度！！！！！！
        list.add(-1);
        for (int i = 0; i < len; i++) {
            if (nums[i] % 2 == 1) {
                // 奇数
                list.add(i);
                count++;
            }
        }
        list.add(len);
        int result = 0;
        for (int i = 1; i + k < count + 2; i++) {
            int curPreIndex = list.get(i - 1);
            int curIndex = list.get(i);
            int lastPreIndex = list.get(i + k - 1);
            int lastIndex = list.get(i + k);
            result += (curIndex - curPreIndex) * (lastIndex - lastPreIndex);
        }
        return result;
    }
}
