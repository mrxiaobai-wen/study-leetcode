package dynamic_programming.medium;

import org.junit.Test;

import java.util.Arrays;

/**
 * 698. 划分为k个相等的子集
 *
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *  
 *
 * 提示：
 *
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanPartitionKSubsets_698 {

    @Test
    public void test() {
        assert true == canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1},4);
        assert false == canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1},5);
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        //return fun1(nums,k);
        // 动态规划又叫记忆化递归
        return dp(nums,k);
    }

    enum Result { TRUE, FALSE }
    public boolean dp(int[] nums, int k) {
        //nums数组的总和
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        // memo长度是2的nums.length次方
        Result[] memo = new Result[1 << nums.length];
        // 最后一个位置设为true
        memo[(1 << nums.length) - 1] = Result.TRUE;
        return search(0, sum, memo, nums, sum / k);
    }

    //nums下标、剩余的nums的和、memo数组、nums数组、每组的和
    // used 是整形、有32位， 而nums长度最长是16,所以used完全够用。used某一位是1代表nums[某一位]被用过
    boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {
            memo[used] = Result.FALSE;
            // 防止targ为0,所以求余之前每次减掉1、求余之后再加1,
            int targ = (todo - 1) % target + 1;
            // 先尝试放第1个数、如果成功就结束，如果不成功、就再尝试放第2个数，依次类推、直到最后一个数
            for (int i = 0; i < nums.length; i++) {
                //这个位置的数没有用过、然后不大于target，就可用
                if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                    if (search(used | (1<<i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
                }
            }
        }
        return memo[used] == Result.TRUE;
    }

    public boolean fun1(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int avg = sum / k;
        int[] arr = new int[k];
        Arrays.fill(arr,avg);
        Arrays.sort(nums);
        if (nums[nums.length - 1] > avg) {
            return false;
        }
        return helper(nums,nums.length - 1,arr,k);
    }
    private boolean helper(int[] nums,int cur,int[] arr,int k) {
        if (cur < 0) {
            return true;
        }
        for (int i = 0;i < k;i++) {
            if (arr[i] >= nums[cur]) {
                arr[i] -= nums[cur];
                if (helper(nums,cur - 1,arr,k)) {
                    return true;
                }
                arr[i] += nums[cur];
            }
        }
        return false;
    }
}
